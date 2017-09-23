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

import org.junit.Ignore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
        classes = AcmeAirApplication.class,
        webEnvironment = RANDOM_PORT,
        properties = {
                "flights.max.per.segment=3",
                "server.port=8092",
                "zuul.routes.bookings.url=http://localhost:8092",
                "spring.data.mongodb.host=localhost",
                "spring.data.mongodb.port=27017"
        })
@ActiveProfiles({"mongodb", "SpringCloud", "test", "perf"})
@Ignore("Ignore the mongoDB test which could not run it")
public class AcmeAirApplicationMongodbTest extends AcmeAirApplicationTestBase {
}
