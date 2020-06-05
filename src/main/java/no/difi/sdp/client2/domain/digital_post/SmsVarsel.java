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
package no.difi.sdp.client2.domain.digital_post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SmsVarsel extends Varsel {

    private static final Logger LOG = LoggerFactory.getLogger(SmsVarsel.class);

    private SmsVarsel(String varslingsTekst) {
        super(varslingsTekst);
    }

    /**
     * Integrasjonspunkt er ansvarlig for å hente mobilnummer til mottaker.
     * @see #builder(String)
     */
    @Deprecated
    public static Builder builder(String mobilnummer, String varslingsTekst) {
        return new Builder(varslingsTekst);
    }

    /**
     * @param varslingsTekst Avsenderstyrt varslingstekst som skal inngå i varselet.
     */
    public static Builder builder(String varslingsTekst) {
        return new Builder(varslingsTekst);
    }

    @Deprecated
    public String getMobilnummer() {
        LOG.warn("NOT SUPPORTED");
        return null;
    }

    public static class Builder {
        private SmsVarsel target;
        private boolean built = false;

        private Builder(String varslingsTekst) {
            target = new SmsVarsel(varslingsTekst);
        }

        @Deprecated
        public Builder varselEtterDager(List<Integer> varselEtterDager) {
            LOG.warn("NOT SUPPORTED");
            return this;
        }

        public SmsVarsel build() {
            if (built) throw new IllegalStateException("Can't build twice");
            built = true;
            return target;
        }
    }
}
