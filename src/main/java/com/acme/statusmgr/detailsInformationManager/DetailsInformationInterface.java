package com.acme.statusmgr.detailsInformationManager;

/**
 * Interface for the details facades that can be implemented however the user
 * sees fit and changed using the static method in ServerStatusDecorator.
 */

public interface DetailsInformationInterface {

    int getAvailableProcessors();

    long getJVMMemory();

    long getTotalJVMMemory();

    String getJREVersion();

    String getTempLocation();



}
