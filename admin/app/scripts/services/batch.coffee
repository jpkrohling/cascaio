'use strict'

###*
 # @ngdoc service
 # @name adminApp.Batch
 # @description
 # # Batch
 # Service in the adminApp.
###
angular.module('adminApp').service 'Batch', ($resource) ->
  $resource('http://api.cascaio.com:8080/v1/batch')
