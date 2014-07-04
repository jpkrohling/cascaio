'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:StockMarketNewCtrl
 # @description
 # # StockMarketNewCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'StockMarketNewCtrl', ($scope, $location, toaster, StockMarket, Currency) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-reference').addClass('active')

  $scope.stockMarketNew = new StockMarket({})
  $scope.errors = []

  $scope.persist = ->
    $scope.stockMarketNew.$save({}
    , ->
      toaster.pop('success', '', 'Stock Market created')
      $location.path('/reference/stockMarkets');
    , (httpResponse) ->
      $scope.errors = []
      toaster.pop('error', '', 'Invalid data, please check your input.')
      for violation in httpResponse.data.parameterViolations
        fieldParts = violation["path"].split(".")
        fieldName = fieldParts[fieldParts.length-1]
        $scope.errors.push "The field '" + fieldName + "' is not valid: " + violation["message"]
    )
