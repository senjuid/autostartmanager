var exec = require('cordova/exec');

exports.showStartManager = function (success, error) {
  exec(success, error, 'AutoStartManagerPlugin', 'showStartManager', []);
};
