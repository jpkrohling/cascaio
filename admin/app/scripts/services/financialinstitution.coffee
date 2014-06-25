'use strict'

###*
 # @ngdoc service
 # @name adminApp.FinancialInstitution
 # @description
 # # FinancialInstitution
 # Service in the adminApp.
###
angular.module('adminApp').service 'FinancialInstitution', ($resource) ->
  $resource('http://api.cascaio.com:8080/v1/reference/financialInstitutions/:id', {id:'@id'})
