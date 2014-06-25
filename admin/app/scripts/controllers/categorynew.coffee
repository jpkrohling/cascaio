'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:CategoryNewCtrl
 # @description
 # # CategoryNewCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'CategoryNewCtrl', ($scope, $location, toaster, Category) ->
  $scope.categoryNew = new Category({})
  $scope.errors = []
  $scope.categories = Category.query()

  $scope.persist = ->
    $scope.categoryNew.$save({}
    , ->
      toaster.pop('success', '', 'Category created')
      $location.path('/reference/categories');
    , (httpResponse) ->
      $scope.errors = []
      toaster.pop('error', '', 'Invalid data, please check your input.')
      for violation in httpResponse.data.parameterViolations
        fieldParts = violation["path"].split(".")
        fieldName = fieldParts[fieldParts.length-1]
        $scope.errors.push "The field '" + fieldName + "' is not valid: " + violation["message"]
    )
