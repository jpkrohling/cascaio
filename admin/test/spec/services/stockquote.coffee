'use strict'

describe 'Service: StockQuote', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  StockQuote = {}
  beforeEach inject (_StockQuote_) ->
    StockQuote = _StockQuote_

  it 'should do something', ->
    expect(!!StockQuote).toBe true
