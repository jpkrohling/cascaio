'use strict'

###*
 # @ngdoc service
 # @name adminApp.environment
 # @description
 # # environment
 # Constant in the adminApp.
###
angular.module('adminApp')
  .constant 'environment', 
  {
    development: {
      apihost: 'http://dev.api.cascaio.com:8080/v1'
    }
    
    production: {
      apihost: 'https://api.cascaio.com/v1'
    }
  }
  