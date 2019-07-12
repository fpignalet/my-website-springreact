package com.core.ext;

public class ExtFacade {

    static {
        System.loadLibrary("engexternal");
    }

    static class Data {
        public int field1 = 0;
        public long field2 = 1;
        public float field3 = (float) 2.0;
        public double field4 = 2.0;
        public String field5 = "TEST";
        public int[] field6 = { 0, 1, 2 };
    };

    private native String execute(final Data data);
    private native String[] getData();

    public String test_execute() {
        final Data data =  new Data();
        return execute(data);
    }

    public String[] test_getData() {
        return getData();
    }

}
