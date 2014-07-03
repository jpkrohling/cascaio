'use strict'

###*
 # @ngdoc service
 # @name adminApp.MutualFund
 # @description
 # # MutualFund
 # Service in the adminApp.
###
angular.module('adminApp').service 'MutualFund', ($resource, config) ->
  $resource(config.apihost + '/reference/mutualFunds/:id', {id:'@id'})
