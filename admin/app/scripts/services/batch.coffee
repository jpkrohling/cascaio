'use strict'

###*
 # @ngdoc service
 # @name adminApp.Batch
 # @description
 # # Batch
 # Service in the adminApp.
###
angular.module('adminApp').service 'Batch', ($resource, config) ->
  $resource(config.apihost + '/batch')
