'use strict'

###*
 # @ngdoc service
 # @name adminApp.AuthInterceptor
 # @description
 # # AuthInterceptor
 # Service in the adminApp.
###
angular.module('adminApp').factory 'AuthInterceptor', ($q, toaster, apiUrl) ->
  {
  request: (request) ->
    if request.url.indexOf(apiUrl) == -1
      return request

    addBearer = ->
      window.keycloak.updateToken(5).success(() ->
        request.headers.Authorization = 'Bearer ' + window.keycloak.token;
        deferred.notify()
        deferred.resolve(request)
      )

    deferred = $q.defer()
    if (window.keycloak.authenticated)
      addBearer()
    else
      oldCallback = window.keycloak.onAuthSuccess
      window.keycloak.onAuthSuccess = ->
        oldCallback && oldCallback()
        addBearer()
        window.keycloak.onAuthSuccess = oldCallback

    return $q.when(deferred.promise)

  responseError: (rejection) ->
    if (rejection.status == 401)
      toaster.pop('error', 'Logged out', 'Your session has expired. Please, login again.')
      window.keycloak.logout()

    return $q.reject(rejection)
  }


angular.module('adminApp').config ($httpProvider) -> $httpProvider.interceptors.push('AuthInterceptor')
