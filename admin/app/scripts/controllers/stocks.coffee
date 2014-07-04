'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:StocksCtrl
 # @description
 # # StocksCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'StocksCtrl', ($scope, $filter, $location, toaster, ngTableParams, Stock) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-reference').addClass('active')

  $scope.loading = false
  $scope.stocks = []

  $scope.tableParams = new ngTableParams({page: 1, count: 25}, {
    getData: ($defer, params) ->
      data = $scope.stocks

      if params.filter()
        data = $filter('filter')(data, params.filter())

      params.total(data.length)
      $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()))
  })

  $scope.load = ->
    $scope.loading = true
    $scope.stocks = Stock.query({}, ->
      $scope.loading = false
      $scope.tableParams.reload()
    , -> $scope.loading = false
    )

  $scope.load()
