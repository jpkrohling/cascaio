'use strict'

###*
 # @ngdoc service
 # @name adminApp.ExchangeRate
 # @description
 # # ExchangeRate
 # Service in the adminApp.
###
angular.module('adminApp').service 'ExchangeRate', ($resource, apiUrl) ->
  $resource(apiUrl + '/reference/exchangeRates/:id', {id:'@id'})
