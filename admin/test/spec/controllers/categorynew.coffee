'use strict'

describe 'Controller: CategorynewCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  CategorynewCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CategorynewCtrl = $controller 'CategorynewCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
