
'use strict';

angular.module('App').service('TagService', ['$http', '$q', function($http, $q) {
		var REST_SERVICE_URI = "http://127.0.0.1:8080/tag/";
			
        return {
			fetchAllTags: function() {
				var deferred = $q.defer();
				$http.get(REST_SERVICE_URI)
				.then(
					function (response) {
						deferred.resolve(response.data);
					},
					function(errResponse){
						console.error('Error while fetching Tags');
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			},
			
			fetchTag: function(tagId) {
				var deferred = $q.defer();
				$http.get(REST_SERVICE_URI+tagId)
				.then(function (response) {
                    if (response.status == 200) {
                        deferred.resolve(response.data);
                    }
                    else {
                        deferred.reject('Error while fetching Tag');
                    }
				});
				return deferred.promise;
			}
		}	
}]);