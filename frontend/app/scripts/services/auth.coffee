'use strict'

###*
 # @ngdoc service
 # @name frontendApp.Auth
 # @description
 # # Auth
 # Service in the frontendApp.
###
angular.module('frontendApp').service 'Auth', ['$window', '$rootScope', 'toaster', class Auth
  constructor: ($window, $rootScope, toaster) ->
    @keycloak = $window.keycloak
    unless @keycloak.hasResourceRole("user", "frontend")
      alert("There's something wrong with your credentials. Contact support.")
      @keycloak.logout()

    $rootScope.username = @keycloak.idToken.name

  logout: ->
    @keycloak.logout()

  updateToken: (periodicity) ->
    @keycloak.updateToken(periodicity)

  token: ->
    @keycloak.token

  isAuthenticated: ->
    @keycloak.authenticated
]
