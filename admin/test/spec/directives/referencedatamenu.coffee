'use strict'

describe 'Directive: casReferenceDataMenu', ->

  # load the directive's module
  beforeEach module 'adminApp'

  scope = {}

  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()

  it 'is not a valid test', inject ($compile) ->
    element = angular.element '<reference-data-menu></reference-data-menu>'
    element = $compile(element) scope
