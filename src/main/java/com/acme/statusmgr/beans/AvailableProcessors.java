package com.acme.statusmgr.beans;

public class AvailableProcessors extends ServerStatusDecorator{

    public static final String template = ", and there are %s processors available";

    public AvailableProcessors(ServerStatusInterface serverStatus) {
        super(serverStatus);
    }



    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + String.format(template, detailsInformation.getAvailableProcessors());
    }




}
