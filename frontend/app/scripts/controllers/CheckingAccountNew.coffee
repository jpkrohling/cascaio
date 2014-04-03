'use strict'

angular.module('frontendApp')
  .controller 'CheckingAccountNewCtrl', ['$scope', 'CheckingAccount', ($scope, CheckingAccount) ->
    $scope.checkingAccountNew = new CheckingAccount({})

    form = $('#checkingAccountNewForm')
    financialInstitutionDropDown = $('#checkingAccountNewFormFinancialInstitutionDropdown')

    financialInstitutionDropDown.dropdown({
      onChange: (val) ->
        $scope.checkingAccountNew.financialInstitutionId = val
        $scope.$apply();
    })

    form.form({
            financialInstitutionId: {
              identifier  : 'financialInstitutionId'
              rules: [{ type   : 'empty', prompt : 'Please select a financial institution'}]
            },
            name: {
              identifier  : 'name'
              rules: [{ type   : 'empty', prompt : 'Please enter an account name' } ]
            },
            currency: {
              identifier  : 'currency'
              rules: [ { type   : 'empty', prompt : 'Please enter a currency' } ]
            }
    })

    $scope.createNewAccount = () ->
      if (form.form('validate form'))
        $scope.checkingAccountNew.$save()
        $scope.checkingAccountNew = new CheckingAccount({})
  ]