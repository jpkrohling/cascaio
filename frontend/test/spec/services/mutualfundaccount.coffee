'use strict'

describe 'Service: MutualFundAccount', ->

  # load the service's module
  beforeEach module 'frontendApp'

  # instantiate service
  MutualFundAccount = {}
  beforeEach inject (_MutualFundAccount_) ->
    MutualFundAccount = _MutualFundAccount_

  it 'should do something', ->
    expect(!!MutualFundAccount).toBe true
