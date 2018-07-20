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
package com.acmeair.web;


import com.acmeair.config.CustomerConfiguration;
import com.acmeair.loader.CustomerLoaderREST;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Configuration
public class CustomerServiceApp extends ResourceConfig {
    
    @Value("${spring.jersey.application-path:/}")
    private String apiPath;
    
    @PostConstruct
    @Profile("!SpringCloud")
    public void init() {
        // The init method is called
        configureSwagger();
    }

    @PostConstruct
    @Profile("SpringCloud")
    public void initWithSpringCloud() {
        register(CustomerExceptionHandler.class);
        register(LoginResponseFilter.class);

        // The init method is called
        configureSwagger();
    }
    
    private void configureSwagger() {
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);
        
        // Just setup the configuration of the swagger API
        BeanConfig config = new BeanConfig();
        config.setConfigId("AcmeAire-CustomerService");
        config.setTitle("AcmeAire + CustomerService ");
        config.setVersion("v1");
        config.setSchemes(new String[] {"http"});
        config.setBasePath(apiPath);
        config.setResourcePackage("com.acmeair");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
    
    public CustomerServiceApp() {
        registerClasses(CustomerREST.class, LoginREST.class, CustomerConfiguration.class, CustomerLoaderREST.class);
    }

    @Provider
    private static class CustomerExceptionHandler implements ExceptionMapper<InvocationException> {

        @Override
        public Response toResponse(InvocationException e) {
            return Response.status(e.getStatus()).entity(e.getErrorData()).build();
        }
    }
}
