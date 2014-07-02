'use strict'

###*
 # @ngdoc service
 # @name adminApp.config
 # @description
 # # config
 # Factory in the adminApp.
###
angular.module('adminApp')
  .factory 'config', (environment) ->
    environment['@@environment']