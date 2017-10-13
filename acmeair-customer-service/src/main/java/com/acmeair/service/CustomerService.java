/*******************************************************************************
* Copyright (c) 2013-2015 IBM Corp.
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
// this sourcecode is modified by Huawei Technologies Co., Ltd.
package com.acmeair.service;

import com.acmeair.entities.Customer;
import com.acmeair.entities.CustomerSession;
import com.acmeair.morphia.entities.CustomerAddressImpl;

public interface CustomerService {
    Customer createCustomer(String username, String password, Customer.MemberShipStatus status, int total_miles, int miles_ytd, String phoneNumber,
                                   Customer.PhoneType phoneNumberType, CustomerAddressImpl address
    );
    
    CustomerAddressImpl createAddress(String streetAddress1, String streetAddress2, String city, String stateProvince, String country, String postalCode);
    
    Customer updateCustomer(Customer customer);
    
    Customer getCustomerByUsername(String username);
    
    boolean validateCustomer(String username, String password);
    
    Customer getCustomerByUsernameAndPassword(String username, String password);
    
    CustomerSession validateSession(String sessionid);
    
    CustomerSession createSession(String customerId);
    
    void invalidateSession(String sessionid);
    
    Long count();
    
    Long countSessions();
}
