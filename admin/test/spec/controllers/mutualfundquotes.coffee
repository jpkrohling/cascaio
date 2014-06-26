'use strict'

describe 'Controller: MutualfundquotesCtrl', ->

  # load the controller's module
  beforeEach module 'adminApp'

  MutualfundquotesCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    MutualfundquotesCtrl = $controller 'MutualfundquotesCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
