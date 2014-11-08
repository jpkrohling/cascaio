'use strict'

###*
 # @ngdoc service
 # @name frontendApp.MutualFund
 # @description
 # # MutualFund
 # Service in the frontendApp.
###
angular.module('frontendApp').service 'MutualFund', ($resource, config) ->
  $resource(config.apihost + '/reference/mutualFunds/:id', {id:'@id'})
