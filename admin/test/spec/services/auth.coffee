'use strict'

describe 'Service: Auth', ->

  # load the service's module
  beforeEach module 'adminApp'

  # instantiate service
  Auth = {}
  beforeEach inject (_Auth_) ->
    Auth = _Auth_

  it 'findout a way to check it', ->
    console.log("ignored")
