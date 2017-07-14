var exec = require('cordova/exec');

exports.set = function(volume, showToast, success, error) {
	if (arguments.length < 4) {
		success = showToast;
		error = success;
		showToast = true;
	}
	exec(success, error, "AndroidVolume", "set", [volume, showToast]);
};

exports.setAlarm = function(volume, showToast, success, error) {
	if (arguments.length < 4) {
		success = showToast;
		error = success;
		showToast = true;
	}
	exec(success, error, "AndroidVolume", "setAlarm", [volume, showToast]);
};

exports.setMusic = function(volume, showToast, success, error) {
	if (arguments.length < 4) {
		success = showToast;
		error = success;
		showToast = true;
	}
	exec(success, error, "AndroidVolume", "setMusic", [volume, showToast]);
};

exports.setNotification = function(volume, showToast, success, error) {
	if (arguments.length < 4) {
		success = showToast;
		error = success;
		showToast = true;
	}
	exec(success, error, "AndroidVolume", "setNotification", [volume, showToast]);
};

exports.setRinger = function(volume, showToast, success, error) {
	if (arguments.length < 4) {
		success = showToast;
		error = success;
		showToast = true;
	}
	exec(success, error, "AndroidVolume", "setRinger", [volume, showToast]);
};

exports.setSystem = function(volume, showToast, success, error) {
	if (arguments.length < 4) {
		success = showToast;
		error = success;
		showToast = true;
	}
	exec(success, error, "AndroidVolume", "setSystem", [volume, showToast]);
};