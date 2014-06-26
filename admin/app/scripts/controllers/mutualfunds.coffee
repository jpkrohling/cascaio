'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:MutualFundsCtrl
 # @description
 # # MutualFundsCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'MutualFundsCtrl', ($scope, $location, $filter, toaster, ngTableParams, MutualFund) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-reference').addClass('active')

  $scope.loading = false
  $scope.mutualFunds = []

  $scope.tableParams = new ngTableParams({page: 1, count: 25}, {
    getData: ($defer, params) ->
      data = $scope.mutualFunds

      if params.filter()
        data = $filter('filter')(data, params.filter())

      params.total(data.length)
      $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()))
  })

  $scope.load = ->
    $scope.loading = true
    $scope.mutualFunds = MutualFund.query({}, ->
      $scope.loading = false
      $scope.tableParams.reload()
    )

  $scope.showCreateForm = ->
    $location.path('/reference/mutualFunds/new')

  $scope.remove = (mutualFund) ->
    mutualFund.$remove().then(->
      toaster.pop('success', '', 'Mutual Fund removed')
      $scope.mutualFunds.splice( $scope.mutualFunds.indexOf(mutualFund), 1 );
      $scope.tableParams.reload()
    )

  $scope.openQuotes = (mutualFund) ->
    $location.path('/reference/mutualFunds/' + mutualFund.id + '/quotes')

  $scope.load()
