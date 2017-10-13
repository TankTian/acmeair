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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

abstract class RemoteCustomerLoader implements CustomerLoader {

    private final RestTemplate restTemplate;

    @Value("${customer.service.name:customerServiceApp}")
    String customerServiceName;

    RemoteCustomerLoader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return !clientHttpResponse.getStatusCode().is2xxSuccessful();
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
                throw new RuntimeException("Remote customer loader returns status code "
                                           + clientHttpResponse.getStatusCode());
            }
        });
    }

    @Override
    public void loadCustomers(long numCustomers) {
        restTemplate.postForEntity(
                getCustomerServiceAddress() + "/info/loader/load?number={numberOfCustomers}",
                null,
                String.class,
                numCustomers
        );
    }

    protected abstract String getCustomerServiceAddress();
}
