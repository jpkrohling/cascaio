'use strict'

angular.module('frontendApp')
  .service 'CheckingAccount', ['$resource', ($resource) ->
    $resource('http://api.cascaio.com:8080/v1/user/checkingAccounts')
  ]
