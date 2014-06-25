'use strict'

###*
 # @ngdoc service
 # @name adminApp.Category
 # @description
 # # Category
 # Service in the adminApp.
###
angular.module('adminApp').service 'Category', ($resource, apiUrl) ->
  $resource(apiUrl + '/reference/categories/:id', {id:'@id'})
