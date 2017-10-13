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
// this sourcecode is modified by Huawei Technologies Co., Ltd.
package com.acmeair.config;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.acmeair.loader.Loader;
import io.servicecomb.provider.rest.common.RestSchema;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;

@RestSchema(schemaId = "loader")
@Path("/info/loader")
@Api(value = "Customer Service User Information Loading Service", produces = MediaType.TEXT_PLAIN)
public class LoaderREST {

    @Autowired
    private Loader loader;

    @GET
    @Path("/query")
    @Produces(MediaType.TEXT_PLAIN)
    public String queryLoader() {
        String response = loader.queryLoader();
        return response;
    }

    @GET
    @Path("/load")
    @Produces(MediaType.TEXT_PLAIN)
    public String loadDB(@DefaultValue("-1") @QueryParam("numCustomers") long numCustomers) {
        String response = loader.loadDB(numCustomers);
        return response;
    }
}
