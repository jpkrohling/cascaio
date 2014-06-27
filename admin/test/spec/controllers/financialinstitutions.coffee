'use strict'

describe 'Controller: FinancialInstitutionsCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  FinancialInstitutionsCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    FinancialInstitutionsCtrl = $controller 'FinancialInstitutionsCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
