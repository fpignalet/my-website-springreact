class Multiple {

    /// @brief
    /// @param ident
    static init(ident) {
        if(null == window.instances) {
            window.instances = {};
        }
        if(null == window.instances[ident]) {
            window.instances[ident] = [];
        }
    }

    /// @brief
    /// @param ident
    /// @param inst
    static add(ident, inst) {
        let elem = window.instances[ident];
        elem.push(inst);
        return elem.length - 1;
    }

    /// @brief
    /// @param ident
    /// @param index
    static get(ident, index) {
        let elem = window.instances[ident];
        if(-1 == index) {
            return elem[elem.length-1];
        }
        else {
            return elem[index];
        }
    }

    /// @brief
    /// @param ident
    constructor(ident) {
        Multiple.init(ident);
        this.index = Multiple.add(ident, this);
    }

}

export default Multiple;