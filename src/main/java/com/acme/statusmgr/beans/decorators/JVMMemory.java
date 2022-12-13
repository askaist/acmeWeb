package com.acme.statusmgr.beans.decorators;

import com.acme.statusmgr.beans.ServerStatusInterface;

public class JVMMemory extends ServerStatusDecorator{

    public static final String template = ", and there are %s bytes of JVM memory free";

    public JVMMemory(ServerStatusInterface serverStatus) {
        super(serverStatus);
    }



    @Override
    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + String.format(template, detailsInformation.getJVMMemory());
    }

    @Override
    public Integer getRequestCost() {
        return serverStatus.getRequestCost() + 7;
    }
}
