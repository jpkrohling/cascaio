'use strict'

describe 'Service: Stock', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  Stock = {}
  beforeEach inject (_Stock_) ->
    Stock = _Stock_

  it 'should do something', ->
    expect(!!Stock).toBe true
