'use strict'

describe 'Controller: MutualFundAccountNewCtrl', ->

  # load the controller's module
  beforeEach module 'frontendApp'

  MutualFundAccountNewCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    MutualFundAccountNewCtrl = $controller 'MutualFundAccountNewCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
