'use strict'

angular.module('frontendApp')
  .service 'SavingsAccount', ['$resource', ($resource) ->
    $resource('http://api.cascaio.com:8080/v1/user/savingsAccounts/:id')
  ]
