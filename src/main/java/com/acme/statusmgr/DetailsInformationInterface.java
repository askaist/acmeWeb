package com.acme.statusmgr;

public interface DetailsInformationInterface {

    int getAvailableProcessors();

    long getJVMMemory();

    long getTotalJVMMemory();

    String getJREVersion();

    String getTempLocation();



}
