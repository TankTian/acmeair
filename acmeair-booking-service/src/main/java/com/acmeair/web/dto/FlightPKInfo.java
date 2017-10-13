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
// this sourcecode is modified by Huawei Technologies Co., Ltd.
package com.acmeair.web.dto;

import java.util.Objects;

public class FlightPKInfo {

	private String id;
	private String flightSegmentId;
	
	FlightPKInfo(){}
	FlightPKInfo(String flightSegmentId,String id){
		this.id = id;
		this.flightSegmentId = flightSegmentId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFlightSegmentId() {
		return flightSegmentId;
	}
	public void setFlightSegmentId(String flightSegmentId) {
		this.flightSegmentId = flightSegmentId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FlightPKInfo that = (FlightPKInfo) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(flightSegmentId, that.flightSegmentId);
	}

	@Override
	public String toString() {
		return "FlightPKInfo{" +
				"id='" + id + '\'' +
				", flightSegmentId='" + flightSegmentId + '\'' +
				'}';
	}
}
