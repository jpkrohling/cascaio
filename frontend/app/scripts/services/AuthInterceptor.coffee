'use strict'

angular.module('frontendApp')
  .factory 'AuthInterceptor', ['$q', ($q) ->
      {
        request: (config) ->
          if config.url.indexOf('api.cascaio.com') == -1
            return config

          addBearer = () ->
            window.keycloak.updateToken(5).success(() ->
              config.headers.Authorization = 'Bearer ' + window.keycloak.token;
              deferred.notify()
              deferred.resolve(config)
            )

          deferred = $q.defer()
          if (window.keycloak.authenticated)
            addBearer()
          else
            oldCallback = window.keycloak.onAuthSuccess
            window.keycloak.onAuthSuccess = () ->
              oldCallback && oldCallback()
              addBearer()
              window.keycloak.onAuthSuccess = oldCallback

          return $q.when(deferred.promise)
        response: (config) ->
          return config
      }
    ]

angular.module('frontendApp')
  .config ['$httpProvider', ($httpProvider) ->
      $httpProvider.interceptors.push('AuthInterceptor')
  ]