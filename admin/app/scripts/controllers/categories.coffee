'use strict'

###*
 # @ngdoc function
 # @name adminApp.controller:CategoriesCtrl
 # @description
 # # CategoriesCtrl
 # Controller of the adminApp
###
angular.module('adminApp').controller 'CategoriesCtrl', ($scope, $filter, $location, toaster, ngTableParams, Category) ->
  $('#main-nav li').removeClass('active')
  $('#main-nav-reference').addClass('active')

  $scope.loading = false
  $scope.categories = []

  $scope.tableParams = new ngTableParams({page: 1, count: 25}, {
    getData: ($defer, params) ->
      data = $scope.categories

      if params.filter()
        data = $filter('filter')(data, params.filter())

      params.total(data.length)
      $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()))
  })

  $scope.load = ->
    $scope.loading = true
    $scope.categories = Category.query({}, ->
      $scope.loading = false
      $scope.tableParams.reload()
    , -> $scope.loading = false
    )

  $scope.showCreateForm = ->
    $location.path('/reference/categories/new')

  $scope.remove = (category) ->
    category.$remove().then(->
      toaster.pop('success', '', 'Category removed')
      $scope.categories.splice( $scope.categories.indexOf(category), 1 );
      $scope.tableParams.reload()
    )

  $scope.load()
