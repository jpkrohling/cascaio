'use strict'

describe 'Service: Mutualfundquote', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  Mutualfundquote = {}
  beforeEach inject (_Mutualfundquote_) ->
    Mutualfundquote = _Mutualfundquote_

  it 'should do something', ->
    expect(!!Mutualfundquote).toBe true
