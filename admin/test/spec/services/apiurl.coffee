'use strict'

describe 'Service: apiUrl', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  apiUrl = {}
  beforeEach inject (_apiUrl_) ->
    apiUrl = _apiUrl_

  it 'should do something', ->
    expect(!!apiUrl).toBe true
