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
package no.difi.sdp.client2.domain.fysisk_post;

import no.difi.sdp.client2.domain.ForretningsMelding;
import no.difi.sdp.client2.domain.ForretningsMeldingType;
import no.difi.sdp.client2.domain.TekniskMottaker;

import java.util.List;

public class FysiskPost extends ForretningsMelding {

    private KonvoluttAdresse mottaker;
    private Posttype posttype;
    private Utskriftsfarge utskriftsfarge;
    private Returhaandtering returhaandtering;
    private KonvoluttAdresse returadresse;
    private List<Printinstruksjon> printinstruksjoner;

    public FysiskPost() {
        super(ForretningsMeldingType.PRINT);
    }

    @Deprecated
    public KonvoluttAdresse getAdresse() {
        return mottaker;
    }

    public KonvoluttAdresse getMottaker() {
        return mottaker;
    }

    public Posttype getPosttype() {
        return posttype;
    }

    public Utskriftsfarge getUtskriftsfarge() {
        return utskriftsfarge;
    }

    public Returhaandtering getReturhaandtering() {
        return returhaandtering;
    }

    public KonvoluttAdresse getReturadresse() {
        return returadresse;
    }

    @Deprecated
    public TekniskMottaker getUtskriftsleverandoer() {
        return null;
    }

    public List<Printinstruksjon> getPrintinstruksjoner() {
        return printinstruksjoner;
    }

    public static FysiskPost.Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private final FysiskPost fysiskPost;
        private boolean built = false;

        private Builder() {
            fysiskPost = new FysiskPost();
        }

        public Builder adresse(KonvoluttAdresse adresse) {
            fysiskPost.mottaker = adresse;
            return this;
        }

        public Builder sendesMed(Posttype posttype) {
            fysiskPost.posttype = posttype;
            return this;
        }

        @Deprecated
        public Builder utskrift(Utskriftsfarge utskriftsfarge, TekniskMottaker utskriftsleverandoer) {
            return utskrift(utskriftsfarge);
        }

        public Builder utskrift(Utskriftsfarge utskriftsfarge) {
            fysiskPost.utskriftsfarge = utskriftsfarge;
            return this;
        }

        public Builder retur(Returhaandtering haandtering, KonvoluttAdresse returadresse) {
            fysiskPost.returhaandtering = haandtering;
            fysiskPost.returadresse = returadresse;
            return this;
        }

        public Builder printinstruksjoner(List<Printinstruksjon> printinstruksjoner) {
            fysiskPost.printinstruksjoner = printinstruksjoner;
            return this;
        }

        public FysiskPost build() {
            if (built) throw new IllegalStateException("Can't build twice");
            built = true;
            return fysiskPost;
        }

    }
}
