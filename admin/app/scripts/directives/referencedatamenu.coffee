'use strict'

###*
 # @ngdoc directive
 # @name adminApp.directive:referenceDataMenu
 # @description
 # # referenceDataMenu
###
angular.module('adminApp').directive('casReferenceDataMenu', ($document) ->
  templateUrl: 'views/directives/referencedatamenu.html'
  restrict: 'A'
  scope: {
    selected: '@selected'
  }

  link: (scope, element, attrs) ->
    $document.ready(->
      documentHeight = $( window ).height()
      navbarpfHeight = $('.navbar-pf').outerHeight()
      footer = $('#footer').outerHeight()
      colHeight = documentHeight - navbarpfHeight - footer

      element.children('[class*="col-"]').css({ "min-height":colHeight});
      $(".reference-menu").children().removeClass("active")
      $(".reference-menu-" + scope.selected).addClass("active")
    )
)
