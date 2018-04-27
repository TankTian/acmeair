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
package com.acmeair.morphia.services;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.acmeair.entities.Booking;
import com.acmeair.entities.Flight;
import com.acmeair.morphia.entities.BookingImpl;
import com.acmeair.morphia.entities.FlightImpl;
import com.acmeair.morphia.repositories.BookingRepository;
import com.acmeair.service.FlightService;
import com.acmeair.service.KeyGenerator;
import com.acmeair.service.UserService;
import com.acmeair.web.dto.CustomerInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.seanyinx.github.unit.scaffolding.Randomness.uniquify;
import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest {
    @Mock
    private UserService userService;

    @Mock
    private FlightService flightService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private KeyGenerator keyGenerator;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private BookingImpl  booking;
    private CustomerInfo customer;
    private Flight       flight;

    @Before
    public void setUp() throws Exception {
        FixtureFactoryLoader.loadTemplates("com.acmeair.booking.templates");
        FixtureFactoryLoader.loadTemplates("com.acmeair.flight.templates");

        booking = Fixture.from(BookingImpl.class).gimme("valid");
        flight = Fixture.from(FlightImpl.class).gimme("valid");
        customer = new CustomerInfo(booking.getCustomerId(), null, null, 0, 0, null, null, null);

        when(keyGenerator.generate()).thenReturn(booking.getBookingId());
    }

    @Test
    public void deletesBookingWhenCancelled() {
        bookingService.cancelBooking(booking.getCustomerId(), booking.getBookingId());

        verify(bookingRepository).delete(booking.getBookingId());
    }

    @Test
    public void getsBookingIdWhenFlightsBooked() {
        when(flightService.getFlightByFlightId(booking.getFlightId(), null)).thenReturn(flight);
        when(userService.getCustomerInfo(booking.getCustomerId())).thenReturn(customer);

        String bookingId = bookingService.bookFlight(
                booking.getCustomerId(),
                uniquify("flightSegmentId"),
                booking.getFlightId()
        );

        assertThat(bookingId, is(booking.getBookingId()));
    }

    @Test
    public void getsBookingOfSpecifiedUser() {
        when(bookingRepository.findByCustomerId(booking.getCustomerId())).thenReturn(singletonList(booking));

        List<Booking> bookings = bookingService.getBookingsByUser(booking.getCustomerId());

        assertThat(bookings, contains(booking));
    }
}
