'use strict'

angular.module('frontendApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ngRoute'
])
.config ['$routeProvider', ($routeProvider) ->
    $routeProvider
    .when '/',
        templateUrl: 'views/main.html'
        controller: 'MainCtrl'
    .when '/checkingAccount/new',
        templateUrl: 'views/checking/checkingaccountnew.html'
        controller: 'CheckingAccountNewCtrl'
    .when '/checkingAccount/:accountId',
        templateUrl: 'views/checking/checkingaccounttransactions.html'
        controller: 'CheckingAccountTransactionsCtrl'
    .when '/savingsAccount/new',
        templateUrl: 'views/savings/savingsaccountnew.html'
        controller: 'SavingsAccountNewCtrl'
    .when '/savingsAccount/:accountId',
        templateUrl: 'views/savings/savingsaccounttransactions.html'
        controller: 'SavingsAccountTransactionsCtrl'
    .otherwise
        redirectTo: '/'
  ]

