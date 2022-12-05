package com.acme.statusmgr.beans;

import com.acme.statusmgr.DetailsInformationInterface;

public class AvailableProcessors extends ServerStatusDecorator{

    public static final String template = ", and there are %s processors available";

    public AvailableProcessors(ServerStatus serverStatus) {
        super(serverStatus);
    }

    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + String.format(template, detailsInformation.getAvailableProcessors());
    }


//    @Override
//    public void setDetailsInformation(DetailsInformationInterface detailsInformation) {
//        this.detailsInformation = detailsInformation;
//    }
}
