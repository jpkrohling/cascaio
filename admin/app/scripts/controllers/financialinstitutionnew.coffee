'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:FinancialInstitutionNewCtrl
 # @description
 # # FinancialInstitutionNewCtrl
 # Controller of the adminApp
###
angular.module('adminApp')
.controller 'FinancialInstitutionNewCtrl', ['$scope', 'FinancialInstitution', ($scope, FinancialInstitution) ->
    sidebar()

    $scope.financialInstitutionNew = new FinancialInstitution({})
    $scope.errors = []
    $scope.persist = ->
      console.log("Persisting financial institution")
      console.log($scope.financialInstitutionNew)
      $scope.financialInstitutionNew.$save({}
      , ->
        console.log("Success")
      , (httpResponse) ->
        console.log(httpResponse.data.parameterViolations)
        $scope.errors = []
        for violation in httpResponse.data.parameterViolations
          fieldParts = violation["path"].split(".")
          fieldName = fieldParts[fieldParts.length-1]
          $scope.errors.push "The field '" + fieldName + "' is not valid: " + violation["message"]
      ).then((financialInstitution) ->
        console.log("Persisted")
      )

  ]