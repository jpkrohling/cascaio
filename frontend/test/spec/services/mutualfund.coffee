'use strict'

describe 'Service: MutualFund', ->

  # load the service's module
  beforeEach module 'frontendApp'

  # instantiate service
  MutualFund = {}
  beforeEach inject (_MutualFund_) ->
    MutualFund = _MutualFund_

  it 'should do something', ->
    expect(!!MutualFund).toBe true
