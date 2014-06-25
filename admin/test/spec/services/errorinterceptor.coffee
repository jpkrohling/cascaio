'use strict'

describe 'Service: errorinterceptor', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  errorinterceptor = {}
  beforeEach inject (_errorinterceptor_) ->
    errorinterceptor = _errorinterceptor_

  it 'should do something', ->
    expect(!!errorinterceptor).toBe true
