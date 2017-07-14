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
		if ("set".equals(action)) {
			setAllVolumes(args.getInt(0), args.getBoolean(1), callbackContext);
			return true;
		} else if ("setAlarm".equals(action)) {
			setAlarmVolume(args.getInt(0), args.getBoolean(1), callbackContext);
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
		}

		return false;
	}

	private void setAllVolumes(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		setAlarmVolume(volume, false, callbackContext);
		setMusicVolume(volume, false, callbackContext);
		setNotificationVolume(volume, false, callbackContext);
		setRingerVolume(volume, false, callbackContext);
		setSystemVolume(volume, false, callbackContext);
		if (showToast) {
			Toast.makeText(
				webView.getContext(),
				"Volume: " + String.valueOf(volume),
				Toast.LENGTH_LONG
			).show();
		}
		callbackContext.success(volume);
	}

	private void setMusicVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		AudioManager mgr = (AudioManager)this.cordova.getActivity().getSystemService(Context.AUDIO_SERVICE);
		mgr.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		if (showToast) {
			Toast.makeText(
				webView.getContext(),
				"Music Volume: " + String.valueOf(volume),
				Toast.LENGTH_LONG
			).show();
		}
		callbackContext.success(volume);
	}

	private void setRingerVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		AudioManager mgr = (AudioManager)this.cordova.getActivity().getSystemService(Context.AUDIO_SERVICE);
		mgr.setStreamVolume(AudioManager.STREAM_RING, volume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		if (showToast) {
			Toast.makeText(
				webView.getContext(),
				"Ringer Volume: " + String.valueOf(volume),
				Toast.LENGTH_LONG
			).show();
		}
		callbackContext.success(volume);
	}

	private void setAlarmVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		AudioManager mgr = (AudioManager)this.cordova.getActivity().getSystemService(Context.AUDIO_SERVICE);
		mgr.setStreamVolume(AudioManager.STREAM_ALARM, volume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		if (showToast) {
			Toast.makeText(
				webView.getContext(),
				"Alarm Volume: " + String.valueOf(volume),
				Toast.LENGTH_LONG
			).show();
		}
		callbackContext.success(volume);
	}

	private void setSystemVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		AudioManager mgr = (AudioManager)this.cordova.getActivity().getSystemService(Context.AUDIO_SERVICE);
		mgr.setStreamVolume(AudioManager.STREAM_SYSTEM, volume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		if (showToast) {
			Toast.makeText(
				webView.getContext(),
				"System Volume: " + String.valueOf(volume),
				Toast.LENGTH_LONG
			).show();
		}
		callbackContext.success(volume);
	}

	private void setNotificationVolume(
		int volume,
		boolean showToast,
		CallbackContext callbackContext
	) {
		AudioManager mgr = (AudioManager)this.cordova.getActivity().getSystemService(Context.AUDIO_SERVICE);
		mgr.setStreamVolume(AudioManager.STREAM_NOTIFICATION, volume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		if (showToast) {
			Toast.makeText(
				webView.getContext(),
				"Notification Volume: " + String.valueOf(volume),
				Toast.LENGTH_LONG
			).show();
		}
		callbackContext.success(volume);
	}
}