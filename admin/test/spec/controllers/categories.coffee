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

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
