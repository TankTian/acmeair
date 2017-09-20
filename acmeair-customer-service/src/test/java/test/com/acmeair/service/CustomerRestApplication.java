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
package test.com.acmeair.service;

import com.acmeair.loader.CustomerLoaderREST;
import com.acmeair.service.CustomerService;
import com.acmeair.service.CustomerServiceSupport;
import com.acmeair.service.KeyGenerator;
import com.acmeair.web.CustomerREST;
import com.acmeair.web.CustomerValidationRuleConfig;
import com.acmeair.web.LoginREST;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.ws.rs.ApplicationPath;


@SpringBootApplication
@Import(CustomerValidationRuleConfig.class)
public class CustomerRestApplication {

    private final KeyGenerator    keyGenerator    = Mockito.mock(KeyGenerator.class);
    private final CustomerService customerService = Mockito.mock(CustomerServiceSupport.class);

    public static void main(String[] args) {
        SpringApplication.run(CustomerRestApplication.class, args);
    }

    @Bean
    KeyGenerator keyGenerator() {
        return keyGenerator;
    }

    @Bean
    CustomerService customerService() {
        return customerService;
    }

    @Configuration
    @ApplicationPath("/rest")
    public static class CustomerRestApp extends ResourceConfig {
        public CustomerRestApp() {
            registerClasses(CustomerREST.class, LoginREST.class, CustomerLoaderREST.class);
            property(ServletProperties.FILTER_FORWARD_ON_404, true);
        }
    }
}
