package com.acme.statusmgr.beans.decorators;

import com.acme.statusmgr.detailsInformationManager.DetailsInformationInterface;
import com.acme.statusmgr.beans.ServerStatusInterface;

import java.util.List;

public abstract class ServerStatusDecorator implements ServerStatusInterface {


    ServerStatusInterface serverStatus;
    static DetailsInformationInterface detailsInformation;


    public ServerStatusDecorator(ServerStatusInterface serverStatus) {

        this.serverStatus = serverStatus;
    }

    public static void setDetailsInformation(DetailsInformationInterface detailsInformation) {
        ServerStatusDecorator.detailsInformation = detailsInformation;
    }

    @Override
    public String getContentHeader() {
        return serverStatus.getContentHeader();
    }


    @Override
    public long getId() {

        return serverStatus.getId();
    }

    public static ServerStatusInterface decorateServerStatus(ServerStatusInterface serverStatus, List<String> list) {
        for (String detail: list) {

            switch (detail) {
                case "availableProcessors": serverStatus = new AvailableProcessors(serverStatus); break;

                case "freeJVMMemory": serverStatus = new JVMMemory(serverStatus); break;

                case "totalJVMMemory": serverStatus = new TotalJVMMemory(serverStatus); break;

                case "jreVersion": serverStatus = new JREVersion(serverStatus); break;

                case "tempLocation": serverStatus = new TempLocation(serverStatus); break;
            }
        }
        return serverStatus;
    }




}
