'use strict'

describe 'Service: Authinterceptor', () ->

  # load the service's module
  beforeEach module 'frontendApp'

  # instantiate service
  Authinterceptor = {}
  beforeEach inject (_Authinterceptor_) ->
    Authinterceptor = _Authinterceptor_

  it 'should do something', () ->
    expect(!!Authinterceptor).toBe true
