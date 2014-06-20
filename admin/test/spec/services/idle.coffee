'use strict'

describe 'Service: Idle', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  Idle = {}
  beforeEach inject (_Idle_) ->
    Idle = _Idle_

  it 'should do something', ->
    expect(!!Idle).toBe true
