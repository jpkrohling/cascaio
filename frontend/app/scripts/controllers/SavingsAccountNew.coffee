'use strict'

angular.module('frontendApp')
  .controller 'SavingsAccountNewCtrl', ['$rootScope', '$scope', 'SavingsAccount', ($rootScope, $scope, SavingsAccount) ->
    $scope.savingsAccountNew = new SavingsAccount({})

    form = $('#savingsAccountNewForm')
    financialInstitutionDropDown = $('#savingsAccountNewFormFinancialInstitutionDropdown')

    financialInstitutionDropDown.dropdown({
      onChange: (val) ->
        $scope.savingsAccountNew.financialInstitutionId = val
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
        $scope.savingsAccountNew.$save().then((account) ->
          $rootScope.savingsAccounts.push account
        )
        $scope.savingsAccountNew = new CheckingAccount({})
  ]