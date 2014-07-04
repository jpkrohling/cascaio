'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:StockMarketsCtrl
 # @description
 # # StockMarketsCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'StockMarketsCtrl', ($scope, $location, $filter, toaster, ngTableParams, StockMarket) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-reference').addClass('active')

  $scope.loading = false
  $scope.stockMarkets = []

  $scope.tableParams = new ngTableParams({page: 1, count: 25}, {
    getData: ($defer, params) ->
      data = $scope.stockMarkets

      if params.filter()
        data = $filter('filter')(data, params.filter())

      params.total(data.length)
      $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()))
  })

  $scope.showCreateForm = ->
    $location.path('/reference/stockMarkets/new')

  $scope.load = ->
    $scope.loading = true
    $scope.stockMarkets = StockMarket.query({}, ->
      $scope.loading = false
      $scope.tableParams.reload()
    , -> $scope.loading = false
    )

  $scope.load()
