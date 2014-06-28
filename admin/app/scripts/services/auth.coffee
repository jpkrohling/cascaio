'use strict'

###*
 # @ngdoc service
 # @name adminApp.Auth
 # @description
 # # Auth
 # Service in the adminApp.
###
angular.module('adminApp').service 'Auth', ($window) ->
  $window.keycloak
