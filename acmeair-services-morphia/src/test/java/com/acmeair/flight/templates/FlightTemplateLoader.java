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
package com.acmeair.flight.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.acmeair.morphia.entities.FlightImpl;
import com.acmeair.morphia.entities.FlightSegmentImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import static com.seanyinx.github.unit.scaffolding.Randomness.nextId;
import static com.seanyinx.github.unit.scaffolding.Randomness.uniquify;

public class FlightTemplateLoader implements TemplateLoader {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void load() {
        Fixture.of(FlightSegmentImpl.class).addTemplate("valid", new Rule() {{
            add("_id", someId());
            add("originPort", uniquify("originPort"));
            add("destPort", uniquify("destPort"));
            add("miles", random(Integer.class));
        }});

        Fixture.of(FlightImpl.class).addTemplate("valid", new Rule() {{
            add("_id", someId());
            add("flightSegmentId", someId());
            add("scheduledDepartureTime", beforeDate("2017-04-01", dateFormat));
            add("scheduledArrivalTime", afterDate("2017-04-01", dateFormat));
            add("firstClassBaseCost", random(BigDecimal.class));
            add("economyClassBaseCost", random(BigDecimal.class));
            add("numFirstClassSeats", random(Integer.class));
            add("numEconomyClassSeats", random(Integer.class));
            add("airplaneTypeId", someId());
            add("flightSegment", one(FlightSegmentImpl.class, "valid"));
        }});
    }

    private String someId() {
        return String.valueOf(nextId());
    }
}
