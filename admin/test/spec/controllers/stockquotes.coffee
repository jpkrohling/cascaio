'use strict'

describe 'Controller: StockQuotesCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  StockQuotesCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    StockQuotesCtrl = $controller 'StockQuotesCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
