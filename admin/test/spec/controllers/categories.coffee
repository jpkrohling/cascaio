'use strict'

describe 'Controller: CategoriesCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  CategoriesCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CategoriesCtrl = $controller 'CategoriesCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
