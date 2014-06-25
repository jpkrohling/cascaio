'use strict'

describe 'Directive: referenceDataMenu', ->

  # load the directive's module
  beforeEach module 'adminApp'

  scope = {}

  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()

  it 'should make hidden element visible', inject ($compile) ->
    element = angular.element '<reference-data-menu></reference-data-menu>'
    element = $compile(element) scope
    expect(element.text()).toBe 'this is the referenceDataMenu directive'
