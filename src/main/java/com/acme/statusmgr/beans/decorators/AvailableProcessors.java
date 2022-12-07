package com.acme.statusmgr.beans.decorators;

import com.acme.statusmgr.beans.ServerStatusInterface;

public class AvailableProcessors extends ServerStatusDecorator{

    public static final String template = ", and there are %s processors available";

    public AvailableProcessors(ServerStatusInterface serverStatus) {
        super(serverStatus);
    }



    public String getStatusDesc() {
        System.out.println(serverStatus.getStatusDesc());
        return serverStatus.getStatusDesc() + String.format(template, detailsInformation.getAvailableProcessors());
    }

    @Override
    public Integer getRequestCost() {
        return serverStatus.getRequestCost() + 3;
    }




}
