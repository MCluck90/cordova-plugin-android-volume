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

var AndroidVolume = function() {
	// Create new event handlers on the window (returns a channel instance)
    this.channels = {
        volume: cordova.addWindowEventHandler('volume'),
    };
    for (var key in this.channels) {
        this.channels[key].onHasSubscribersChange = AndroidVolume.onHasSubscribersChange;
    }
}

function numHandlers() {
	var count = 0;
	for (var key in androidVolume.channels) {
        count += androidVolume.channels[key].numHandlers;
	}
	return count;
}

AndroidVolume.onHasSubscribersChange = function () {
    // If we just registered the first handler, make sure native listener is started.
    if (this.numHandlers === 1 && numHandlers() === 1) {
        exec(androidVolume._onUpdate, androidVolume._onError, 'AndroidVolume', 'registerVolumeObserver', []);
    } else if (numHandlers() === 0) {
        exec(null, null, 'AndroidVolume', 'unregisterVolumeObserver', []);
    }
};

AndroidVolume.prototype._onUpdate = function (info) {
    cordova.fireWindowEvent('volume', info);
};

AndroidVolume.prototype._onError = function (e) {
    console.log('Error initializing AndroidVolume event: ' + e);
};


AndroidVolume.prototype.getAlarm        = createGetVolume('getAlarm');
AndroidVolume.prototype.getDTMF         = createGetVolume('getDTMF');
AndroidVolume.prototype.getMusic        = createGetVolume('getMusic');
AndroidVolume.prototype.getNotification = createGetVolume('getNotification');
AndroidVolume.prototype.getRinger       = createGetVolume('getRinger');
AndroidVolume.prototype.getSystem       = createGetVolume('getSystem');
AndroidVolume.prototype.getVoiceCall    = createGetVolume('getVoiceCall');

AndroidVolume.prototype.set             = createSetVolume('setAll');
AndroidVolume.prototype.setAlarm        = createSetVolume('setAlarm');
AndroidVolume.prototype.setAll          = AndroidVolume.prototype.set;
AndroidVolume.prototype.setDTMF         = createSetVolume('setDTMF');
AndroidVolume.prototype.setMusic        = createSetVolume('setMusic');
AndroidVolume.prototype.setNotification = createSetVolume('setNotification');
AndroidVolume.prototype.setRinger       = createSetVolume('setRinger');
AndroidVolume.prototype.setSystem       = createSetVolume('setSystem');
AndroidVolume.prototype.setVoiceCall    = createSetVolume('setVoiceCall');

var androidVolume = new AndroidVolume();
module.exports = androidVolume;