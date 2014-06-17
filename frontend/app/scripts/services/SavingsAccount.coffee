'use strict'

angular.module('frontendApp')
.service 'SavingsAccount', ['$resource', ($resource) ->
    $resource('http://api.cascaio.com/v1/user/savingsAccounts/:id')
  ]
