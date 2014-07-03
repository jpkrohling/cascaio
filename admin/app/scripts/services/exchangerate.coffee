'use strict'

###*
 # @ngdoc service
 # @name adminApp.ExchangeRate
 # @description
 # # ExchangeRate
 # Service in the adminApp.
###
angular.module('adminApp').service 'ExchangeRate', ($resource, config) ->
  $resource(config.apihost + '/reference/exchangeRates/:id', {id:'@id'})
