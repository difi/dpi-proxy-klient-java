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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class BusinessScope implements Serializable {

    protected List<Scope> scope;

    public BusinessScope() {
    }

    public BusinessScope(List<Scope> scope) {
        this.scope = scope;
    }

    /**
     * Gets the value of the scope property.
     */
    public List<Scope> getScope() {
        if (scope == null) {
            scope = new ArrayList<>();
        }
        return this.scope;
    }

    public BusinessScope addScope(Scope scope) {
        getScope().add(scope);
        return this;
    }

    public BusinessScope addScopes(Scope... scopes) {
        getScope().addAll(Arrays.asList(scopes));
        return this;
    }

    public void setScope(List<Scope> scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessScope that = (BusinessScope) o;
        return Objects.equals(scope, that.scope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scope);
    }

    public String toString() {
        return "BusinessScope(scope=" + this.getScope() + ")";
    }
}
