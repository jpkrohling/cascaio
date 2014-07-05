'use strict'

###*
 # @ngdoc service
 # @name frontendApp.Currency
 # @description
 # # Currency
 # Service in the frontendApp.
###
angular.module('frontendApp').service 'Currency', ($resource, config) ->
  $resource(config.apihost + '/reference/currencies/:id', {id:'@id'})
