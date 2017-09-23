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

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.HttpStatus;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

public class RemoteCustomerLoaderTest {
    @Rule
    public final PactProviderRule providerRule = new PactProviderRule("CustomerService", this);

    private final RemoteCustomerLoader customerLoader = new UnitTestRemoteCustomerLoader(providerRule.getConfig().url());

    @Pact(consumer = "RemoteCustomerLoader")
    public PactFragment createFragment(PactDslWithProvider pactDslWithProvider) throws JsonProcessingException {
        return pactDslWithProvider
                .given("Remote customer loader is available")
                .uponReceiving("a request to load customers")
                .path("/rest/info/loader/load")
                .query("number=5")
                .method("POST")
                .willRespondWith()
                .status(HttpStatus.SC_NO_CONTENT)
                .toFragment();
    }

    @Test
    @PactVerification
    public void requestsRemoteToLoadCustomers() throws IOException {
        customerLoader.loadCustomers(5);
    }
}
