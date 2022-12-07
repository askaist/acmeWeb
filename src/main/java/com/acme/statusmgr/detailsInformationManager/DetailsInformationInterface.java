package com.acme.statusmgr.detailsInformationManager;

public interface DetailsInformationInterface {

    int getAvailableProcessors();

    long getJVMMemory();

    long getTotalJVMMemory();

    String getJREVersion();

    String getTempLocation();



}
