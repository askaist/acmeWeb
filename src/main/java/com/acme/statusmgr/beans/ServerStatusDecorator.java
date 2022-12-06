package com.acme.statusmgr.beans;

import com.acme.statusmgr.DetailsInformationInterface;

public abstract class ServerStatusDecorator implements ServerStatusInterface {


    ServerStatusInterface serverStatus;
    DetailsInformationInterface detailsInformation;


    public ServerStatusDecorator(ServerStatusInterface serverStatus) {

        this.serverStatus = serverStatus;
    }

    public void setDetailsInformation(DetailsInformationInterface detailsInformation) {
        this.detailsInformation = detailsInformation;
    }


    @Override
    public long getId() {
        return serverStatus.getId();
    }



    @Override
    public Integer getRequestCost() {
        return serverStatus.getRequestCost();
    }
}
