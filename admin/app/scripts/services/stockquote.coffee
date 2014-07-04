'use strict'

###*
 # @ngdoc service
 # @name adminApp.Stockquote
 # @description
 # # Stockquote
 # Service in the adminApp.
###
angular.module('adminApp').service 'StockQuote', ($resource, config) ->
  $resource(config.apihost + '/reference/stocks/:stockId/quotes/:id', {stockId:'@stockId', id:'@id'})
