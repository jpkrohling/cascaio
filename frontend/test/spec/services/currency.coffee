'use strict'

describe 'Service: Currency', ->

  # load the service's module
  beforeEach module 'frontendApp'

  # instantiate service
  Currency = {}
  beforeEach inject (_Currency_) ->
    Currency = _Currency_

  it 'should do something', ->
    expect(!!Currency).toBe true
