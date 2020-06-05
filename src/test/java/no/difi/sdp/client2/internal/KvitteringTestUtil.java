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

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class KvitteringTestUtil {
    public static String Feilmelding() {
        return TilXmlDokument("Feilmelding.xml");
    }

    public static String Leveringskvittering() {
        return TilXmlDokument("Leveringskvittering.xml");
    }

    public static String Mottakskvittering() {
        return TilXmlDokument("Mottakskvittering.xml");
    }

    public static String Returpostkvittering() {
        return TilXmlDokument("Returpostkvittering.xml");
    }

    public static String VarslingFeiletKvittering() {
        return TilXmlDokument("VarslingFeiletKvittering.xml");
    }

    public static String Åpningskvittering() {
        return TilXmlDokument("Åpningskvittering.xml");
    }

    public static String TilXmlDokument(String kvittering) {
        try {
            return IOUtils.toString(KvitteringTestUtil.class.getResourceAsStream("/kvitteringer/" + kvittering), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
