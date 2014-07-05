'use strict'

###*
 # @ngdoc service
 # @name frontendApp.CheckingAccountTransaction
 # @description
 # # CheckingAccountTransaction
 # Service in the frontendApp.
###
angular.module('frontendApp').service 'CheckingAccountTransaction', ($resource, config) ->
  $resource(config.apihost + '/user/checkingAccounts/:accountId/transactions/:id', {accountId:'@accountId', id:'@id'})
