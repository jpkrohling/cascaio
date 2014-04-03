'use strict'

describe 'Controller: CheckingaccountnewCtrl', () ->

  # load the controller's module
  beforeEach module 'frontendApp'

  CheckingaccountnewCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CheckingaccountnewCtrl = $controller 'CheckingaccountnewCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', () ->
    expect(scope.awesomeThings.length).toBe 3
