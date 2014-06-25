'use strict'

###*
 # @ngdoc overview
 # @name adminApp
 # @description
 # # adminApp
 #
 # Main module of the application.
###
angular.module('adminApp', [
  'ngAnimate',
  'ngCookies',
  'ngResource',
  'ngRoute',
  'ngSanitize',
  'ngTouch',
  'ngIdle',
  'ui.bootstrap',
  'toaster',
  'ui.select2',
  'ngTable'
])
.config ($routeProvider, $idleProvider, $keepaliveProvider) ->
    $routeProvider
    .when '/',
        templateUrl: 'views/main.html'
        controller: 'MainCtrl'

    .when '/reference',
        templateUrl: 'views/reference/categories.html'
        controller: 'CategoriesCtrl'

    .when '/reference/financialInstitutions',
        templateUrl: 'views/reference/financialinstitutions.html'
        controller: 'FinancialInstitutionsCtrl'
    .when '/reference/financialInstitutions/new',
        templateUrl: 'views/reference/financialinstitutionnew.html'
        controller: 'FinancialInstitutionNewCtrl'

    .when '/reference/categories',
        templateUrl: 'views/reference/categories.html'
        controller: 'CategoriesCtrl'
    .when '/reference/categories/new',
        templateUrl: 'views/reference/categorynew.html'
        controller: 'CategoryNewCtrl'

    .when '/reference/exchangeRates',
        templateUrl: 'views/reference/exchangerates.html'
        controller: 'ExchangeRatesCtrl'

    .when '/reference/mutualFunds',
        templateUrl: 'views/reference/mutualfunds.html'
        controller: 'MutualFundsCtrl'
    .when '/reference/mutualFunds/new',
        templateUrl: 'views/reference/mutualfundnew.html'
        controller: 'MutualFundNewCtrl'

    .when '/batch',
        templateUrl: 'views/batch.html'
        controller: 'BatchCtrl'

    .otherwise
        redirectTo: '/'

    $idleProvider.idleDuration(60 * 5) # 5 minutes
    $idleProvider.warningDuration(60) # 1 minute
    $keepaliveProvider.interval(10)