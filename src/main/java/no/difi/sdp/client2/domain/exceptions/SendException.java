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
package no.difi.sdp.client2.domain.exceptions;

/**
 * Felles superklasse for alle Exceptions som oppstår under sending/mottak av forespørsler mot meldingsformidler.
 */
public class SendException extends SikkerDigitalPostException {

    private final AntattSkyldig antattSkyldig;

    public SendException(String message, AntattSkyldig antattSkyldig, Exception e) {
        super(message, e);
        this.antattSkyldig = antattSkyldig;
    }

    public AntattSkyldig getAntattSkyldig() {
        return antattSkyldig;
    }

    public enum AntattSkyldig {
        /**
         * Feilen er trolig forårsaket av en feil i klienten eller klientoppsettet.
         * <p>
         * Å forsøke samme forespørsel igjen vil sannsynligvis ikke gjøre noe med situasjonen.
         */
        KLIENT,

        /**
         * Feilen er trolig forårsaket av en feil i meldingsformidleren.
         * <p>
         * Det kan fungere å prøve forespørselen igjen senere.
         */
        SERVER,

        /**
         * Uvisst om feilen er forårsaket av klienten eller meldingsformidleren.
         */
        UKJENT;

        public static AntattSkyldig fraHttpStatusCode(int statusKode) {

            if (statusKode / 100 == 5) {
                return SERVER;
            } else if (statusKode / 100 == 4) {
                return KLIENT;
            } else {
                return UKJENT;
            }
        }
    }

}
