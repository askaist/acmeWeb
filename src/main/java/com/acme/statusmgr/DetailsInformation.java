package com.acme.statusmgr;

public class DetailsInformation implements DetailsInformationInterface {

    Runtime runtime = Runtime.getRuntime();

    @Override
    public int getAvailableProcessors() {
        return runtime.availableProcessors();
    }

    @Override
    public long getJVMMemory() {
        return runtime.freeMemory();
    }

    @Override
    public long getTotalJVMMemory() {
        return runtime.totalMemory();
    }

    @Override
    public String getJREVersion() {
        return "jre version";
    }

    @Override
    public String getTempLocation() {
        return System.getenv("TEMP");
    }
}
