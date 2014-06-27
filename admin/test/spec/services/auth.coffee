'use strict'

describe 'Service: Auth', ->

  # load the service's module
  beforeEach module 'adminApp'

  window.keycloak = {mocked: true}

  # instantiate service
  Auth = {}
  beforeEach inject (_Auth_) ->
    Auth = _Auth_

  it 'wrap window.keycloak', ->
    expect(!!Auth).toBe true
    expect(Auth.mocked).toBe true
