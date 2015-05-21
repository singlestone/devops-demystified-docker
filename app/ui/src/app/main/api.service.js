(function () {
  'use strict';

  angular
    .module('devops')
    .service('Api', Api);


  Api.$inject = ['$http'];
  function Api($http) {

    this.allParticipants = function() {
      return $http.get('api/participants').then(function(response) {
        return response.data;
      });
    };
  }
})();
