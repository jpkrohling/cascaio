'use strict'

describe 'Controller: CheckingaccountupdateCtrl', () ->

  # load the controller's module
  beforeEach module 'frontendApp'

  CheckingaccountupdateCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CheckingaccountupdateCtrl = $controller 'CheckingaccountupdateCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', () ->
    expect(scope.awesomeThings.length).toBe 3
