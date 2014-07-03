'use strict'

###*
 # @ngdoc service
 # @name adminApp.AuthInterceptor
 # @description
 # # AuthInterceptor
 # Service in the adminApp.
###
angular.module('adminApp').factory 'AuthInterceptor', ($q, toaster, config, Auth) ->
  {
  request: (request) ->
    if request.url.indexOf(config.apihost) == -1
      return request

    addBearer = ->
      Auth.updateToken(5)
      .success(->
        request.headers.Authorization = 'Bearer ' + Auth.token();
        deferred.notify()
        deferred.resolve(request)
      )

    deferred = $q.defer()
    if (Auth.isAuthenticated())
      addBearer()
    else
      Auth.onAuthSuccess = -> addBearer()

    return $q.when(deferred.promise)

  responseError: (rejection) ->
    if (rejection.status == 401)
      toaster.pop('error', 'Logged out', 'Your session has expired. Please, login again.')
      Auth.logout()

    return $q.reject(rejection)
  }


angular.module('adminApp').config ($httpProvider) -> $httpProvider.interceptors.push('AuthInterceptor')
