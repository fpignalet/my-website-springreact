/*************************************************************************************
 * INCLUDES
 *************************************************************************************/
'use strict';

// import { HttpClientModule } from '@angular/common/http';

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/
class TemplateData {
    constructor() {
        this.message = ""
    }
}

export class TestApp2 {

    create(appname, ctrlname) {
        const testapp = angular.module(appname, [ 'ngRoute' ]);

        testapp.config(["$routeProvider", function($routeProvider) {

            $routeProvider
                .when("/Menu1", {
                    // templateUrl : "anghome.html",
                    template : () => {
                        return "" +
                            "<h1>Choosed: Menu 1, {{data.message}}</h1>" +
                            "<p>result = 75%.</p>" +
                            ""
                    },
                    // controller: ctrlname
                    controller: "ctrlMENU1"
                })
                .when("/Menu2", {
                    template : () => {
                        return "" +
                            "<h1>Choosed: Menu 2, {{data.message}}</h1>" +
                            "<p>result = 95%.</p>" +
                            ""
                    },
                    // controller: ctrlname
                    controller: "ctrlMENU2"
                })
                .otherwise({
                    template : () => {
                        return "" +
                            "<h1>Choosed: Nothing, {{data.message}}</h1>" +
                            "<p>Nothing has been selected</p>" +
                            ""
                    },
                    controller: ctrlname
                });
        }]);

        testapp.controller("ctrlMENU1", ["$scope", function($scope) {
            console.log("$scope = " + $scope.toString());
            let data = new TemplateData()
            data.message = 'Hello from MENU1 Controller'
            $scope.data = data;

            // const params = new HttpParams({
            //     fromString: ''
            // });
            //
            // this.courses$ = this.http
            //     .request(
            //         "GET",
            //         "/exthttpgetjson0",
            //         {
            //             responseType:"json",
            //             params
            //         })
            //     .do(console.log)
            //     .map(data => _.values(data));
        }]);

        testapp.controller("ctrlMENU2", ["$scope", function($scope) {
            console.log("$scope = " + $scope.toString());
            let data = new TemplateData()
            data.message = 'Hello from MENU2 Controller'
            $scope.data = data;
        }]);

        testapp.controller(ctrlname, ["$scope", function($scope) {
            console.log("$scope = " + $scope.toString());
            let data = new TemplateData()
            data.message = 'Hello from Default Controller'
            $scope.data = data;
        }]);
    }

}
