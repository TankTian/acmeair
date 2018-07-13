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

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Profile("!SpringCloud")
@Service
class CseAuthenticationCommand extends AuthenticationCommand {

  private static final Logger logger = LoggerFactory.getLogger(CseAuthenticationCommand.class);

  @Value("${customer.service.name:customerServiceApp}")
  private String customerServiceName;

  CseAuthenticationCommand() {
    super(RestTemplateBuilder.create());
  }

  @Override
  protected String getCustomerServiceAddress() {
    return "cse://" + customerServiceName;
  }

  @Override
  protected Object validationRequest(String sessionId) {
    Map<String, String> map = new HashMap<>();
    map.put("sessionId", sessionId);
    return map;
  }
}
