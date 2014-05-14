'use strict'

angular.module('frontendApp')
  .controller 'MainCtrl', ['$rootScope', '$scope', 'CheckingAccount', 'SavingsAccount', ($rootScope, $scope, CheckingAccount, SavingsAccount) ->
    $rootScope.checkingAccounts = CheckingAccount.query()
    $rootScope.savingsAccounts = SavingsAccount.query()
  ]
