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
package com.acmeair.config;

import com.acmeair.loader.FlightLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Profile("perf")
@Configuration
class FlightLoaderConfig {
    private static final Logger logger = LoggerFactory.getLogger(FlightLoaderConfig.class);
    
    @Autowired
    private FlightLoader flightLoader;

    @PostConstruct
    void populateFlights() {
        try {
            long start = System.currentTimeMillis();
            logger.info("Start loading flights");
            flightLoader.loadFlights();
            long stop = System.currentTimeMillis();
            double length = (stop - start) / 1000.0;
            logger.info("Finished loading flights in {} seconds", length);
        } catch (Exception e) {
            logger.error("Failed to fights", e);
        }
    }
}