'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:MutualFundQuotesCtrl
 # @description
 # # MutualFundQuotesCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'MutualFundQuotesCtrl', ($scope, $filter, $routeParams, ngTableParams, MutualFund, MutualFundQuote) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-reference').addClass('active')

  $scope.mutualFund = {}
  $scope.quotes = []
  $scope.loading = false

  $scope.tableParams = new ngTableParams({page: 1, count: 25}, {
    getData: ($defer, params) ->
      data = $scope.quotes

      if params.filter()
        data = $filter('filter')(data, params.filter())

      params.total(data.length)
      $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()))
  })

  $scope.load = ->
    $scope.loading = true

    $scope.mutualFund = MutualFund.get({id: $routeParams.id}, ->
      $scope.quotes = MutualFundQuote.query({fundId:$scope.mutualFund.id}, ->
        $scope.loading = false
        $scope.tableParams.reload()
      )
    )

  $scope.load()
