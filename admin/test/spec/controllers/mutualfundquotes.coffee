'use strict'

describe 'Controller: MutualFundQuotesCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  MutualFundQuotesCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    MutualFundQuotesCtrl = $controller 'MutualFundQuotesCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
