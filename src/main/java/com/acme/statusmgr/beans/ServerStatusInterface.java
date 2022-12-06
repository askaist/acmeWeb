package com.acme.statusmgr.beans;

import com.acme.statusmgr.DetailsInformationInterface;

public interface ServerStatusInterface {





    public abstract void setDetailsInformation(DetailsInformationInterface detailsInformation);

    abstract long getId();

    abstract String getStatusDesc();

    abstract Integer getRequestCost();



}
