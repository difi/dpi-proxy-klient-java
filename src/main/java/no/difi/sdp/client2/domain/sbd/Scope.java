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

import java.util.ArrayList;
import java.util.List;


public class Scope {

    protected String type;

    protected String instanceIdentifier;

    protected String identifier;

    protected List<CorrelationInformation> scopeInformation;

    public Scope() {
    }

    public Scope(String type, String instanceIdentifier, String identifier, List<CorrelationInformation> scopeInformation) {
        this.type = type;
        this.instanceIdentifier = instanceIdentifier;
        this.identifier = identifier;
        this.scopeInformation = scopeInformation;
    }

    public List<CorrelationInformation> getScopeInformation() {
        if (scopeInformation == null) {
            scopeInformation = new ArrayList<>();
        }
        return this.scopeInformation;
    }

    public Scope addScopeInformation(CorrelationInformation correlationInformation) {
        getScopeInformation().add(correlationInformation);
        return this;
    }

    public String getType() {
        return this.type;
    }

    public String getInstanceIdentifier() {
        return this.instanceIdentifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInstanceIdentifier(String instanceIdentifier) {
        this.instanceIdentifier = instanceIdentifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setScopeInformation(List<CorrelationInformation> scopeInformation) {
        this.scopeInformation = scopeInformation;
    }

    public String toString() {
        return "Scope(type=" + this.getType() + ", instanceIdentifier=" + this.getInstanceIdentifier() + ", identifier=" + this.getIdentifier() + ", scopeInformation=" + this.getScopeInformation() + ")";
    }
}
