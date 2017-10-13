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
package com.acmeair.web;

import com.acmeair.config.AcmeAirConfiguration;
import com.acmeair.config.LoaderREST;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;

@Configuration
@Primary
public class AcmeAirApp extends ResourceConfig {

    @Value("${spring.jersey.application-path:/}")
    private String apiPath;

    public AcmeAirApp() {
        registerClasses(BookingsREST.class, FlightsREST.class);
        registerClasses(LoaderREST.class, AcmeAirConfiguration.class);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }

    @PostConstruct
    public void init() {
        // The init method is called
        configureSwagger();
    }

    private void configureSwagger() {
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);

        // Just setup the configuration of the swagger API
        BeanConfig config = new BeanConfig();
        config.setConfigId("AcmeAire-BookingService");
        config.setTitle("AcmeAire + BookingService ");
        config.setVersion("v1");
        config.setSchemes(new String[] {"http"});
        config.setBasePath(apiPath);
        config.setResourcePackage("com.acmeair");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}
