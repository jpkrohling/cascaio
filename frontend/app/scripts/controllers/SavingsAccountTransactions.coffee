'use strict'

angular.module('frontendApp')
  .controller 'SavingsAccountTransactionsCtrl', ['$scope', '$routeParams', 'SavingsAccount', ($scope, $routeParams, SavingsAccount) ->
    console.log("Loading savings account with ID " + $routeParams.accountId)
    $scope.savingsAccount = SavingsAccount.get({id: $routeParams.accountId})
  ]