'use strict'

describe 'Controller: MutualfundsCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  MutualfundsCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    MutualfundsCtrl = $controller 'MutualfundsCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
