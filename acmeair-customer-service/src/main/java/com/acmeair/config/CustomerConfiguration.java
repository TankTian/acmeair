/*******************************************************************************
* Copyright 2017 Huawei Technologies Co., Ltd
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*******************************************************************************/
package com.acmeair.config;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.acmeair.service.CustomerService;
import io.servicecomb.provider.rest.common.RestSchema;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestSchema(schemaId = "customer_configuration")
@Path("/info/config")
@Api(value = "Customer Service Configuration Information Service", produces = MediaType.APPLICATION_JSON)
public class CustomerConfiguration {
    @Autowired
    private CustomerService customerService;

    @GET
    @Path("/countCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get the Customer Resource.",produces=MediaType.APPLICATION_JSON, response = Long.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "CustomerCount Resource is found"),
            @ApiResponse(code = 404, message = "CustomerCount Resource cannot be found")
    
    })
    public long countCustomer() {
        try {
          return  customerService.count();
        } catch (Exception e) {
            e.printStackTrace();
            return  -1;
        }
    }

    @GET
    @Path("/countSessions")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get the Customer SessionCount Resource.",produces=MediaType.APPLICATION_JSON, response = Long.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "CustomerSessionCount Resource is found"),
        @ApiResponse(code = 404, message = "CustomerSessionCount Resource cannot be found")
    
    })
    public long countCustomerSessions() {
        try {

            return customerService.countSessions();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
