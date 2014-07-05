'use strict'

###*
 # @ngdoc function
 # @name frontendApp.controller:CheckingAccountDetailCtrl
 # @description
 # # CheckingAccountDetailCtrl
 # Controller of the frontendApp
###
angular.module('frontendApp').controller 'CheckingAccountDetailCtrl', ($scope, $routeParams, CheckingAccount) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-checking-accounts').addClass('active')

  $scope.checkingAccount = CheckingAccount.get({id: $routeParams.id})
