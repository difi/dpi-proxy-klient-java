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

import com.fasterxml.jackson.annotation.JsonIgnore;
import no.difi.sdp.client2.domain.ForretningsMelding;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;


public class StandardBusinessDocument {


    private StandardBusinessDocumentHeader standardBusinessDocumentHeader;

    private Object any;

    @JsonIgnore
    public ForretningsMelding getForretningsMelding() {
        return (ForretningsMelding) getAny();
    }

    @JsonIgnore
    public MessageInfo getMessageInfo() {
        return new MessageInfo(getMessageType(), getReceiverIdentifier(), getSenderIdentifier(), getConversationId(), getDocumentId());
    }

    @JsonIgnore
    public String getSenderIdentifier() {
        return getStandardBusinessDocumentHeader().getFirstSender()
                .map(Partner::getIdentifier)
                .map(PartnerIdentification::getStrippedValue)
                .orElse(null);
    }

    @JsonIgnore
    public String getReceiverIdentifier() {
        return getStandardBusinessDocumentHeader().getFirstReceiver()
                .map(Partner::getIdentifier)
                .map(PartnerIdentification::getStrippedValue)
                .orElse(null);
    }

    @JsonIgnore
    public String getConversationId() {
        return getOptionalConversationId()
                .orElseThrow(RuntimeException::new);
    }

    @JsonIgnore
    public Optional<String> getOptionalConversationId() {
        return findScope(ScopeType.CONVERSATION_ID)
                .map(Scope::getInstanceIdentifier);
    }

    @JsonIgnore
    public List<Scope> getScopes() {
        return getStandardBusinessDocumentHeader()
                .getBusinessScope()
                .getScope();
    }

    public Scope getScope(ScopeType scopeType) {
        return findScope(scopeType)
                .orElseThrow(() -> new RuntimeException(String.format("Missing scope %s", scopeType.name())));
    }

    public Optional<Scope> findScope(ScopeType scopeType) {
        return getScopes()
                .stream()
                .filter(scope -> scopeType.toString().equals(scope.getType()) || scopeType.name().equals(scope.getType()))
                .findAny();
    }

    @JsonIgnore
    public String getMessageType() {
        return getStandardBusinessDocumentHeader().getDocumentIdentification().getType();
    }

    @JsonIgnore
    public String getStandard() {
        return getStandardBusinessDocumentHeader().getDocumentIdentification().getStandard();
    }

    @JsonIgnore
    public String getProcess() {
        return getScope(ScopeType.CONVERSATION_ID)
                .getIdentifier();
    }

    @JsonIgnore
    public String getDocumentId() {
        return getStandardBusinessDocumentHeader().getDocumentIdentification().getInstanceIdentifier();
    }

    @JsonIgnore
    public String getMessageId() {
        return getDocumentId();
    }

    @JsonIgnore
    public Optional<String> getOptionalMessageId() {
        return Optional.ofNullable(getDocumentId());
    }

    @JsonIgnore
    public Optional<ZonedDateTime> getExpectedResponseDateTime() {
        return getScope(ScopeType.CONVERSATION_ID)
                .getScopeInformation()
                .stream().findFirst()
                .map(CorrelationInformation::getExpectedResponseDateTime);
    }

    public StandardBusinessDocumentHeader getStandardBusinessDocumentHeader() {
        return this.standardBusinessDocumentHeader;
    }

    public Object getAny() {
        return this.any;
    }

    public void setStandardBusinessDocumentHeader(StandardBusinessDocumentHeader standardBusinessDocumentHeader) {
        this.standardBusinessDocumentHeader = standardBusinessDocumentHeader;
    }

    public void setAny(Object any) {
        this.any = any;
    }

    public String toString() {
        return "StandardBusinessDocument(standardBusinessDocumentHeader=" + this.getStandardBusinessDocumentHeader() + ", any=" + this.getAny() + ")";
    }
}
