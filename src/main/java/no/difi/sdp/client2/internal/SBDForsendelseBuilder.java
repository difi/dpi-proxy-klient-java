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
package no.difi.sdp.client2.internal;

import no.difi.sdp.client2.domain.Avsender;
import no.difi.sdp.client2.domain.DatabehandlerOrganisasjonsnummer;
import no.difi.sdp.client2.domain.ForretningsMelding;
import no.difi.sdp.client2.domain.Forsendelse;
import no.difi.sdp.client2.domain.digital_post.DigitalPost;
import no.difi.sdp.client2.domain.sbd.StandardBusinessDocument;
import no.difi.sdp.client2.domain.sbd.StandardBusinessDocumentHeader;

import java.time.Clock;
import java.time.ZonedDateTime;

import static no.difi.sdp.client2.domain.Forsendelse.Type.DIGITAL;
import static no.difi.sdp.client2.domain.sbd.Process.DIGITAL_POST_INFO;
import static no.difi.sdp.client2.domain.sbd.Process.DIGITAL_POST_VEDTAK;

public class SBDForsendelseBuilder {
    public static StandardBusinessDocument buildSBD(DatabehandlerOrganisasjonsnummer databehandler, Forsendelse forsendelse) {
        Clock clock = Clock.systemDefaultZone();
        return buildSBD(databehandler,forsendelse, clock);
    }

    public static StandardBusinessDocument buildSBD(DatabehandlerOrganisasjonsnummer databehandler, Forsendelse forsendelse, Clock clock) {
        //SBD
        ForretningsMelding forretningsMelding = forsendelse.getForretningsMelding();
        forretningsMelding.setHoveddokument(forsendelse.getDokumentpakke().getHoveddokument().getFilnavn());
        final Avsender avsender = forsendelse.getAvsender();
        forretningsMelding.setAvsenderId(avsender.getAvsenderIdentifikator());
        forretningsMelding.setFakturaReferanse(avsender.getFakturaReferanse());

        if(forsendelse.type == DIGITAL) {
            forsendelse.getDokumentpakke().getHoveddokumentOgVedlegg()
                .filter(dokument -> dokument.getMetadataDocument().isPresent())
                .forEach(dokument -> ((DigitalPost)forretningsMelding).addMetadataMapping(dokument.getFileName(), dokument.getMetadataDocument().get().getFileName()));
        }

        StandardBusinessDocument sbd = new StandardBusinessDocument();

        String konversasjonsId = forsendelse.getKonversasjonsId();

        final StandardBusinessDocumentHeader sbdHeader = new StandardBusinessDocumentHeader.Builder().process(DIGITAL_POST_VEDTAK)
            .standard(forsendelse.type)
            .from(databehandler).onBehalfOf(avsender.getOrganisasjonsnummer())
            .to(forsendelse.getMottaker())
            .type(forretningsMelding.getType())
            .relatedToConversationId(konversasjonsId)
            .relatedToMessageId(konversasjonsId)
            .creationDateAndTime(ZonedDateTime.now(clock))
            .build();

        sbd.setStandardBusinessDocumentHeader(sbdHeader);
        sbd.setAny(forretningsMelding);

        return sbd;
    }

}
