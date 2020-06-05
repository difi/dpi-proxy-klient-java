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
package no.digipost.api.representations;

import org.w3.xmldsig.Reference;

@Deprecated
public class KvitteringsReferanse {

    private final String marshalled;

    private KvitteringsReferanse(String marshalledReference) {
        marshalled = marshalledReference;
    }

    @Deprecated
    public static Builder builder(String marshalledReference) {
        return new Builder(marshalledReference);
    }

    @Deprecated
    public String getMarshalled() {
        return marshalled;
    }

    @Deprecated
    public Reference getUnmarshalled() {
        return null;
    }


    @Deprecated
    public static class Builder {
        private KvitteringsReferanse target;
        private boolean built = false;

        private Builder(String marshalledReference) {
            this.target = new KvitteringsReferanse(marshalledReference);
        }

        @Deprecated
        public KvitteringsReferanse build() {
            if (built) {
                throw new IllegalStateException("Kan ikke bygges flere ganger.");
            }
            built = true;
            return target;
        }
    }
}
