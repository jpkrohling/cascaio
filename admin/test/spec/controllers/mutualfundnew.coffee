'use strict'

describe 'Controller: MutualfundnewCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  MutualfundnewCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    MutualfundnewCtrl = $controller 'MutualfundnewCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
