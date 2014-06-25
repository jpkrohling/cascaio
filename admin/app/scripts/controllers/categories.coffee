'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:CategoriesCtrl
 # @description
 # # CategoriesCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'CategoriesCtrl', ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-reference').addClass('active')

  $(document).ready( () ->
    $('.datatable').dataTable({"fnDrawCallback": ( oSettings ) ->
      if ($('.sidebar-pf').length > 0)
        sidebar()
    })
  )
