'use strict'

describe 'Controller: StocksCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  StocksCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    StocksCtrl = $controller 'StocksCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
