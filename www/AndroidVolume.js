var exec = require('cordova/exec');

function createSetVolume(funcName) {
	return function(volume, showToast, success, error) {
		if (arguments.length < 4) {
			success = showToast;
			error = success;
			showToast = true;
		}
		success = success || function(){};
		error = error || function(){}
		exec(success, error, 'AndroidVolume', funcName, [volume, showToast]);
	}
}

exports.set             = createSetVolume('setAll');
exports.setAlarm        = createSetVolume('setAlarm');
exports.setAll          = exports.set;
exports.setDTMF         = createSetVolume('setDTMF');
exports.setMusic        = createSetVolume('setMusic');
exports.setNotification = createSetVolume('setNotification');
exports.setRinger       = createSetVolume('setRinger');
exports.setSystem       = createSetVolume('setSystem');
exports.setVoiceCall    = createSetVolume('setVoiceCall');