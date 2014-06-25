'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:FinancialInstitutionCtrl
 # @description
 # # FinancialInstitutionCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'FinancialInstitutionCtrl', ($scope, toaster, $http, $filter, ngTableParams, FinancialInstitution) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-reference').addClass('active')

  bundesbankFileLocation = 'http://www.bundesbank.de/Redaktion/DE/Downloads/Aufgaben/Unbarer_Zahlungsverkehr/Bankleitzahlen/2014_09_07/blz_2014_06_09_txt.txt?__blob=publicationFile'

  $scope.financialInstitutions = []

  $scope.tableParams = new ngTableParams({page: 1, count: 25}, {
    getData: ($defer, params) ->
      data = $scope.financialInstitutions

      if params.filter()
        data = $filter('filter')(data, params.filter())

      params.total(data.length)
      $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()))
  })

  $scope.load = ->
    $scope.financialInstitutions = FinancialInstitution.query({}, ->
      $scope.tableParams.reload()
    )

  $scope.importFileBundesbank = ->
    toaster.pop('info', 'Action in background', 'Import is running on the background.')
    $http.post('http://api.cascaio.com:8080/v1/reference/financialInstitutions/import/bundesbank', {location: bundesbankFileLocation})
    .success((data, status, headers, config) ->
        toaster.pop('success', 'Finished', 'Import of the Bundesbank file finished. Success: ' + data["success"] + ", skipped: " + data["skipped"])
        $scope.load()
      )
    .error( (data) ->
        toaster.pop('error', 'Failed', 'Import of the Bundesbank file failed.' + data["errorMessage"] )
      )

  $scope.remove = (financialInstitution) ->
    financialInstitution.$remove().then(->
      toaster.pop('success', '', 'Financial institution removed')
      $scope.financialInstitutions.splice( $scope.financialInstitutions.indexOf(financialInstitution), 1 );
      $scope.tableParams.reload()
    )

  $scope.load()
