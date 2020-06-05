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

public enum ScopeType {
    CONVERSATION_ID("ConversationId"),
    SENDER_REF("SenderRef"),
    RECEIVER_REF("ReceiverRef"),
    ;

    private String fullname;

    ScopeType(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return this.fullname;
    }
}
