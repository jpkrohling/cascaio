'use strict'

###*
 # @ngdoc service
 # @name adminApp.StockMarket
 # @description
 # # StockMarket
 # Service in the adminApp.
###
angular.module('adminApp').service 'StockMarket', ($resource, config) ->
    $resource(config.apihost + '/reference/stockMarkets/:id', {id:'@id'})
