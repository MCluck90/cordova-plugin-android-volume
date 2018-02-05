var exec = require('cordova/exec');

function createGetVolume(funcName) {
	return function(success, error) {
		success = success || function(){};
		error = error || function(){};
		exec(success, error, 'AndroidVolume', funcName, []);
	}
}

function createSetVolume(funcName) {
	return function(volume, showToast, success, error) {
		showToast = showToast || false;
		success = success || function(){};
		error = error || function(){}
		exec(success, error, 'AndroidVolume', funcName, [volume, showToast]);
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