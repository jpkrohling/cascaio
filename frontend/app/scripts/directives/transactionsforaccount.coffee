'use strict'

###*
 # @ngdoc directive
 # @name frontendApp.directive:transactionsForAccount
 # @description
 # # transactionsForAccount
###
angular.module('frontendApp').directive('casTransactionsForAccount', ->
  templateUrl: 'views/directives/transactionsforaccount.html'
  restrict: 'A'
  scope: {
    account: '='
  }
  controller: ($scope, $modal, toaster, CheckingAccountTransaction) ->
    $scope.transactions = []
    $scope.loading = false
    $scope.transactionToRemove = {}

    $scope.modalController = ($scope, $modalInstance, transaction) ->
      $scope.transaction = transaction
      $scope.cancel = ->
        $modalInstance.dismiss('cancel')
      $scope.ok = ->
        $modalInstance.close('remove')

    $scope.confirmRemoveTransaction = (transaction) ->
      modal = $modal.open({
        templateUrl: 'views/_checkingaccountdetails-removetransactionmodal.html'
        resolve: {
          transaction: -> transaction
        }
        controller: $scope.modalController
      })

      modal.result.then(->
        transaction.$remove(->
          $scope.transactions.splice( $scope.transactions.indexOf(transaction), 1 );
          toaster.pop('success', 'Transaction removed', 'The selected transaction was removed')
        , ->
          toaster.pop('error', 'Transaction not removed', 'The selected transaction could not be removed due to an error.')
        )
      )

    $scope.load = ->
      $scope.loading = true
      $scope.transactions = CheckingAccountTransaction.query({accountId: $scope.account.id}, ->
        $scope.loading = false
      , -> $scope.loading = false
      )

    $scope.load()
)
