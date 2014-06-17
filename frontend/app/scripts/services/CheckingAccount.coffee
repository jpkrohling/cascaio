'use strict'

angular.module('frontendApp')
.service 'CheckingAccount', ['$resource', ($resource) ->
    $resource('http://api.cascaio.com/v1/user/checkingAccounts/:id')
  ]
