'use strict'

describe 'Service: CheckingAccount', ->

  # load the service's module
  beforeEach module 'frontendApp'

  # instantiate service
  CheckingAccount = {}
  beforeEach inject (_CheckingAccount_) ->
    CheckingAccount = _CheckingAccount_

  it 'should do something', ->
    expect(!!CheckingAccount).toBe true
