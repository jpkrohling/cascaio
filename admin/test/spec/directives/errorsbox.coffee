'use strict'

describe 'Directive: errorsBox', ->

  # load the directive's module
  beforeEach module 'adminApp'

  scope = {}

  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()

  it 'should make hidden element visible', inject ($compile) ->
    element = angular.element '<errors-box></errors-box>'
    element = $compile(element) scope
    expect(element.text()).toBe 'this is the errorsBox directive'
