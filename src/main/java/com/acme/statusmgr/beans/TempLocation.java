package com.acme.statusmgr.beans;

public class TempLocation extends ServerStatusDecorator{

    public static final String template = ", and the server's temp file location is %s";

    public TempLocation(ServerStatusInterface serverStatus) {
        super(serverStatus);
    }



    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + String.format(template, detailsInformation.getTempLocation());
    }
}
