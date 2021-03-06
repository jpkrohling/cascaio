'use strict'

###*
 # @ngdoc overview
 # @name frontendApp
 # @description
 # # frontendApp
 #
 # Main module of the application.
###
angular.module('frontendApp', [
  'ngAnimate',
  'ngCookies',
  'ngResource',
  'ngRoute',
  'ngSanitize',
  'ngTouch',
  'ngIdle',
  'ui.bootstrap',
  'toaster',
  'ui.select',
  'ngTable'
])
.config ($routeProvider, $idleProvider, $keepaliveProvider) ->
    $routeProvider
    .when '/',
        templateUrl: 'views/dashboard.html'
        controller: 'DashboardCtrl'
    .when '/dashboard',
      templateUrl: 'views/dashboard.html'
      controller: 'DashboardCtrl'

    .when '/checkingAccounts',
      templateUrl: 'views/checkingaccounts.html'
      controller: 'CheckingAccountsCtrl'
    .when '/checkingAccounts/new',
      templateUrl: 'views/checkingaccountnew.html'
      controller: 'CheckingAccountNewCtrl'

    .when '/checkingAccounts/:id',
      templateUrl: 'views/checkingaccountdetail.html'
      controller: 'CheckingAccountDetailCtrl'

    .when '/mutualFundAccounts',
      templateUrl: 'views/mutualfundaccounts.html'
      controller: 'MutualFundAccountsCtrl'
    .when '/mutualFundAccounts/new',
      templateUrl: 'views/mutualfundaccountnew.html'
      controller: 'MutualFundAccountNewCtrl'

    .when '/mutualFundAccounts/:id',
      templateUrl: 'views/mutualfundaccountdetail.html'
      controller: 'MutualFundAccountDetailCtrl'

    .otherwise
        redirectTo: '/'

    $idleProvider.idleDuration(60 * 5) # 5 minutes
    $idleProvider.warningDuration(60) # 1 minute
    $keepaliveProvider.interval(10)