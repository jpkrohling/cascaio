'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:MainCtrl
 # @description
 # # MainCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'MainCtrl', ($scope, $idle, Auth) ->

  Auth.onAuthSuccess = ->
    $scope.username = Auth.idToken.name

  $scope.logout = ()->
    Auth.logout()

  $scope.$on '$keepalive', () ->
    Auth.updateToken(60)

  $scope.$on '$idleStart', () ->
    $('#idle').slideDown()

  $scope.$on '$idleEnd', () ->
    $("#idle").slideUp()

  $scope.$on '$idleTimeout', () ->
    $("#idle").slideUp()
    $scope.logout()

  $idle.watch();
