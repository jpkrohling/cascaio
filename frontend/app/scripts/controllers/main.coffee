'use strict'

###*
 # @ngdoc function
 # @name frontendApp.controller:MainCtrl
 # @description
 # # MainCtrl
 # Controller of the frontendApp
###
angular.module('frontendApp').controller 'MainCtrl', ($scope, $idle, $route, Auth, config) ->

  $scope.logout = ()->
    Auth.logout()

  $scope.$on '$keepalive', ->
    Auth.updateToken(60).error(-> Auth.logout())

  $scope.$on '$idleStart', ->
    $('#idle').slideDown()

  $scope.$on '$idleEnd', ->
    $("#idle").slideUp()

  $scope.$on '$idleTimeout', ->
    $("#idle").slideUp()
    $scope.logout()

  $idle.watch()
  $route.reload()