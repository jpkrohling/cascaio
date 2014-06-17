'use strict'

angular.module('frontendApp')
.service 'FinancialInstitution', ['$resource', ($resource) ->
    $resource('http://api.cascaio.com/v1/reference/financialInstitutions')
  ]
