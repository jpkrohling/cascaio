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

  $scope.confirmRemoveTransaction = (transaction) ->
    modal = $modal.open({
      templateUrl: 'views/_checkingaccountdetails-removetransactionmodal.html'
      resolve: {
        transaction: transaction
      }
    })

    modal.result.then(->
      console.log('removing transaction')
    , ->
      console.log('dismissing removal')
    )

  $scope.load()
