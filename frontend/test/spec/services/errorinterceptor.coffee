'use strict'

describe 'Service: ErrorInterceptor', ->

  # load the service's module
  beforeEach module 'frontendApp'

  # instantiate service
  ErrorInterceptor = {}
  beforeEach inject (_ErrorInterceptor_) ->
    ErrorInterceptor = _ErrorInterceptor_

  it 'should do something', ->
    expect(!!ErrorInterceptor).toBe true
