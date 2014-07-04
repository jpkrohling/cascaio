'use strict'

###*
 # @ngdoc service
 # @name adminApp.Stock
 # @description
 # # Stock
 # Service in the adminApp.
###
angular.module('adminApp').service 'Stock', ($resource, config) ->
    $resource(config.apihost + '/reference/stocks/:id', {id:'@id'})
