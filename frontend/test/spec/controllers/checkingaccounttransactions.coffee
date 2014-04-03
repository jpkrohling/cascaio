'use strict'

describe 'Controller: CheckingaccounttransactionsCtrl', () ->

  # load the controller's module
  beforeEach module 'frontendApp'

  CheckingaccounttransactionsCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CheckingaccounttransactionsCtrl = $controller 'CheckingaccounttransactionsCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', () ->
    expect(scope.awesomeThings.length).toBe 3
