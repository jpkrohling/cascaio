'use strict'

describe 'Controller: MutualFundAccountDetailCtrl', ->

  # load the controller's module
  beforeEach module 'frontendApp'

  MutualFundAccountDetailCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    MutualFundAccountDetailCtrl = $controller 'MutualFundAccountDetailCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
