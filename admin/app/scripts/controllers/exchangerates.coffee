'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:ExchangeRatesCtrl
 # @description
 # # ExchangeRatesCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'ExchangeRatesCtrl', ($scope, $filter, toaster, ngTableParams, ExchangeRate, Currency) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-reference').addClass('active')

  $scope.currencies = Currency.query()
  $scope.loading = false
  $scope.exchangeRates = []
  $scope.currencyFrom = {}
  $scope.currencyTo = {}
  $scope.dateStart = ""
  $scope.dateEnd = ""

  $scope.validCurrencies = ->
    return $scope.currencyFrom.selected != "" && $scope.currencyTo.selected != "" && $scope.currencyFrom.selected != $scope.currencyTo.selected

  $scope.tableParams = new ngTableParams({page: 1, count: 25}, {
    getData: ($defer, params) ->
      data = $scope.exchangeRates

      if params.filter()
        data = $filter('filter')(data, params.filter())

      params.total(data.length)
      $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()))
  })

  $scope.load = ->
    if $scope.currencyFrom.selected.code == ""
      toaster.pop('warning', 'Missing data', 'Please, enter a currency to be converted from')
      return

    if $scope.currencyTo.selected.code == ""
      toaster.pop('warning', 'Missing data', 'Please, enter a currency to be converted to')
      return

    $scope.loading = true

    $scope.exchangeRates = ExchangeRate.query({
        currencyFrom: $scope.currencyFrom.selected.code,
        currencyTo: $scope.currencyTo.selected.code,
        dateStart: $scope.dateStart,
        dateEnd: $scope.dateEnd
      }
    , ->
      $scope.loading = false
      $scope.tableParams.reload()
    , -> $scope.loading = false
    )
