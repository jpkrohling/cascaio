'use strict'

describe 'Service: Country', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  Country = {}
  beforeEach inject (_Country_) ->
    Country = _Country_

  it 'should do something', ->
    expect(!!Country).toBe true
