'use strict'

describe 'Service: ExchangeRate', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  ExchangeRate = {}
  beforeEach inject (_ExchangeRate_) ->
    ExchangeRate = _ExchangeRate_

  it 'should do something', ->
    expect(!!ExchangeRate).toBe true
