function onVolumeUpdate(info) {
  cordova.fireWindowEvent('volume', info);
}

function onVolumeError(e) {
  console.error('Failed to initialize android volume event handler: ' + e);
}

const volumeEventHandler = cordova.addWindowEventHandler('volume');
volumeEventHandler.onHasSubscribersChange = function() {
  if (volumeEventHandler.numHandlers === 1) {
    exec(onVolumeUpdate, onVolumeError, 'AndroidVolume', 'registerVolumeObserver', []);
  } else if (volumeEventHandler.numHandlers === 0) {
    exec(null, null, 'AndroidVolume', 'unregisterVolumeObserver', []);
  }
}

exports.getAlarm        = createGetVolume('getAlarm');
exports.getDTMF         = createGetVolume('getDTMF');
exports.getMusic        = createGetVolume('getMusic');
exports.getNotification = createGetVolume('getNotification');
exports.getRinger       = createGetVolume('getRinger');
exports.getSystem       = createGetVolume('getSystem');
exports.getVoiceCall    = createGetVolume('getVoiceCall');

exports.set             = createSetVolume('setAll');
exports.setAlarm        = createSetVolume('setAlarm');
exports.setAll          = exports.set;
exports.setDTMF         = createSetVolume('setDTMF');
exports.setMusic        = createSetVolume('setMusic');
exports.setNotification = createSetVolume('setNotification');
exports.setRinger       = createSetVolume('setRinger');
exports.setSystem       = createSetVolume('setSystem');
exports.setVoiceCall    = createSetVolume('setVoiceCall');