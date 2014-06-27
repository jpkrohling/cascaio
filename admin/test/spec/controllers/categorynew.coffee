'use strict'

describe 'Controller: CategoryNewCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  CategoryNewCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CategoryNewCtrl = $controller 'CategoryNewCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
