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
package com.acmeair.booking.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.acmeair.morphia.entities.BookingImpl;

import static com.seanyinx.github.unit.scaffolding.Randomness.nextId;

public class BookingTemplateLoader implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(BookingImpl.class).addTemplate("valid", new Rule() {{
            add("_id", someId());
            add("flightId", someId());
            add("customerId", someId());
            add("dateOfBooking", instant("now"));
        }});
    }

    private String someId() {
        return String.valueOf(nextId());
    }
}
