'use strict'

describe 'Controller: StockMarketNewCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  StockMarketNewCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    StockMarketNewCtrl = $controller 'StockMarketNewCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
