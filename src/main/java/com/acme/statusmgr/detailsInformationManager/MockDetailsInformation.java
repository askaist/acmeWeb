package com.acme.statusmgr.detailsInformationManager;

/**
 * Gets predetermined values that is to be used for testing
 * in order to maintain constant results.
 */
public class MockDetailsInformation implements DetailsInformationInterface{
    @Override
    public int getAvailableProcessors() {
        return 4;
    }

    @Override
    public long getJVMMemory() {
        return 127268272;
    }

    @Override
    public long getTotalJVMMemory() {
        return 159383552;
    }

    @Override
    public String getJREVersion() {
        return "15.0.2+7-27";
    }

    @Override
    public String getTempLocation() {
        return """
                M:\\\\AppData\\\\Local\\\\Temp

                """;
    }
}
