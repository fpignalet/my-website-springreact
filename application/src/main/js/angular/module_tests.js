/*************************************************************************************
 * INCLUDES
 *************************************************************************************/
'use strict';

import {TestApp1} from "./angapp1.js";
import {TestApp2} from "./angapp2.js";

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
        const appname = contener.getElementById(id).getAttribute("ng-app");
        const ctrlname = contener.getElementById(id).getAttribute("ng-controller");

        const app1 = new TestApp1();
        app1.create(appname, ctrlname, ifirstName, ilastName, products);

//        console.log(this.getFuncName() + "OK");
    }

    /// @brief add angularjs to tech tests
    /// @param name desc...
    /// @param ctrl desc...
    /// @param ifirstName desc...
    /// @param ilastName desc...
    /// @param products desc...
    testANGULAR2(contener, id) {
        const appname = contener.getElementById(id).getAttribute("ng-app");
        const ctrlname = contener.getElementById(id).getAttribute("ng-controller");

        const app2 = new TestApp2();
        app2.create(appname, ctrlname);

//        console.log(this.getFuncName() + "OK");
    }

}

window.angularexe = () => {
    try {
        const local = new AngularTests();

        local.testANGULAR1(document, "angutest1",
                               "Firstname", "Lastname", ["Item 1", "Item 2", "Item 3"]);

        local.testANGULAR2(document, "angutest2");
        angular.bootstrap(document.getElementById("angutest2"), ['otherapp']);
    }
    catch (e) {
        console.log(e.toString())
        alert("angular app failed")
    }
    finally {
        //...
    }
}
