'use strict'

describe 'Service: Batch', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  Batch = {}
  beforeEach inject (_Batch_) ->
    Batch = _Batch_

  it 'should do something', ->
    expect(!!Batch).toBe true
