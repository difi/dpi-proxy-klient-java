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

import com.fasterxml.jackson.annotation.JsonValue;

public enum Sikkerhetsnivaa {

    /**
     * "Mellomhøyt" sikkerhetsnivå.
     *
     * Vanligvis passord.
     */
    NIVAA_3(3),

    /**
     * Offentlig godkjent to-faktor elektronisk ID.
     *
     * For eksempel BankID, Buypass eller Commfides.
     */
    NIVAA_4(4);

    private final int verdi;

    Sikkerhetsnivaa(int verdi) {
        this.verdi = verdi;
    }

    @JsonValue
    public int getVerdi() {
        return verdi;
    }
}
