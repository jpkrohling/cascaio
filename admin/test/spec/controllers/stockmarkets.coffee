'use strict'

describe 'Controller: StockMarketsCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  StockmarketsCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    StockmarketsCtrl = $controller 'StockMarketsCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
