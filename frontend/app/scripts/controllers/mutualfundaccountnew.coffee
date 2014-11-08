'use strict'

###*
 # @ngdoc function
 # @name frontendApp.controller:MutualFundAccountNewCtrl
 # @description
 # # MutualFundAccountNewCtrl
 # Controller of the frontendApp
###
angular.module('frontendApp').controller 'MutualFundAccountNewCtrl', ($scope, $filter, $location, toaster, MutualFund, MutualFundAccount) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-mutual-fund-accounts').addClass('active')

  $scope.mutualFund = {}
  $scope.mutualFundAccountNew = new MutualFundAccount({})
  $scope.errors = []
  $scope.mutualFunds = MutualFund.query()
  $scope.mutualFundResults = []

  $scope.persist = ->
    $scope.mutualFundAccountNew.mutualFundId = $scope.mutualFund.selected.id
    $scope.mutualFundAccountNew.$save({}
    , ->
      toaster.pop('success', '', 'Mutual fund account created')
      $location.path('/mutualFundAccounts');
    , (httpResponse) ->
      $scope.errors = []
      for violation in httpResponse.data.parameterViolations
        fieldParts = violation["path"].split(".")
        fieldName = fieldParts[fieldParts.length-1]
        $scope.errors.push "The field '" + fieldName + "' is not valid: " + violation["message"]
    )

  $scope.refreshMutualFunds = (input) ->
    data = {results: []}
    console.log("Searching...")
    if input.length < 3
      console.log("Input is too small for a search: #{input.length}")
      return

    filtered = $filter('filter')($scope.mutualFunds, input)
    for result in filtered
      data.results.push(result)

    $scope.mutualFundResults = data.results
    return
