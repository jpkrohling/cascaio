'use strict'

###*
 # @ngdoc service
 # @name adminApp.FinancialInstitution
 # @description
 # # FinancialInstitution
 # Service in the adminApp.
###
angular.module('adminApp').service 'FinancialInstitution', ($resource, config) ->
  $resource(config.apihost + '/reference/financialInstitutions/:id', {id:'@id'})
