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
    @keycloak = new Keycloak()
    @keycloak.init({
        onLoad: 'login-required'
        checkLoginIframe: false
        preserveFragment: false
    }).success( =>

      unless @keycloak.hasResourceRole("admin", "admin")
        alert("Only admins are allowed to work on this interface. You'll be logged out")
        @keycloak.logout()

      $rootScope.username = @keycloak.idToken.name
      $rootScope.$apply()
    ).error( =>
      toaster.pop('error', 'Error', 'An unknown error happened during the login procedure.')
    )

  logout: ->
    @keycloak.logout()

  updateToken: (periodicity) ->
    @keycloak.updateToken(periodicity)

  token: ->
    @keycloak.token

  isAuthenticated: ->
    @keycloak.authenticated
]