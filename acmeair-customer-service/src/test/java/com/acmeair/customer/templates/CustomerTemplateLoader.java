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
package com.acmeair.customer.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.acmeair.entities.Customer;
import com.acmeair.morphia.entities.CustomerAddressImpl;
import com.acmeair.morphia.entities.CustomerImpl;
import com.acmeair.morphia.entities.CustomerSessionImpl;

import java.text.SimpleDateFormat;

import static com.seanyinx.github.unit.scaffolding.Randomness.nextId;
import static com.seanyinx.github.unit.scaffolding.Randomness.uniquify;

public class CustomerTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(CustomerAddressImpl.class).addTemplate("valid", new Rule() {{
            add("streetAddress1", uniquify("broad way ave "));
            add("streetAddress2", uniquify("white plain street "));
            add("city", random(String.class, "Beijing", "NYC", "Chicago", "White Plains", "Boston"));
            add("stateProvince", random(String.class, "NY", "TX", "TK"));
            add("country", random(String.class, "USA", "China", "Canada"));
            add("postalCode", uniquify("60"));
        }});

        Fixture.of(CustomerImpl.class).addTemplate("valid", new Rule() {{
            add("_id", String.valueOf(nextId()));
            add("password", uniquify("password"));
            add("status", random(Customer.MemberShipStatus.class));
            add("total_miles", random(Integer.class, range(1, 100000)));
            add("miles_ytd", random(Integer.class, range(1, 100000)));
            add("address", one(CustomerAddressImpl.class, "valid"));
            add("phoneNumber", random(String.class, uniquify("086"), uniquify("065")));
            add("phoneNumberType", random(Customer.PhoneType.class));
        }});

        Fixture.of(CustomerSessionImpl.class).addTemplate("base", new Rule() {{
            add("_id", uniquify("session-id"));
            add("customerid", uniquify("customer-id"));
            add("lastAccessedTime", instant("now"));
        }});

        Fixture.of(CustomerSessionImpl.class).addTemplate("valid").inherits("base", new Rule() {{
            add("timeoutTime", afterDate("2099-01-01", new SimpleDateFormat("yyyy-MM-dd")));
        }});

        Fixture.of(CustomerSessionImpl.class).addTemplate("expired").inherits("base", new Rule() {{
            add("timeoutTime", beforeDate("2009-01-01", new SimpleDateFormat("yyyy-MM-dd")));
        }});
    }
}
