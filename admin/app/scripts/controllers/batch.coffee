'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:BatchCtrl
 # @description
 # # BatchCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'BatchCtrl', ($scope, $http, Batch, apiUrl) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-batch').addClass('active')

  $scope.batches = []

  $scope.load = ->
    $scope.batches = Batch.query()

  $scope.submitJob = (jobName) ->
    $http({method: 'POST', url: "#{apiUrl}/batch/#{jobName}"})
    .success((data, status, headers, config) ->
        $scope.load()
      )

  $scope.load()
