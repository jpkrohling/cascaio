'use strict'

describe 'Controller: CheckingAccountNewCtrl', ->

  # load the controller's module
  beforeEach module 'frontendApp'

  CheckingAccountNewCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CheckingAccountNewCtrl = $controller 'CheckingAccountNewCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
