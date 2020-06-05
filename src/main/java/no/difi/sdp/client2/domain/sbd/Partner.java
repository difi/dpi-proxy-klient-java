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
package no.difi.sdp.client2.domain.sbd;

import java.util.ArrayList;
import java.util.List;

public class Partner {

    protected PartnerIdentification identifier;
    protected List<ContactInformation> contactInformation;

    public Partner(PartnerIdentification identifier) {
        this.identifier = identifier;
    }

    public Partner(final PartnerIdentification identifier, final List<ContactInformation> contactInformation) {
        this.identifier = identifier;
        this.contactInformation = contactInformation;
    }

    public Partner setIdentifier(PartnerIdentification identifier) {
        this.identifier = identifier;
        identifier.setPartner(this);
        return this;
    }

    public List<ContactInformation> getContactInformation() {
        if (contactInformation == null) {
            contactInformation = new ArrayList<>();
        }
        return this.contactInformation;
    }

    public Partner addContactInformation(ContactInformation contactInformation) {
        getContactInformation().add(contactInformation);
        return this;
    }

    public PartnerIdentification getIdentifier() {
        return this.identifier;
    }

    public void setContactInformation(List<ContactInformation> contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String toString() {
        return "Partner(identifier=" + this.getIdentifier() + ", contactInformation=" + this.getContactInformation() + ")";
    }
}
