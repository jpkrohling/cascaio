'use strict'

describe 'Controller: MutualFundAccountsCtrl', ->

  # load the controller's module
  beforeEach module 'frontendApp'

  MutualFundAccountsCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    MutualFundAccountsCtrl = $controller 'MutualFundAccountsCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
