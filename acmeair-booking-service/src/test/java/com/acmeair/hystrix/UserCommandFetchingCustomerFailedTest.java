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
package com.acmeair.hystrix;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import com.acmeair.service.UserService;
import org.apache.http.HttpStatus;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

import static com.seanyinx.github.unit.scaffolding.AssertUtils.expectFailing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserCommandFetchingCustomerFailedTest {
    @Rule
    public final PactProviderRule providerRule = new PactProviderRule("CustomerService", this);

    private final UserService userService = new TestUserCommand(providerRule.getConfig().url());

    @Pact(consumer = "UserService")
    public PactFragment createFragment(PactDslWithProvider pactDslWithProvider) {
        return pactDslWithProvider
                .given("No customer Mike found")
                .uponReceiving("a request for Mike")
                .path("/rest/api/customer/mike")
                .method("GET")
                .willRespondWith()
                .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .toFragment();
    }

    @Test
    @PactVerification
    public void fetchesExpectedCustomer() throws IOException {
        try {
            userService.getCustomerInfo("mike");
            expectFailing(NoSuchElementException.class);
        } catch (NoSuchElementException e) {
            assertThat(e.getMessage(), is("No such customer with id mike"));
        }
    }
}
