package com.acme.statusmgr;

import com.acme.statusmgr.beans.*;
import com.acme.statusmgr.beans.decorators.ServerStatusDecorator;
import com.acme.statusmgr.detailsInformationManager.DetailsInformation;
import com.acme.statusmgr.detailsInformationManager.MockDetailsInformation;
import org.slf4j.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Controller for all web/REST requests about the status of servers
 * <p>
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 * All start with /server
 * /status  will give back status of server
 * a param of 'name' specifies a requestor name to appear in response
 * <p>
 * Examples:
 * http://localhost:8080/server/status
 * <p>
 * http://localhost:8080/server/status?name=Noach
 */
@RestController
@RequestMapping("/server")
public class StatusController {

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();


    /**
     * Process a request for server status information
     *
     * @param name optional param identifying the requester
     * @return a ServerStatus object containing the info to be returned to the requestor
     */
    @RequestMapping("/status")
    public ServerStatus getStatus(@RequestParam(value = "name", defaultValue = "Anonymous") String name, @RequestParam(required = false) List<String> details) {
        if (details != null) {
            Logger logger = LoggerFactory.getLogger("StuffImInterestedIn");
            logger.info("details provided: " + details);
        }
        return new ServerStatus(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/status/detailed")
    public ServerStatusInterface getDetailed(
            @RequestParam(value = "name", defaultValue = "Anonymous") String name,
            @RequestParam(value = "details", defaultValue = "") List<String> details) {

        ServerStatusDecorator.setDetailsInformation(new DetailsInformation());


        ServerStatusInterface serverStatus = new ServerStatus(counter.incrementAndGet(), String.format(template, name));
        serverStatus = ServerStatusDecorator.decorateServerStatus(serverStatus, details);


        return serverStatus;
    }







}
