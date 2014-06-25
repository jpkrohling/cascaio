'use strict'

describe 'Controller: FinancialinstitutionsCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  FinancialinstitutionsCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    FinancialinstitutionCtrl = $controller 'FinancialinstitutionsCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
