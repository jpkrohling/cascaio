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

  it 'is not a valid test yet', ->
    console.log("not implemented")
