package com.acme.statusmgr.beans.decorators;

import com.acme.statusmgr.detailsInformationManager.DetailsInformationInterface;
import com.acme.statusmgr.beans.ServerStatusInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
    public long getId() {

        return serverStatus.getId();
    }


    @Override
    public String getContentHeader() {
        return serverStatus.getContentHeader();
    }



    @Override
    public String getStatusDesc() {
        return null;
    }



    @Override
    public Integer getRequestCost() {
        return null;
    }








    public static ServerStatusInterface decorateServerStatus(ServerStatusInterface serverStatus, List<String> list) {
        for (String detail: list) {

            switch (detail) {
                case "availableProcessors" -> serverStatus = new AvailableProcessors(serverStatus);
                case "freeJVMMemory" -> serverStatus = new JVMMemory(serverStatus);
                case "totalJVMMemory" -> serverStatus = new TotalJVMMemory(serverStatus);
                case "jreVersion" -> serverStatus = new JREVersion(serverStatus);
                case "tempLocation" -> serverStatus = new TempLocation(serverStatus);
                default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid details option: " + detail);
            }
        }
        return serverStatus;
    }




}
