'use strict'

###*
 # @ngdoc service
 # @name frontendApp.MutualFundAccount
 # @description
 # # MutualFundAccount
 # Service in the frontendApp.
###
angular.module('frontendApp').service 'MutualFundAccount', ($resource, config) ->
  $resource(config.apihost + '/user/mutualFundAccounts/:id', {id:'@id'})
