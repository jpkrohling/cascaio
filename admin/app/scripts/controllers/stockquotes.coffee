'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:StockquotesCtrl
 # @description
 # # StockquotesCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'StockQuotesCtrl', ($scope, $filter, $routeParams, $location, ngTableParams, Stock, StockQuote) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-reference').addClass('active')

  $scope.stock = {}
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

    $scope.stock = Stock.get({id: $routeParams.id}, ->
      $scope.quotes = StockQuote.query({stockId:$scope.stock.id}, ->
        $scope.loading = false
        $scope.tableParams.reload()
      , -> $scope.loading = false
      )
    , ->
      $scope.loading = false
      $location.path('/reference/stocks')
    )

  $scope.load()
