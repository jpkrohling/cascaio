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
      controller: 'CheckingaccounttransactionsCtrl'
    .when '/checkingAccount/:accountId/update',
      templateUrl: 'views/checking/checkingaccountupdate.html'
      controller: 'CheckingaccountupdateCtrl'
    .otherwise
        redirectTo: '/'
  ]

