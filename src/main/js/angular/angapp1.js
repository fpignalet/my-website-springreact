/*************************************************************************************
 * INCLUDES
 *************************************************************************************/
'use strict';

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/
export class TestApp1 {

    create(appname, ctrlname, ifirstName, ilastName, products) {
        const testapp = angular.module(appname, []);

        testapp.controller(ctrlname, ["$scope", function($scope) {

            // -------------------------------------
            $scope.ifirstName = ifirstName;
            $scope.ilastName = ilastName;

            // -------------------------------------
            $scope.products = products;

            $scope.dynlistadditem = function () {
                $scope.errortext = "";

                const value = $scope.field2Add;
                if (null === value) {
                    $scope.errortext = "Please enter something";
                    return;
                }

                if ($scope.products.indexOf(value) !== -1) {
                    $scope.errortext = "The item is already in the list";
                    return;
                }

                $scope.products.push(value);
            };

            $scope.dynlistremoveitem = function (index) {
                $scope.errortext = "";
                $scope.products.splice(index, 1);
            };

            // -------------------------------------
            $scope.onmouseover = function(event) {
                $scope.x = event.clientX;
                $scope.y = event.clientY;
            };

        }]);
    }
}
