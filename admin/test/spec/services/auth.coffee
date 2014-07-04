'use strict'

describe 'Service: Auth', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  class Keycloak
    constructor: ->
      @idToken = {
        name: "bla"
      }

    hasResourceRole: (realm, role) ->
      return true

  window.keycloak = new Keycloak()
  Auth = {}
  beforeEach inject (_Auth_) ->
    Auth = _Auth_

  it 'findout a way to check it', ->
    console.log("ignored")
