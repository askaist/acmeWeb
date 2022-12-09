package com.acme.statusmgr.detailsInformationManager;

/**
 * Utilized Runtime and System to get information unique to the
 * users computer.
 */

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
        return String.valueOf(Runtime.version());
    }

    @Override
    public String getTempLocation() {
        return System.getenv("TEMP");
    }
}
