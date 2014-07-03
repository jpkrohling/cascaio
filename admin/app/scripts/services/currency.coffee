'use strict'

###*
 # @ngdoc service
 # @name adminApp.Currency
 # @description
 # # Currency
 # Service in the adminApp.
###
angular.module('adminApp').service 'Currency', ($resource, config) ->
  $resource(config.apihost + '/reference/currencies/:id', {id:'@id'})
