'use strict'

###*
 # @ngdoc service
 # @name adminApp.Auth
 # @description
 # # Auth
 # Service in the adminApp.
###
angular.module('adminApp').service 'Auth', ['$location', '$window', '$rootScope', 'toaster', class Auth
  constructor: ($location, $window, $rootScope, toaster) ->
    @keycloak = $window.keycloak
    unless @keycloak.hasResourceRole("admin", "admin")
      alert("Only admins are allowed to work on this interface. You'll be logged out")
      @keycloak.logout()

    $rootScope.username = @keycloak.idToken.name
    $rootScope.$apply()

  logout: ->
    @keycloak.logout()

  updateToken: (periodicity) ->
    @keycloak.updateToken(periodicity)

  token: ->
    @keycloak.token

  isAuthenticated: ->
    @keycloak.authenticated
]