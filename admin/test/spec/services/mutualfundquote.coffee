'use strict'

describe 'Service: MutualFundQuote', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  MutualFundQuote = {}
  beforeEach inject (_MutualFundQuote_) ->
    MutualFundQuote = _MutualFundQuote_

  it 'should do something', ->
    expect(!!MutualFundQuote).toBe true
