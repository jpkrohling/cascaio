'use strict'

describe 'Controller: MutualFundNewCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  MutualFundNewCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    MutualFundNewCtrl = $controller 'MutualFundNewCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
