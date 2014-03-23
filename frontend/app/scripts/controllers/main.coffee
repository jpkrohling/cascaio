'use strict'

angular.module('frontendApp')
.controller 'MainCtrl', ['$scope', ($scope) ->
    $scope.keycloak = Keycloak({
      clientId: 'frontend',
      realm: 'cascaio',
      onload: 'login-required'
    })

    $scope.keycloak.init () ->
      console.debug("Init called")
      $scope.loadUserData();

    $scope.loadUserData = () ->
      console.debug("loadUserData")
      $scope.idToken = $scope.keycloak.idToken
      $scope.$apply()
  ]
