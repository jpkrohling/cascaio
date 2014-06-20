'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:FinancialInstitutionCtrl
 # @description
 # # FinancialInstitutionCtrl
 # Controller of the adminApp
###
angular.module 'adminApp'
.controller 'FinancialInstitutionCtrl', ['$scope', 'FinancialInstitution', ($scope, FinancialInstitution) ->
    $('#main-nav li').removeClass('active')
    $('#main-nav-reference').addClass('active')
    sidebar()

    $scope.financialInstitutions = []
    $scope.load = ->
      $scope.financialInstitutions = FinancialInstitution.query()

    $scope.load()
  ]