'use strict'

describe 'Service: Checkingaccount', () ->

  # load the service's module
  beforeEach module 'frontendApp'

  # instantiate service
  Checkingaccount = {}
  beforeEach inject (_Checkingaccount_) ->
    Checkingaccount = _Checkingaccount_

  it 'should do something', () ->
    expect(!!Checkingaccount).toBe true
