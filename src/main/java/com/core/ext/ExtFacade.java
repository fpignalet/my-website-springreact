package com.core.ext;

public class ExtFacade {

    static {
        final String libraryName = "extfacade";
        System.loadLibrary(libraryName);
    }

    /**
     *
     */
    static class Data {
        public int field1 = 0;
        public long field2 = 1;
        public float field3 = (float) 2.0;
        public double field4 = 2.0;
        public String field5 = "TEST";
        public int[] field6 = { 0, 1, 2 };
    };

    /**
     *
     */
    public void testAll() {
        ///1. TEST with params:
        final String result_execute = testExecute();
        System.out.println(result_execute);

        ///2. TEST with complex return value:
        final String[] result_getData = testGetData();
        for(final String data: result_getData){
            System.out.println(data);
        }
    }

    /**
     * @param data
     * @return
     */
    protected native String execute(final Data data);

    /**
     * @return
     */
    protected String testExecute() {
        final Data data =  new Data();
        return execute(data);
    }

    /**
     * @return
     */
    protected native String[] getData();

    /**
     * @return
     */
    protected String[] testGetData() {
        return getData();
    }

}
