'use strict'

###*
 # @ngdoc service
 # @name frontendApp.CheckingAccount
 # @description
 # # CheckingAccount
 # Service in the frontendApp.
###
angular.module('frontendApp').service 'CheckingAccount', ($resource, config) ->
  $resource(config.apihost + '/user/checkingAccounts/:id', {id:'@id'})
