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
package no.difi.sdp.client2.smoke;

import no.difi.sdp.client2.ObjectMother;
import no.difi.sdp.client2.domain.AktoerOrganisasjonsnummer;
import no.difi.sdp.client2.domain.Miljo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.URI;


@Disabled("This test runs the client against a running 'Integrasjonspunkt'.")
public class SmokeTest {

    private static final AktoerOrganisasjonsnummer avsenderOrgnr = ObjectMother.POSTEN_ORGNR;
    Miljo LOKAL = new Miljo(URI.create("http://localhost:9093"));


    @Test
    public void send_simple_digital_message() {

        new SmokeTestHelper(LOKAL)
                .create_digital_forsendelse(ObjectMother.avsender(avsenderOrgnr))
                .send()
                .fetch_receipt()
                .expect_receipt_to_be_leveringskvittering()
                .confirm_receipt();
    }


    @Test
    public void send_simple_fysisk_post_message() {
        new SmokeTestHelper(LOKAL)
            .create_print_forsendelse()
            .send()
            .fetch_receipt()
            .expect_receipt_to_be_leveringskvittering()
            .confirm_receipt();
    }


    @Test
    public void send_ehf_message() {
        new SmokeTestHelper(LOKAL)
            .create_ehf_forsendelse(ObjectMother.avsender(avsenderOrgnr))
            .send()
            .fetch_receipt()
            .expect_receipt_to_be_leveringskvittering()
            .confirm_receipt();
    }

    @Test
    public void hentKvitteringer() {
        new SmokeTestHelper(LOKAL).fetch_receipt();
    }

}
