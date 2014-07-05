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

  $scope.checkingAccountNew = new CheckingAccount({})
  $scope.errors = []
  $scope.financialInstitutions = FinancialInstitution.query()
  $scope.currencies = Currency.query()

  $scope.persist = ->
    $scope.checkingAccountNew.financialInstitutionId = $("#financialInstitutionId").select2("val")
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

  $('#financialInstitutionId').select2({
    minimumInputLength: 3
    query: (q) ->
      data = {results: []}
      filtered = $filter('filter')($scope.financialInstitutions, q.term)
      for result in filtered
        data.results.push({id: result.id, text: "#{result.bankleitzahl} - #{result.name}"})
      q.callback(data)
      return
  })