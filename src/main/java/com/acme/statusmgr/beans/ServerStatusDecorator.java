package com.acme.statusmgr.beans;

import com.acme.statusmgr.DetailsInformationInterface;

public abstract class ServerStatusDecorator implements ServerStatusInterface {


    ServerStatus serverStatus;
    DetailsInformationInterface detailsInformation;


    public ServerStatusDecorator(ServerStatus serverStatus) {

        this.serverStatus = serverStatus;
    }

    public void setDetailsInformation(DetailsInformationInterface detailsInformation) {
        this.detailsInformation = detailsInformation;
    }
}
