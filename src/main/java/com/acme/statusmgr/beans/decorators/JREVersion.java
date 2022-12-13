package com.acme.statusmgr.beans.decorators;

import com.acme.statusmgr.beans.ServerStatusInterface;

public class JREVersion extends ServerStatusDecorator{

    public static final String template = ", and the JRE version is %s";

    public JREVersion(ServerStatusInterface serverStatus) {
        super(serverStatus);
    }



    @Override
    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + String.format(template, detailsInformation.getJREVersion());
    }

    @Override
    public Integer getRequestCost() {
        return serverStatus.getRequestCost() + 19;
    }
}
