'use strict'

###*
 # @ngdoc service
 # @name adminApp.Country
 # @description
 # # Country
 # Service in the adminApp.
###
angular.module('adminApp').service 'Country', ($resource, apiUrl) ->
  $resource(apiUrl + '/reference/countries')
