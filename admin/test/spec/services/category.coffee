'use strict'

describe 'Service: Category', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  Category = {}
  beforeEach inject (_Category_) ->
    Category = _Category_

  it 'should do something', ->
    expect(!!Category).toBe true
