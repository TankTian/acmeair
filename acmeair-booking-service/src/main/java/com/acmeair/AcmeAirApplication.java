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
package com.acmeair;

import io.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class AcmeAirApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcmeAirApplication.class, args);
    }
    
    @Configuration
    @Profile("SpringCloud")
    @EnableDiscoveryClient
    class ConsulServiceDiscoveryConfig {
        // Using the spring cloud discovery client to access the customer service
        // You can enable this feature by using the profile of SpringCloud
    }

    @Configuration
    @Profile("!SpringCloud")
    @EnableServiceComb
    class ServiceCombConfig {
        // Here we just enable ServiceComb by default
    }
}
