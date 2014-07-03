'use strict'

###*
 # @ngdoc service
 # @name adminApp.Category
 # @description
 # # Category
 # Service in the adminApp.
###
angular.module('adminApp').service 'Category', ($resource, config) ->
  $resource(config.apihost + '/reference/categories/:id', {id:'@id'})
