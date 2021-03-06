/**
 * Copyright (C) Posten Norge AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package no.difi.sdp.client2;

import no.difi.sdp.client2.domain.exceptions.SendIOException;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import static no.difi.sdp.client2.ObjectMother.databehandler;
import static no.difi.sdp.client2.domain.exceptions.SendException.AntattSkyldig.UKJENT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;

public class SikkerDigitalPostKlientTest {

    private static final URI lokalTimeoutUrl = URI.create("http://10.255.255.1");

    @Test
    public void handles_connection_timeouts() {
        KlientKonfigurasjon klientKonfigurasjon = KlientKonfigurasjon.builder(lokalTimeoutUrl)
                .connectionTimeout(1, TimeUnit.MILLISECONDS)
                .build();

        SikkerDigitalPostKlient postklient = new SikkerDigitalPostKlient(databehandler(), klientKonfigurasjon);

        try {
            postklient.send(ObjectMother.digitalForsendelse());
            fail("Should fail");
        } catch (SendIOException e) {
            assertThat(e.getAntattSkyldig(), equalTo(UKJENT));
        }
    }

    @Test
    public void calls_http_interceptors() {
        final StringBuilder interceptorString = new StringBuilder();

        KlientKonfigurasjon klientKonfigurasjon = KlientKonfigurasjon.builder(lokalTimeoutUrl)
                .connectionTimeout(1, TimeUnit.MILLISECONDS)
                .httpRequestInterceptors(
                        (request, context) -> interceptorString.append("First interceptor called"),
                        (request, context) -> interceptorString.append(", and second too!"))
                .build();

        SikkerDigitalPostKlient postklient = new SikkerDigitalPostKlient(databehandler(), klientKonfigurasjon);

        try {
            postklient.send(ObjectMother.digitalForsendelse());
            fail("Fails");
        } catch (SendIOException e) {
            assertThat(interceptorString.toString(), equalTo("First interceptor called, and second too!"));
        }
    }
}
