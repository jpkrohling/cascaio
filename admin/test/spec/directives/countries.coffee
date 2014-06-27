'use strict'

describe 'Directive: countries', ->

  # load the directive's module
  beforeEach module 'adminApp'

  scope = {}

  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()

  it 'is not a valid test', inject ($compile) ->
    element = angular.element '<countries></countries>'
    element = $compile(element) scope
