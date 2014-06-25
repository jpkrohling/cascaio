'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:MutualfundnewCtrl
 # @description
 # # MutualfundnewCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'MutualFundNewCtrl', ($scope, $location, toaster, MutualFund, Currency) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-reference').addClass('active')

  $scope.mutualFundNew = new MutualFund({})
  $scope.errors = []
  $scope.currencies = Currency.query()

  $scope.persist = ->
    $scope.mutualFundNew.$save({}
    , ->
      toaster.pop('success', '', 'Mutual Fund created')
      $location.path('/reference/mutualFunds');
    , (httpResponse) ->
      $scope.errors = []
      toaster.pop('error', '', 'Invalid data, please check your input.')
      for violation in httpResponse.data.parameterViolations
        fieldParts = violation["path"].split(".")
        fieldName = fieldParts[fieldParts.length-1]
        $scope.errors.push "The field '" + fieldName + "' is not valid: " + violation["message"]
    )
