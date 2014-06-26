'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:FinancialInstitutionNewCtrl
 # @description
 # # FinancialInstitutionNewCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'FinancialInstitutionNewCtrl', ($scope, $location, toaster, FinancialInstitution, Country) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-reference').addClass('active')

  $scope.financialInstitutionNew = new FinancialInstitution({})
  $scope.errors = []
  $scope.countries = Country.query()

  $scope.persist = ->
    $scope.financialInstitutionNew.$save({}
    , ->
      toaster.pop('success', '', 'Financial institution created')
      $location.path('/reference/financialInstitutions');
    , (httpResponse) ->
      $scope.errors = []
      for violation in httpResponse.data.parameterViolations
        fieldParts = violation["path"].split(".")
        fieldName = fieldParts[fieldParts.length-1]
        $scope.errors.push "The field '" + fieldName + "' is not valid: " + violation["message"]
    )
