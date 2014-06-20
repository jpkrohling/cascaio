'use strict'

###*
 # @ngdoc overview
 # @name adminApp
 # @description
 # # adminApp
 #
 # Main module of the application.
###
angular
.module('adminApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngIdle',
    'ui.bootstrap'
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
        templateUrl: 'views/reference/financialinstitution.html'
        controller: 'FinancialInstitutionCtrl'
    .when '/reference/financialInstitutions/new',
        templateUrl: 'views/reference/financialinstitutionnew.html'
        controller: 'FinancialInstitutionNewCtrl'

    .when '/reference/categories',
        templateUrl: 'views/reference/categories.html'
        controller: 'CategoriesCtrl'

    .when '/batch',
      templateUrl: 'views/batch.html'
      controller: 'BatchCtrl'

    .otherwise
        redirectTo: '/'

    $idleProvider.idleDuration(60 * 5) # 5 minutes
    $idleProvider.warningDuration(60) # 1 minute
    $keepaliveProvider.interval(10)