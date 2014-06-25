'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:FinancialInstitutionCtrl
 # @description
 # # FinancialInstitutionCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'FinancialInstitutionsCtrl', ($scope, toaster, $http, $filter, $location, ngTableParams, FinancialInstitution) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-reference').addClass('active')

  $scope.loading = false
  $scope.financialInstitutions = []
  $scope.bundesbankFileUrl = ''

  $scope.tableParams = new ngTableParams({page: 1, count: 25}, {
    getData: ($defer, params) ->
      data = $scope.financialInstitutions

      if params.filter()
        data = $filter('filter')(data, params.filter())

      params.total(data.length)
      $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()))
  })

  $scope.load = ->
    $scope.loading = true
    $scope.financialInstitutions = FinancialInstitution.query({}, ->
      $scope.loading = false
      $scope.tableParams.reload()
    )

  $scope.importFileBundesbank = ->
    $('#bundesbankFileUrlModal').modal('hide')
    toaster.pop('info', 'Action in background', 'Import is running on the background.')
    console.log("Location: " + $scope.bundesbankFileUrl)
    $http.post('http://api.cascaio.com:8080/v1/reference/financialInstitutions/import/bundesbank', {location: $scope.bundesbankFileUrl})
    .success((data, status, headers, config) ->
        toaster.pop('success', 'Finished', 'Import of the Bundesbank file finished. Success: ' + data["success"] + ", skipped: " + data["skipped"])
        $scope.load()
      )
    .error( (data) ->
        toaster.pop('error', 'Failed', 'Import of the Bundesbank file failed.' + data["errorMessage"] )
      )

  $scope.showCreateForm = ->
    $location.path('/reference/financialInstitutions/new')

  $scope.remove = (financialInstitution) ->
    financialInstitution.$remove().then(->
      toaster.pop('success', '', 'Financial institution removed')
      $scope.financialInstitutions.splice( $scope.financialInstitutions.indexOf(financialInstitution), 1 );
      $scope.tableParams.reload()
    )

  $scope.load()
