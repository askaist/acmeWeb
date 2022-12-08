/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.statusmgr;

import com.acme.statusmgr.beans.decorators.ServerStatusDecorator;
import com.acme.statusmgr.detailsInformationManager.MockDetailsInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ServerStatusControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setMockDetails() {

        ServerStatusDecorator.setDetailsInformation(new MockDetailsInformation());

    }



    @Test
    public void noNameParamShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/server/status")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up"));
    }

    @Test
    public void nameParamShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/server/status").param("name", "RebYid"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by RebYid"));
    }


    @Test
    public void detailed_name_availProc() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=availableProcessors&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(4))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and there are 4 processors available"));
    }


    @Test
    public void nameAndDetailsTest() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Yankel&details=availableProcessors,freeJVMMemory,totalJVMMemory,jreVersion,tempLocation"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(72))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and there are 4 processors available, and there are 127268272 bytes of JVM memory free, and there is a total of 159383552 bytes of JVM memory, and the JRE version is 15.0.2+7-27, and the server's temp file location is M:\\\\AppData\\\\Local\\\\Temp"));
    }

    @Test
    public void detailsThenNameTest() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=availableProcessors&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(4))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and there are 4 processors available"));
    }

    @Test
    public void InvalidDetails() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Yankel&details=availableProcessors,junkERROR"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(status().reason(is("Invalid details option: junkERROR")))
                .andExpect(result -> Assertions.assertEquals("400 BAD_REQUEST \"Invalid details option: junkERROR\"", result.getResolvedException().getMessage()));
    }

    @Test
    public void duplicateDetail() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=availableProcessors,availableProcessors&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Yankel"))
                .andExpect(jsonPath("$.requestCost").value(7))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and there are 4 processors available, and there are 4 processors available"));
    }


}
