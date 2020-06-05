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
package no.difi.sdp.client2.domain;

import no.difi.sdp.client2.domain.exceptions.SikkerDigitalPostException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Dokumentpakke {

    private Dokument hoveddokument;
    private List<Dokument> vedlegg = Collections.emptyList();

    private Dokumentpakke(Dokument hoveddokument) {
        this.hoveddokument = hoveddokument;
    }

    public Dokument getHoveddokument() {
        return hoveddokument;
    }

    public List<Dokument> getVedlegg() {
        return vedlegg;
    }

    public Stream<Dokument> getHoveddokumentOgVedlegg() {
        return Stream.concat(Stream.of(hoveddokument), vedlegg.stream());
    }

    public static Builder builder(Dokument hoveddokument) {
        return new Builder(hoveddokument);
    }

    public static class Builder {

        private final Dokumentpakke target;
        private boolean built = false;

        private Builder(Dokument hoveddokument) {
            target = new Dokumentpakke(hoveddokument);
        }

        public Builder vedlegg(List<Dokument> vedlegg) {
            boolean hasMetadatadokument = vedlegg.stream()
                .map(Dokument::getMetadataDocument)
                .anyMatch(Optional::isPresent);
            if(hasMetadatadokument) {
                throw new SikkerDigitalPostException("Vedlegg støtter ikke metadatadokument/utvidelser.");
            }

            target.vedlegg = new ArrayList<>(vedlegg);
            return this;
        }

        public Builder vedlegg(Dokument... vedlegg) {
            return this.vedlegg(asList(vedlegg));
        }

        public Dokumentpakke build() {
            if (built) throw new IllegalStateException("Can't build twice");
            built = true;
            return target;
        }
    }
}
