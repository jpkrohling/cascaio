'use strict'

describe 'Controller: FinancialInstitutionNewCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  FinancialInstitutionNewCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    FinancialInstitutionNewCtrl = $controller 'FinancialInstitutionNewCtrl', {
      $scope: scope
    }

  it 'is not a valid test yet', ->
    console.log("not implemented")
