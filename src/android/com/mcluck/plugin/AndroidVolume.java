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
		} else if ("getDTMF".equals(action)) {
			getDTMFVolume(callbackContext);
			return true;
		} else if ("getMusic".equals(action)) {
			getMusicVolume(callbackContext);
			return true;
		} else if ("getNotification".equals(action)) {
			getNotificationVolume(callbackContext);
			return true;
		} else if ("getRinger".equals(action)) {
			getRingerVolume(callbackContext);
			return true;
		} else if ("getSystem".equals(action)) {
			getSystemVolume(callbackContext);
			return true;
		} else if ("getVoiceCall".equals(action)) {
			getVoiceCallVolume(callbackContext);
			return true;
		} else if ("getAlarm".equals(action)) {
			getAlarmVolume(callbackContext);
			return true;
		}

		return false;
	}

	public void setVolume(
		final int streamType,
		final String volumeType,
		final int volume,
		final boolean showToast,
		CallbackContext callbackContext
	) {
        final Context context = this.cordova.getActivity();
        final CallbackContext _callbackContext = callbackContext;

		cordova.getThreadPool()
		.execute(new Runnable() {
			public void run() {
				AudioManager manager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
				int max = manager.getStreamMaxVolume(streamType);
				int newVolume = volume;
				if (volume != 0) {
					double percent = (double)volume / 100;
					newVolume = (int)(max * percent);
				}
				manager.setStreamVolume(streamType, newVolume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
				if (showToast) {
					String volumeLabel = (volumeType.length() > 0 ? volumeType + " " : "") +  "Volume: " + String.valueOf(volume);
					Toast.makeText(
						webView.getContext(),
						volumeLabel,
						Toast.LENGTH_LONG
					).show();
				}
				if (_callbackContext != null) {
					_callbackContext.success(volume);
				}
			}
		});
	}

	public void getVolume(final int streamType, CallbackContext callbackContext) {
        final Context context = this.cordova.getActivity();
        final CallbackContext _callbackContext = callbackContext;

		cordova.getThreadPool()
		.execute(new Runnable() {
			public void run() {
				AudioManager manager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
				int max = manager.getStreamMaxVolume(streamType);
				int volume = manager.getStreamVolume(streamType);
				if (volume != 0) {
					double percent = (double)volume / (double)max;
					volume = (int)Math.round(percent * 100);
				}
				_callbackContext.success(volume);
			}
		});
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

	public void getAlarmVolume(CallbackContext callbackContext) {
		getVolume(AudioManager.STREAM_ALARM, callbackContext);
	}

	public void setAlarmVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_ALARM, "Alarm", volume, showToast, callbackContext);
	}

	public void getDTMFVolume(CallbackContext callbackContext) {
		getVolume(AudioManager.STREAM_DTMF, callbackContext);
	}

	public void setDTMFVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_DTMF, "DTMF", volume, showToast, callbackContext);
	}

	public void getMusicVolume(CallbackContext callbackContext) {
		getVolume(AudioManager.STREAM_MUSIC, callbackContext);
	}

	public void setMusicVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_MUSIC, "Music", volume, showToast, callbackContext);
	}

	public void getNotificationVolume(CallbackContext callbackContext) {
		getVolume(AudioManager.STREAM_NOTIFICATION, callbackContext);
	}

	public void setNotificationVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_NOTIFICATION, "Notification", volume, showToast, callbackContext);
	}

	public void getRingerVolume(CallbackContext callbackContext) {
		getVolume(AudioManager.STREAM_RING, callbackContext);
	}

	public void setRingerVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_RING, "Ringer", volume, showToast, callbackContext);
	}

	public void getSystemVolume(CallbackContext callbackContext) {
		getVolume(AudioManager.STREAM_SYSTEM, callbackContext);
	}

	public void setSystemVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_SYSTEM, "System", volume, showToast, callbackContext);
	}

	public void getVoiceCallVolume(CallbackContext callbackContext) {
		getVolume(AudioManager.STREAM_VOICE_CALL, callbackContext);
	}

	public void setVoiceCallVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setVolume(AudioManager.STREAM_VOICE_CALL, "Voice Call", volume, showToast, callbackContext);
	}
}
