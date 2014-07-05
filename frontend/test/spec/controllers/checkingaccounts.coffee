'use strict'

describe 'Controller: CheckingAccountsCtrl', ->

  # load the controller's module
  beforeEach module 'frontendApp'

  CheckingAccountsCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CheckingAccountsCtrl = $controller 'CheckingAccountsCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
