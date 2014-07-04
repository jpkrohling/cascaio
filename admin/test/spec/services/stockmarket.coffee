'use strict'

describe 'Service: StockMarket', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  StockMarket = {}
  beforeEach inject (_StockMarket_) ->
    StockMarket = _StockMarket_

  it 'should do something', ->
    expect(!!StockMarket).toBe true
