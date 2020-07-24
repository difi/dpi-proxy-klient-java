/**
 * Copyright (C) Posten Norge AS
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package no.difi.sdp.client2.domain.fysisk_post;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class FysiskPostSerializer extends StdSerializer<FysiskPost> {
    public FysiskPostSerializer() {
        super(FysiskPost.class);
    }

    @Override
    public void serialize(FysiskPost value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("hoveddokument", value.getHoveddokument());
        gen.writeObjectField("posttype", value.getPosttype());
        gen.writeObjectField("utskriftsfarge", value.getUtskriftsfarge());

        gen.writeObjectField("mottaker", value.getMottaker());

        gen.writeFieldName("retur");
        gen.writeStartObject();
        gen.writeObjectField("mottaker", value.getReturadresse());
        gen.writeObjectField("returhaandtering", value.getReturhaandtering());

        gen.writeEndObject();

        Map<String, String> printinstruksjoner = value.getPrintinstruksjoner()
            .stream()
            .collect(Collectors.toMap(Printinstruksjon::getNavn, Printinstruksjon::getVerdi));

        gen.writeObjectField("printinstruksjoner", printinstruksjoner);


        gen.writeEndObject();
    }
}
