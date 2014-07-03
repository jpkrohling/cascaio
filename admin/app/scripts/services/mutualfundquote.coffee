'use strict'

###*
 # @ngdoc service
 # @name adminApp.MutualFundQuote
 # @description
 # # MutualFundQuote
 # Service in the adminApp.
###
angular.module('adminApp').service 'MutualFundQuote', ($resource, config) ->
  $resource(config.apihost + '/reference/mutualFunds/:fundId/quotes/:id', {fundId:'@fundId', id:'@id'})
