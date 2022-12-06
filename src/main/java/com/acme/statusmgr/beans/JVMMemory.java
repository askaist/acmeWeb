package com.acme.statusmgr.beans;

public class JVMMemory extends ServerStatusDecorator{

    public static final String template = ", and there are %s bytes of JVM memory free";

    public JVMMemory(ServerStatusInterface serverStatus) {
        super(serverStatus);
    }



    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + String.format(template, detailsInformation.getJVMMemory());
    }
}
