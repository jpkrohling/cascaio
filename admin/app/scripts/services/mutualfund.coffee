'use strict'

###*
 # @ngdoc service
 # @name adminApp.MutualFund
 # @description
 # # MutualFund
 # Service in the adminApp.
###
angular.module('adminApp').service 'MutualFund', ($resource, apiUrl) ->
  $resource(apiUrl + '/reference/mutualFunds/:id', {id:'@id'})
