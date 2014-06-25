'use strict'

describe 'Controller: ExchangeratesCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  ExchangeratesCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    ExchangeratesCtrl = $controller 'ExchangeratesCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
