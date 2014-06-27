'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:MainCtrl
 # @description
 # # MainCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'MainCtrl', ($scope, $idle) ->
  previousCallback = window.keycloak.onAuthSuccess
  window.keycloak.onAuthSuccess = ->
    previousCallback && previousCallback()
    $scope.username = window.keycloak.idToken.name

  $scope.logout = ()->
    window.keycloak.logout()

  $scope.$on '$keepalive', () ->
    window.keycloak.updateToken(60)

  $scope.$on '$idleStart', () ->
    $('#idle').slideDown()

  $scope.$on '$idleEnd', () ->
    $("#idle").slideUp()

  $scope.$on '$idleTimeout', () ->
    $("#idle").slideUp()
    $scope.logout()

  $idle.watch();
