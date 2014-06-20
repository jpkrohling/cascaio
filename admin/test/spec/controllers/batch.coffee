'use strict'

describe 'Controller: BatchCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  BatchCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    BatchCtrl = $controller 'BatchCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
