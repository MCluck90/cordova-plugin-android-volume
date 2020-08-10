package com.mcluck.plugin;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.util.SparseIntArray;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

public class AndroidVolumeObserver extends ContentObserver {

    private static final String LOG_TAG = "AndroidVolumeObserver";

    private static final int[] STREAM_TYPES = {
        AudioManager.STREAM_ALARM, 
        AudioManager.STREAM_DTMF, 
        AudioManager.STREAM_MUSIC,
        AudioManager.STREAM_NOTIFICATION,
        AudioManager.STREAM_RING,
        AudioManager.STREAM_SYSTEM,
        AudioManager.STREAM_VOICE_CALL
    };
    private static final String[] STREAM_NAMES = {
        "Alarm", 
        "DTMF", 
        "Music",
        "Notification",
        "Ringer",
        "System",
        "VoiceCall"
    };

    private AudioManager audioManager;
    private SparseIntArray volumes;
    private CallbackContext callbackContext;

    public AndroidVolumeObserver(Context context, CallbackContext callbackContext) {
        super(new Handler());

        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        this.callbackContext = callbackContext;
        this.volumes = new SparseIntArray();
        initialize(context);
    }

    public void release() {
        audioManager = null;
        volumes = null;
        if (callbackContext != null)
        {
            sendUpdate(new JSONObject(), false);
            callbackContext = null;
        }
    }

    @Override
    public boolean deliverSelfNotifications() {
        return false;
    }

    @Override
    public void onChange(boolean selfChange) {
        for (int i = 0; i < STREAM_TYPES.length; i++) {
            int streamType = STREAM_TYPES[i];
            int currentVolume = audioManager.getStreamVolume(streamType);
            if (currentVolume == volumes.get(streamType))
                continue;

            volumes.put(streamType, currentVolume);
            if (currentVolume > 0) {
                int maxVolume = audioManager.getStreamMaxVolume(streamType);
                double percent = (double)currentVolume / (double)maxVolume;
				currentVolume = (int)Math.round(percent * 100);
            }

            sendUpdate(STREAM_NAMES[i], currentVolume);
        }
    }

    private void sendUpdate(String streamType, int volume) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("volumeType", streamType);
            obj.put("volumeLevel", volume);
        } catch (JSONException e) {
            LOG.e(LOG_TAG, e.getMessage(), e);
        }
        sendUpdate(obj, true);
    }

    private void sendUpdate(JSONObject obj, boolean keepCallback) {
        PluginResult updateResult = new PluginResult(PluginResult.Status.OK, obj);
        updateResult.setKeepCallback(keepCallback);
        callbackContext.sendPluginResult(updateResult);
    }

    private void initialize(Context context) {
        for (int i = 0; i < STREAM_TYPES.length; i++) {
            int streamType = STREAM_TYPES[i];
            int volume = audioManager.getStreamVolume(streamType);
            volumes.put(streamType, volume);
        }
    }
}