package com.core.ext;

/**
 *
 */
public class ExtFacade {

    static {
        final String libraryName = "extfacade";
        System.loadLibrary(libraryName);
    }

    /**
     * @brief
     */
    public static class Data {
        public int field1 = 0;
        public long field2 = 1;
        public float field3 = (float) 2.0;
        public double field4 = 2.0;
        public String field5 = "TEST";
        public int[] field6 = { 0, 1, 2 };
    }

    /**
     * @param data
     * @return
     */
    protected native String execute(final Data data);

    /**
     * @return
     */
    protected String testExecute(final Data data) {
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

    /**
     * @brief
     */
    public static class Com {
        public enum Way {
            wayIN, wayOUT
        }

        public Com(final String portName, final Way way) {
            this.way = way;
            this.portName = portName;
        }

        final Way way;
        final String portName;
        public String bufferIn;
        public String bufferOut;
    }

    /**
     * @return
     */
    protected native void writeSerial(final Com com);

    /**
     * @return
     */
    protected void testWriteSerial(final Com com) {
        writeSerial(com);
    }

    /**
     * @return
     */
    protected native void readSerial(final Com com);

    /**
     * @return
     */
    protected void testReadSerial(final Com com) {
        readSerial(com);
    }

}
