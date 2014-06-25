'use strict'

describe 'Service: Mutualfund', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  Mutualfund = {}
  beforeEach inject (_Mutualfund_) ->
    Mutualfund = _Mutualfund_

  it 'should do something', ->
    expect(!!Mutualfund).toBe true
