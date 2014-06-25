'use strict'

describe 'Service: Exchangerate', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  Exchangerate = {}
  beforeEach inject (_Exchangerate_) ->
    Exchangerate = _Exchangerate_

  it 'should do something', ->
    expect(!!Exchangerate).toBe true
