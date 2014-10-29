'use strict'

###*
 # @ngdoc function
 # @name frontendApp.controller:MutualFundAccountsCtrl
 # @description
 # # MutualFundAccountsCtrl
 # Controller of the frontendApp
###
angular.module('frontendApp').controller 'MutualFundAccountsCtrl', ($scope, $filter, $location, toaster, MutualFundAccount) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-mutual-funds').addClass('active')

  $scope.loading = false
  $scope.mutualFundAccounts = []

  $scope.load = ->
    $scope.loading = true
    $scope.mutualFundAccounts = MutualFundAccount.query({}, ->
      $scope.loading = false
    , -> $scope.loading = false
    )

  $scope.showCreateForm = ->
    $location.path('/mutualFundAccounts/new')

  $scope.load()
