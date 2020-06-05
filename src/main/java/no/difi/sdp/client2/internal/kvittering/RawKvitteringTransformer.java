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
package no.difi.sdp.client2.internal.kvittering;

import no.difi.begrep.sdp.schema_v10.SDPKvittering;
import org.unece.cefact.namespaces.standardbusinessdocumentheader.StandardBusinessDocument;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class RawKvitteringTransformer {

    private static final JAXBContext context;
    private static final String contextPath = SDPKvittering.class.getPackage().getName() + ":org.unece.cefact.namespaces.standardbusinessdocumentheader";

    static {
        try {
            context = JAXBContext.newInstance(contextPath);
        } catch (JAXBException e) {
            throw new RuntimeException("Could not instantiate jaxb-context");
        }
    }

    public SimpleSBDMessage transform(String xml) {
        Unmarshaller unmarshaller;
        try {
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        try {
            final StandardBusinessDocument sbd = (StandardBusinessDocument) unmarshaller.unmarshal(new StringReader(xml));
            return new SimpleSBDMessage(sbd.getAny());
        } catch (JAXBException e) {
            throw new RuntimeException("Feil oppsto under parsing av kvittering", e);
        }
    }

}
