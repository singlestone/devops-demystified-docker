(function () {
  'use strict';

  angular
    .module('devops')
    .controller('MainCtrl', MainCtrl);


  MainCtrl.$inject = ['$interval', 'Api'];
  function MainCtrl($interval, Api) {
    var ctrl = this;

    $interval(function() {
      Api.allParticipants().then(function(participants) {
        ctrl.participantRows = _.groupBy(_.sortBy(participants, 'name'), function(p, idx) {
          return Math.floor(idx / 4);
        });
      });
    }, 2000);
  }
})();
