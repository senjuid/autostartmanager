var exec = require('cordova/exec');

module.exports = {
  showStartManager = function (success, error) {
    exec(success, error, 'AutoStartManagerPlugin', 'showStartManager', []);
  },
  checkAutomaticDateTimeZone = function (success, error) {
    exec(success, error, 'AutoStartManagerPlugin', 'checkAutomaticDateTimeZone', []);
  }
}
