package com.acme.statusmgr.beans.decorators;

import com.acme.statusmgr.detailsInformationManager.DetailsInformationInterface;
import com.acme.statusmgr.beans.ServerStatusInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * This is an abstract class used to retrieve information from the details list.
 * All decorators extend this class.
 */

public abstract class ServerStatusDecorator implements ServerStatusInterface {


    ServerStatusInterface serverStatus;
    static DetailsInformationInterface detailsInformation;


    public ServerStatusDecorator(ServerStatusInterface serverStatus) {

        this.serverStatus = serverStatus;
    }

    /**
     * A static method used to set the static variable which is used to decide which
     * details information class is to be used.
     *
     * @param detailsInformation
     */
    public static void setDetailsInformation(DetailsInformationInterface detailsInformation) {
        ServerStatusDecorator.detailsInformation = detailsInformation;
    }


    /**
     * Uses the serverStatus object it wraps to get the ID.
     */
    @Override
    public long getId() {

        return serverStatus.getId();
    }


    /**
     * Uses the serverStatus object it wraps to get he content header.
     */
    @Override
    public String getContentHeader() {
        return serverStatus.getContentHeader();
    }


    /**
     * This get overridden in the decorators.
     * Gets the Unique string to be returned for the detail.
     */
    @Override
    public String getStatusDesc() {
        return null;
    }



    /**
     * This get overridden in the decorators.
     * gets the unique cost of the detail requested.
     */
    @Override
    public Integer getRequestCost() {
        return null;
    }


    /**
     * A static method that StatusController calls which decorates the ServerStatus POJO
     * to what is in the list and throws an error if it is an invalid detail.
     *
     *
     * @param serverStatus an object of type ServerStatusInterface that is to be decorated
     * @param list the list of details that will decorate the ServerStatus
     * @return a fully decorated ServerStatus object
     */
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
