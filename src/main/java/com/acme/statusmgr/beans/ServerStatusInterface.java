package com.acme.statusmgr.beans;

/**
 * This interface is implemented by server status and its decorators
 * It contains the methods needed to retrieve data for a request.
 */

public interface ServerStatusInterface {




     long getId();

     String getStatusDesc();

     Integer getRequestCost();

     String getContentHeader();



}
