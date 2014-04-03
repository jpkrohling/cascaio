'use strict'

angular.module('frontendApp')
  .service 'FinancialInstitution', ['$resource', ($resource) ->
      $resource('http://api.cascaio.com:8080/v1/reference/financialInstitutions')
    ]
