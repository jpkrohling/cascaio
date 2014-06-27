'use strict'

describe 'Directive: errorsBox', ->

  # load the directive's module
  beforeEach module 'adminApp'

  scope = {}

  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()

  it 'is not a valid test', inject ($compile) ->
    element = angular.element '<errors-box></errors-box>'
    element = $compile(element) scope
