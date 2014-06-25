'use strict'

###*
 # @ngdoc service
 # @name adminApp.errorinterceptor
 # @description
 # # errorinterceptor
 # Factory in the adminApp.
###
angular.module('adminApp').factory 'ErrorInterceptor', ($q, toaster) ->
  {
  requestError: (rejection) ->
    console.log("requestError")
    console.debug(rejection)
    return $q.reject(rejection)

  responseError: (rejection) ->
    if (rejection.status == 0)
      toaster.pop('error', 'Error', 'The server seems to be offline. Try again later.')

    return $q.reject(rejection)
  }

angular.module('adminApp').config ($httpProvider) -> $httpProvider.interceptors.push('ErrorInterceptor')
