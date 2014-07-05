'use strict'

describe 'Service: FinancialInstitution', ->

  # load the service's module
  beforeEach module 'frontendApp'

  # instantiate service
  FinancialInstitution = {}
  beforeEach inject (_FinancialInstitution_) ->
    FinancialInstitution = _FinancialInstitution_

  it 'should do something', ->
    expect(!!FinancialInstitution).toBe true
