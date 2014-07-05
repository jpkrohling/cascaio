'use strict'

describe 'Controller: CheckingAccountDetailCtrl', ->

  # load the controller's module
  beforeEach module 'frontendApp'

  CheckingAccountDetailCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CheckingAccountDetailCtrl = $controller 'CheckingAccountDetailCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
