'use strict'

###*
 # @ngdoc service
 # @name adminApp.Country
 # @description
 # # Country
 # Service in the adminApp.
###
angular.module('adminApp').service 'Country', ($resource) ->
  $resource('http://api.cascaio.com:8080/v1/reference/countries')
