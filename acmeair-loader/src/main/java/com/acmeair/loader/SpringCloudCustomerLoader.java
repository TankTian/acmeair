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
package com.acmeair.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("SpringCloud")
class SpringCloudCustomerLoader extends RemoteCustomerLoader {
    private static final Logger logger = LoggerFactory.getLogger(SpringCloudCustomerLoader.class);

    @Autowired
    private LoadBalancerClient loadBalancer;

    SpringCloudCustomerLoader() {
        super(new RestTemplate());
    }

    protected String getCustomerServiceAddress() {
        return customerServiceAddress() + "/rest";
    }

    protected String customerServiceAddress() {
        String address = loadBalancer.choose(customerServiceName).getUri().toString();
        logger.info("Just get the address {} from LoadBalancer.", address);
        return address;
    }
}
