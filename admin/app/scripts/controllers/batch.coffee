'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:BatchCtrl
 # @description
 # # BatchCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'BatchCtrl', ($scope, $http, Batch, apiUrl, toaster) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-batch').addClass('active')

  $scope.batches = []

  $scope.load = ->
    $scope.batches = Batch.query()

  $scope.submitJob = (jobName) ->
    $http({method: 'POST', url: "#{apiUrl}/batch/#{jobName}"})
    .success((data, status, headers, config) ->
      toaster.pop('success', 'Started', 'Job started')
      $scope.load()
    )

  $scope.abortJob = (execution) ->
    $http({method: 'DELETE', url: "#{apiUrl}/batch/#{execution.id}"})
    .success((data, status, headers, config) ->
      toaster.pop('success', 'Aborted', 'Job aborted')
      $scope.load()
    )

  $scope.canAbort = (execution) ->
    return execution.status == "STARTED" || execution.status == "STARTING"

  $scope.load()
