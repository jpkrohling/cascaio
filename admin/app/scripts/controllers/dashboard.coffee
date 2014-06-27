'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:DashboardCtrl
 # @description
 # # DashboardCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'DashboardCtrl', ->
  $('#main-nav li').removeClass('active');
  $('#main-nav-dashboard').addClass('active');
