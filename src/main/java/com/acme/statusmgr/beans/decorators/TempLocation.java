package com.acme.statusmgr.beans.decorators;

import com.acme.statusmgr.beans.ServerStatusInterface;

public class TempLocation extends ServerStatusDecorator{

    public static final String template = ", and the server's temp file location is %s";

    public TempLocation(ServerStatusInterface serverStatus) {
        super(serverStatus);
    }



    @Override
    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + String.format(template, detailsInformation.getTempLocation());
    }

    @Override
    public Integer getRequestCost() {
        return serverStatus.getRequestCost() + 29;
    }
}
