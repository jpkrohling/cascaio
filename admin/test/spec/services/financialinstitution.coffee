'use strict'

describe 'Service: Financialinstitution', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  Financialinstitution = {}
  beforeEach inject (_Financialinstitution_) ->
    Financialinstitution = _Financialinstitution_

  it 'should do something', ->
    expect(!!Financialinstitution).toBe true
