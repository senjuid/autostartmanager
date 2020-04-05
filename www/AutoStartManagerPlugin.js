var exec = require('cordova/exec');

module.exports = {
  showStartManager: function (success, error) {
    exec(success, error, 'AutoStartManagerPlugin', 'showStartManager', []);
  },
  checkAutomaticDateTimeZone: function (success) {
    exec(success, null, 'AutoStartManagerPlugin', 'checkAutomaticDateTimeZone', []);
  },
  checkCamera: function (success) {
    exec(success, null, 'AutoStartManagerPlugin', 'checkCamera', []);
  }
}
