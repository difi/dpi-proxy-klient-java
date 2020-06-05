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

import java.util.Objects;

public class DocumentType {

    private String type;

    private String standard;

    public DocumentType() {
    }

    public String getType() {
        return this.type;
    }

    public String getStandard() {
        return this.standard;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentType that = (DocumentType) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(standard, that.standard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, standard);
    }

    public String toString() {
        return "DocumentType(type=" + this.getType() + ", standard=" + this.getStandard() + ")";
    }
}
