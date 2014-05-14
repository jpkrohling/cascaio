'use strict'

describe 'Controller: SavingsaccountnewCtrl', () ->

  # load the controller's module
  beforeEach module 'frontendApp'

  SavingsaccountnewCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    SavingsaccountnewCtrl = $controller 'SavingsaccountnewCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', () ->
    expect(scope.awesomeThings.length).toBe 3
