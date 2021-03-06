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


public enum Process {

    DIGITAL_POST_INFO("urn:no:difi:profile:digitalpost:info:ver1.0"),
    DIGITAL_POST_VEDTAK("urn:no:difi:profile:digitalpost:vedtak:ver1.0"),
    ;

    private final String value;

    Process(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
