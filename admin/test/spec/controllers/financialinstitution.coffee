'use strict'

describe 'Controller: FinancialinstitutionCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  FinancialinstitutionCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    FinancialinstitutionCtrl = $controller 'FinancialinstitutionCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
