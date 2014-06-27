'use strict'

describe 'Controller: MutualFundsCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  MutualFundsCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    MutualFundsCtrl = $controller 'MutualFundsCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
