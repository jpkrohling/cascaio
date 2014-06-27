'use strict'

describe 'Controller: ExchangeRatesCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  ExchangeRatesCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    ExchangeRatesCtrl = $controller 'ExchangeRatesCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
