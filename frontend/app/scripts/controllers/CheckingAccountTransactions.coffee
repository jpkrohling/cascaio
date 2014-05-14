'use strict'

angular.module('frontendApp')
  .controller 'CheckingAccountTransactionsCtrl', ['$scope', '$routeParams', 'CheckingAccount', ($scope, $routeParams, CheckingAccount) ->
    console.log("Loading checking account with ID " + $routeParams.accountId)
    $scope.checkingAccount = CheckingAccount.get({id: $routeParams.accountId})
  ]