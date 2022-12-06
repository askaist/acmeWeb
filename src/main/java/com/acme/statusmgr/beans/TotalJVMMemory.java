package com.acme.statusmgr.beans;



public class TotalJVMMemory extends ServerStatusDecorator{

    public static final String template = ", and there is a total of %s bytes of JVM memory";

    public TotalJVMMemory(ServerStatusInterface serverStatus) {
        super(serverStatus);
    }



    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + String.format(template, detailsInformation.getTotalJVMMemory());
    }
}
