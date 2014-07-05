'use strict'

###*
 # @ngdoc service
 # @name frontendApp.FinancialInstitution
 # @description
 # # FinancialInstitution
 # Service in the frontendApp.
###
angular.module('frontendApp').service 'FinancialInstitution', ($resource, config) ->
  $resource(config.apihost + '/reference/financialInstitutions/:id', {id:'@id'})
