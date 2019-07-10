/*************************************************************************************
 * INCLUDES
 *************************************************************************************/
'use strict';

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/
class AngularTests {

    /// @brief add angularjs to tech tests
    /// @param name desc...
    /// @param ctrl desc...
    /// @param ifirstName desc...
    /// @param ilastName desc...
    /// @param products desc...
    testANGULAR1(contener, id, ifirstName, ilastName, products) {
        const appname = contener.getElementById("angutest1").getAttribute("ng-app");
        const ctrlname = contener.getElementById("angutest1").getAttribute("ng-controller");

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

//        console.log(this.getFuncName() + "OK");
    }

    /// @brief add angularjs to tech tests
    /// @param name desc...
    /// @param ctrl desc...
    /// @param ifirstName desc...
    /// @param ilastName desc...
    /// @param products desc...
    testANGULAR2(contener, id) {
        function onmenu1() {
            return "<h1>Choosed: Menu 1</h1><p>result = 75%.</p>"
        }
        function onmenu2() {
            return "<h1>Choosed: Menu 2</h1><p>result = 95%.</p>"
        }
        function ondefault() {
            return "<h1>Choosed: Nothing</h1><p>Nothing has been selected</p>"
        }

        const appname = contener.getElementById(id).getAttribute("ng-app");
        const ctrlname = contener.getElementById(id).getAttribute("ng-controller");

        const otherapp = angular.module(appname, [ 'ngRoute' ]);
        otherapp.config(["$routeProvider", function($routeProvider) {

            $routeProvider
                .when("/Menu1", {
                    template : onmenu1(),
                    controller: ctrlname
                })
                .when("/Menu2", {
                    template : onmenu2(),
                    controller: ctrlname
                })
                .otherwise({
                    template : ondefault(),
                    controller: ctrlname
                });
        }]);
        otherapp.controller(ctrlname, ["$scope", function($scope) {
            console.log("$scope = " + $scope.toString());
        }]);

        angular.bootstrap(contener.getElementById(id), ['otherapp']);

//        console.log(this.getFuncName() + "OK");
    }

}

window.angularexe = () => {
    try {
        const local = new AngularTests();
        local.testANGULAR1(document, "angutest1", "Firstname", "Lastname", ["Item 1", "Item 2", "Item 3"]);
        local.testANGULAR2(document, "angutest2");
    }
    catch (e) {
        console.log(e.toString())
    }
    finally {
        //...
    }
}
