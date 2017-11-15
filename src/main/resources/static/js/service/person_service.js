'use strict';

angular.module('App').service('PersonService', ['$http', '$q', function($http, $q) {
		var REST_SERVICE_URI = "http://127.0.0.1:8080/person/";
			
        return {
			fetchAllPersons: function() {
				var deferred = $q.defer();
				$http.get(REST_SERVICE_URI)
				.then(
					function (response) {
						deferred.resolve(response.data);
					},
					function(errResponse){
						console.error('Error while fetching Persons');
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			},
			
			fetchPerson: function(id) {
				var deferred = $q.defer();
				$http.get(REST_SERVICE_URI+id)
				.then(function (response) {
                    if (response.status == 200) {
                        deferred.resolve(response.data);
                    }
                    else {
                        deferred.reject('Error while fetching Person');
                    }
				});
				return deferred.promise;
			},
			
		    createPerson: function(person) {
				var deferred = $q.defer();
				$http.post(REST_SERVICE_URI, person)
				.then(
					function (response) {
						deferred.resolve(response.data);
					},
					function(errResponse){
						console.error('Error while creating User');
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			},

			
			deletePerson: function(id) {
				var deferred = $q.defer();
				$http.delete(REST_SERVICE_URI+id)
				.then(
					function (response) {
						deferred.resolve(response.data);
					},
					function(errResponse){
						console.error('Error while deleting Person');
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			},
			
			updatePerson: function(id, person) {
				var deferred = $q.defer();
				$http.put(REST_SERVICE_URI+id, person)
				.then(
					function (response) {
						deferred.resolve(response.data);
					},
					function(errResponse){
						console.error('Error while updating Person');
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			}
		}	
}]);