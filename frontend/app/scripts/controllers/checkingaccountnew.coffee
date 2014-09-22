'use strict'

###*
 # @ngdoc function
 # @name frontendApp.controller:CheckingAccountNewCtrl
 # @description
 # # CheckingAccountNewCtrl
 # Controller of the frontendApp
###
angular.module('frontendApp').controller 'CheckingAccountNewCtrl', ($scope, $filter, $location, toaster, FinancialInstitution, Currency, CheckingAccount) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-checking-accounts').addClass('active')

  $scope.currency = {}
  $scope.financialInstitution = {}
  $scope.checkingAccountNew = new CheckingAccount({})
  $scope.errors = []
  $scope.financialInstitutions = FinancialInstitution.query()
  $scope.financialInstitutionResults = []
  $scope.currencies = Currency.query()

  $scope.persist = ->
    $scope.checkingAccountNew.financialInstitutionId = $scope.financialInstitution.selected.id
    $scope.checkingAccountNew.currencyUnit = $scope.currency.selected.code
    $scope.checkingAccountNew.$save({}
    , ->
      toaster.pop('success', '', 'Checking account created')
      $location.path('/checkingAccounts');
    , (httpResponse) ->
      $scope.errors = []
      for violation in httpResponse.data.parameterViolations
        fieldParts = violation["path"].split(".")
        fieldName = fieldParts[fieldParts.length-1]
        $scope.errors.push "The field '" + fieldName + "' is not valid: " + violation["message"]
    )

  $scope.refreshFinancialInstitutions = (input) ->
    data = {results: []}
    console.log("Searching...")
    if input.length < 3
      console.log("Input is too small for a search: #{input.length}")
      return

    filtered = $filter('filter')($scope.financialInstitutions, input)
    for result in filtered
      data.results.push(result)

    $scope.financialInstitutionResults = data.results
    return