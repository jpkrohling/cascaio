'use strict'

describe 'Controller: DashboardCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  DashboardCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    DashboardCtrl = $controller 'DashboardCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
