'use strict'

describe 'Service: backendHost', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  backendHost = {}
  beforeEach inject (_backendHost_) ->
    backendHost = _backendHost_

  it 'should do something', ->
    expect(!!backendHost).toBe true
