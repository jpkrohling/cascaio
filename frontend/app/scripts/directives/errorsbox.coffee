'use strict'

###*
 # @ngdoc directive
 # @name frontendApp.directive:casErrorsBox
 # @description
 # # casErrorsBox
###
angular.module('frontendApp').directive('casErrorsBox', ->
  templateUrl: 'views/directives/errorsbox.html'
  restrict: 'A'
  scope: {
    errors: '='
  }
)
