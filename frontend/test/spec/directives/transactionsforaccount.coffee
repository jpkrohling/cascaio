'use strict'

describe 'Directive: transactionsForAccount', ->

  # load the directive's module
  beforeEach module 'frontendApp'

  scope = {}

  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()

  it 'should make hidden element visible', inject ($compile) ->
    element = angular.element '<transactions-for-account></transactions-for-account>'
    element = $compile(element) scope
    expect(element.text()).toBe 'this is the transactionsForAccount directive'
