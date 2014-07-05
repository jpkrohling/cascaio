'use strict'

describe 'Service: CheckingAccountTransaction', ->

  # load the service's module
  beforeEach module 'frontendApp'

  # instantiate service
  CheckingAccountTransaction = {}
  beforeEach inject (_CheckingAccountTransaction_) ->
    CheckingAccountTransaction = _CheckingAccountTransaction_

  it 'should do something', ->
    expect(!!CheckingAccountTransaction).toBe true
