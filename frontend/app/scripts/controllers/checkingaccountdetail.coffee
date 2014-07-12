'use strict'

###*
 # @ngdoc function
 # @name frontendApp.controller:CheckingAccountDetailCtrl
 # @description
 # # CheckingAccountDetailCtrl
 # Controller of the frontendApp
###
angular.module('frontendApp').controller 'CheckingAccountDetailCtrl', ($scope, $routeParams, $modal, $filter, $location, toaster, ngTableParams, CheckingAccount, CheckingAccountTransaction) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-checking-accounts').addClass('active')

  $scope.loading = false
  $scope.checkingAccount = {}
  $scope.transactions = []
  $scope.transactionNew = new CheckingAccountTransaction({})

  $scope.tableParams = new ngTableParams({page: 1, count: 25}, {
    getData: ($defer, params) ->
      data = $scope.transactions

      if params.filter()
        data = $filter('filter')(data, params.filter())

      params.total(data.length)
      $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()))
  })

  $scope.load = ->
    $scope.loading = true
    $scope.checkingAccount = CheckingAccount.get({id: $routeParams.id}, ->
      $scope.transactions = CheckingAccountTransaction.query({accountId: $scope.checkingAccount.id}, ->
        $scope.loading = false
        $scope.tableParams.reload()
      , -> $scope.loading = false
      )
    , ->
      console.log('failed to load checking account')
    )

  $scope.removeAccount = ->
    angular.element('#confirmDeleteModal').on('hidden.bs.modal', ->
      $scope.checkingAccount.$delete({}, ->
        toaster.pop('success', 'Deleted', 'Account and related transactions deleted')
        $location.path('/checkingAccounts')
      , ->
        toaster.pop('error', 'Not deleted', 'There was an error while trying to delete this account.')
      )
    )
    angular.element('#confirmDeleteModal').modal('hide')
    return

  $scope.createTransaction = ->
    $scope.transactionNew.accountId = $scope.checkingAccount.id
    $scope.transactionNew.$save({}, ->
      toaster.pop('success', 'Created', 'Transaction created')
      angular.element('#createTransactionModal').modal('hide')
      $scope.transactions.push $scope.transactionNew
      $scope.transactionNew = new CheckingAccountTransaction({})
      $scope.tableParams.reload()
    , (httpResponse) ->
      toaster.pop('error', 'Not created', 'There was an error while trying to create this account.')
      $scope.transactionErrors = []
      if httpResponse.data?.parameterViolations
        for violation in httpResponse.data.parameterViolations
          fieldParts = violation["path"].split(".")
          fieldName = fieldParts[fieldParts.length-1]
          $scope.transactionErrors.push "The field '" + fieldName + "' is not valid: " + violation["message"]
    )
    return

  $scope.load()
