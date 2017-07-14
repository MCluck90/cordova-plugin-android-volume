package com.mcluck.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import android.content.Context;
import android.media.AudioManager;
import android.widget.Toast;

public class AndroidVolume extends CordovaPlugin {
	@Override
	public boolean execute(
		String action,
		JSONArray args,
		CallbackContext callbackContext
	) throws JSONException {
		if ("setAlarm".equals(action)) {
			setAlarmVolume(args.getInt(0), args.getBoolean(1), callbackContext);
			return true;
		} else if ("setAll".equals(action)) {
			setAllVolumes(args.getInt(0), args.getBoolean(1), callbackContext);
			return true;
		} else if ("setDTMF".equals(action)) {
			setDTMFVolume(args.getInt(0), args.getBoolean(1), callbackContext);
			return true;
		} else if ("setMusic".equals(action)) {
			setMusicVolume(args.getInt(0), args.getBoolean(1), callbackContext);
			return true;
		} else if ("setNotification".equals(action)) {
			setNotificationVolume(args.getInt(0), args.getBoolean(1), callbackContext);
			return true;
		} else if ("setRinger".equals(action)) {
			setRingerVolume(args.getInt(0), args.getBoolean(1), callbackContext);
			return true;
		} else if ("setSystem".equals(action)) {
			setSystemVolume(args.getInt(0), args.getBoolean(1), callbackContext);
			return true;
		} else if ("setVoiceCall".equals(action)) {
			setVoiceCallVolume(args.getInt(0), args.getBoolean(1), callbackContext);
			return true;
		}

		return false;
	}

	public void setVolume(
		int streamType,
		String volumeType,
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		AudioManager manager = (AudioManager)this.cordova.getActivity().getSystemService(Context.AUDIO_SERVICE);
		manager.setStreamVolume(streamType, volume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		if (showToast) {
			if (volumeType.length() > 0) {
				volumeType += " ";
			}
			Toast.makeText(
				webView.getContext(),
				volumeType + "Volume: " + String.valueOf(volume),
				Toast.LENGTH_LONG
			).show();
		}
		if (callbackContext != null) {
			callbackContext.success(volume);
		}
	}

	public void setAllVolumes(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_ALARM, "", volume, false, null);
		setVolume(AudioManager.STREAM_DTMF, "", volume, false, null);
		setVolume(AudioManager.STREAM_MUSIC, "", volume, false, null);
		setVolume(AudioManager.STREAM_NOTIFICATION, "", volume, false, null);
		setVolume(AudioManager.STREAM_RING, "", volume, false, null);
		setVolume(AudioManager.STREAM_SYSTEM, "", volume, false, null);
		setVolume(AudioManager.STREAM_VOICE_CALL, "", volume, showToast, callbackContext);
	}

	public void setAlarmVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_ALARM, "Alarm", volume, showToast, callbackContext);
	}

	public void setDTMFVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_DTMF, "DTMF", volume, showToast, callbackContext);
	}

	public void setMusicVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_MUSIC, "Music", volume, showToast, callbackContext);
	}

	public void setNotificationVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_NOTIFICATION, "Notification", volume, showToast, callbackContext);
	}

	public void setRingerVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_RING, "Ringer", volume, showToast, callbackContext);
	}

	public void setSystemVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_SYSTEM, "System", volume, showToast, callbackContext);
	}

	public void setVoiceCallVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_VOICE_CALL, "Voice Call", volume, showToast, callbackContext);
	}
}