'use strict'

###*
 # @ngdoc function
 # @name frontendApp.controller:CheckingAccountsCtrl
 # @description
 # # CheckingAccountsCtrl
 # Controller of the frontendApp
###
angular.module('frontendApp').controller 'CheckingAccountsCtrl', ($scope, $filter, $location, toaster, CheckingAccount, CheckingAccountTransaction) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-checking-accounts').addClass('active')

  $scope.loading = false
  $scope.checkingAccounts = []

  $scope.load = ->
    $scope.loading = true
    $scope.checkingAccounts = CheckingAccount.query({}, ->
      $scope.loading = false
    , -> $scope.loading = false
    )

  $scope.showCreateForm = ->
    $location.path('/checkingAccounts/new')

  $scope.load()
