'use strict'

###*
 # @ngdoc service
 # @name adminApp.Batch
 # @description
 # # Batch
 # Service in the adminApp.
###
angular.module('adminApp').service 'Batch', ($resource, apiUrl) ->
  $resource(apiUrl + '/batch')
