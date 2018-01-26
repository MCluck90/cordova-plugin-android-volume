# Cordova Android Volume Plugin

Very self-explanatory. Allows you to get and set the volume for Android devices in a Cordova app.

# API

## Get Functions

`success` is a callback called to return the volume. The first parameter is the volume level.

`error` is a callback called if anything goes wrong. (Optional)

```js
// Get the alarm volume level
window.androidVolume.getAlarm(success, error);

// Get the DTMF volume level
window.androidVolume.getDTMF(success, error);

// Get the music/media volume level
window.androidVolume.getMusic(success, error);

// Get the notification volume level
window.androidVolume.getNotification(success, error);

// Get the ringer volume level
window.androidVolume.getRinger(success, error);

// Get the system volume level
window.androidVolume.getSystem(success, error);

// Get the voice call volume level
window.androidVolume.getVoiceCall(success, error);
```

## Set Functions

`volume` is an integer between 0 and 100.

`showToast` is a boolean which, if true, will report the volume change in a toast message. Default: `true`. (Optional)

`success` is a callback called whenever the volume is changed. The first parameter is the new volume value. (Optional)

`error` is a calback called if anything goes wrong. (Optional)

```js
// Set all different types of volume to the same level
window.androidVolume.set(volume, showToast, success, error)

// Set the alarm volume level
window.androidVolume.setAlarm(volume, showToast, success, error)

// Set all different types of volume to the same level
// Alias for `set`
window.androidVolume.setAll(volume, showToast, success, error)

// Set the DTMF volume level
window.androidVolume.setDTMF(volume, showToast, success, error)

// Set the music/media volume level
window.androidVolume.setMusic(volume, showToast, success, error)

// Set the notification volume level
window.androidVolume.setNotification(volume, showToast, success, error)

// Set the ringer/ringtone volume level
window.androidVolume.setRinger(volume, showToast, success, error)

// Set the system volume level
window.androidVolume.setSystem(volume, showToast, success, error)

// Set the voice call volume level
window.androidVolume.setVoiceCall(volume, showToast, success, error)
```