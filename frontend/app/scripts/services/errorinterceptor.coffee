'use strict'

###*
 # @ngdoc service
 # @name frontendApp.ErrorInterceptor
 # @description
 # # ErrorInterceptor
 # Factory in the frontendApp.
###
angular.module('frontendApp').factory 'ErrorInterceptor', ($q, toaster) ->
  {
  requestError: (rejection) ->
    return $q.reject(rejection)

  responseError: (rejection) ->
    if (rejection.status == 0)
      toaster.pop('error', 'Error', 'The server seems to be offline. Try again later.')

    if (rejection.status == 400)
      if rejection.data.parameterViolations
        messages = []
        for violation in rejection.data.parameterViolations
          fieldParts = violation["path"].split(".")
          fieldName = fieldParts[fieldParts.length-1]
          messages.push "The field '" + fieldName + "' is not valid: " + violation["message"]
        toaster.pop('warning', 'Bad data', messages.join('<p>'), 5000, 'trustedHtml')
      else
        toaster.pop('warning', 'Bad data', "The server didn't like this data.")

    if (rejection.status == 404)
      toaster.pop('warning', 'Not found', 'The record you requested could not be found.')

    if (rejection.status == 500)
      toaster.pop('error', 'Error', 'Something went wrong on the server side. Message: ' + rejection.data)

    return $q.reject(rejection)
  }

angular.module('frontendApp').config ($httpProvider) -> $httpProvider.interceptors.push('ErrorInterceptor')
