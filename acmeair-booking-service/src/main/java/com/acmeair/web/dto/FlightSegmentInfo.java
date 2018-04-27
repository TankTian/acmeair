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

import com.acmeair.entities.FlightSegment;

import java.util.Objects;

public class FlightSegmentInfo {

	private String id;
	private String originPort;
	private String destPort;
	private int miles;
	
	public FlightSegmentInfo() {
		
	}
	public FlightSegmentInfo(FlightSegment flightSegment) {
		this.id = flightSegment.getFlightName();
		this.originPort = flightSegment.getOriginPort();
		this.destPort = flightSegment.getDestPort();
		this.miles = flightSegment.getMiles();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String Id) {
		this.id = Id;
	}
	public String getOriginPort() {
		return originPort;
	}
	public void setOriginPort(String originPort) {
		this.originPort = originPort;
	}
	public String getDestPort() {
		return destPort;
	}
	public void setDestPort(String destPort) {
		this.destPort = destPort;
	}
	public int getMiles() {
		return miles;
	}
	public void setMiles(int miles) {
		this.miles = miles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FlightSegmentInfo that = (FlightSegmentInfo) o;
		return miles == that.miles &&
				Objects.equals(id, that.id) &&
				Objects.equals(originPort, that.originPort) &&
				Objects.equals(destPort, that.destPort);
	}

	@Override
	public String toString() {
		return "FlightSegmentInfo{" +
				"Id='" + id + '\'' +
				", originPort='" + originPort + '\'' +
				", destPort='" + destPort + '\'' +
				", miles=" + miles +
				'}';
	}
}
