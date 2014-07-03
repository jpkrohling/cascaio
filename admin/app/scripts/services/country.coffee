'use strict'

###*
 # @ngdoc service
 # @name adminApp.Country
 # @description
 # # Country
 # Service in the adminApp.
###
angular.module('adminApp').service 'Country', ($resource, config) ->
  $resource(config.apihost + '/reference/countries')
