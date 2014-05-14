'use strict'

describe 'Service: Savingsaccount', () ->

  # load the service's module
  beforeEach module 'frontendApp'

  # instantiate service
  Savingsaccount = {}
  beforeEach inject (_Savingsaccount_) ->
    Savingsaccount = _Savingsaccount_

  it 'should do something', () ->
    expect(!!Savingsaccount).toBe true
