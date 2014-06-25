'use strict'

###*
 # @ngdoc service
 # @name adminApp.FinancialInstitution
 # @description
 # # FinancialInstitution
 # Service in the adminApp.
###
angular.module('adminApp').service 'FinancialInstitution', ($resource, apiUrl) ->
  $resource(apiUrl + '/reference/financialInstitutions/:id', {id:'@id'})
