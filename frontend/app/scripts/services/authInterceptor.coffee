'use strict'

angular.module('frontendApp')
  .factory 'AuthInterceptor', ['$rootScope', ($rootScope) ->
      {
      request: (config) ->
        if $rootScope.keycloak and $rootScope.keycloak.token
          console.log("Intercepted request, adding token " + $rootScope.keycloak.token)
          config.headers.Authorization = 'Bearer ' + $rootScope.keycloak.token;
        return config
      response: (config) ->
        console.log("Intercepted response, check if unauthorized is here")
        return config
      }
    ]

angular.module('frontendApp')
  .config ['$httpProvider', ($httpProvider) ->
      $httpProvider.interceptors.push('AuthInterceptor')
  ]