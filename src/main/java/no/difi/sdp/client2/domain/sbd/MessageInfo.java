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

/**
 * Contains information needed to identify a message: who the sender and the receiver is, the original journalpost
 * and the transaction (conversation).
 *
 * To be used with logging and receipts to reduce parameters in methods and dependency to the standard business document.
 */
public class MessageInfo {

    private final String messageType;
    private final String receiverOrgNumber;
    private final String senderOrgNumber;
    private final String conversationId;
    private final String messageId;

    public MessageInfo(String messageType, String receiverOrgNumber, String senderOrgNumber, String conversationId, String messageId) {
        this.messageType = messageType;
        this.receiverOrgNumber = receiverOrgNumber;
        this.senderOrgNumber = senderOrgNumber;
        this.conversationId = conversationId;
        this.messageId = messageId;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public String getReceiverOrgNumber() {
        return this.receiverOrgNumber;
    }

    public String getSenderOrgNumber() {
        return this.senderOrgNumber;
    }

    public String getConversationId() {
        return this.conversationId;
    }

    public String getMessageId() {
        return this.messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageInfo that = (MessageInfo) o;
        return Objects.equals(messageType, that.messageType) &&
                Objects.equals(receiverOrgNumber, that.receiverOrgNumber) &&
                Objects.equals(senderOrgNumber, that.senderOrgNumber) &&
                Objects.equals(conversationId, that.conversationId) &&
                Objects.equals(messageId, that.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageType, receiverOrgNumber, senderOrgNumber, conversationId, messageId);
    }

    public String toString() {
        return "MessageInfo(messageType=" + this.getMessageType() + ", receiverOrgNumber=" + this.getReceiverOrgNumber() + ", senderOrgNumber=" + this.getSenderOrgNumber() + ", conversationId=" + this.getConversationId() + ", messageId=" + this.getMessageId() + ")";
    }
}
