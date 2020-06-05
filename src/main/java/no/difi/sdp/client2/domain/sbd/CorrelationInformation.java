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

import java.time.ZonedDateTime;


/**
 * <p>Java class for CorrelationInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CorrelationInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestingDocumentCreationDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="RequestingDocumentInstanceIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExpectedResponseDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class CorrelationInformation {
    protected ZonedDateTime requestingDocumentCreationDateTime;


    protected String requestingDocumentInstanceIdentifier;
    protected ZonedDateTime expectedResponseDateTime;

    public ZonedDateTime getRequestingDocumentCreationDateTime() {
        return this.requestingDocumentCreationDateTime;
    }

    public String getRequestingDocumentInstanceIdentifier() {
        return this.requestingDocumentInstanceIdentifier;
    }

    public ZonedDateTime getExpectedResponseDateTime() {
        return this.expectedResponseDateTime;
    }

    public void setRequestingDocumentCreationDateTime(ZonedDateTime requestingDocumentCreationDateTime) {
        this.requestingDocumentCreationDateTime = requestingDocumentCreationDateTime;
    }

    public void setRequestingDocumentInstanceIdentifier(String requestingDocumentInstanceIdentifier) {
        this.requestingDocumentInstanceIdentifier = requestingDocumentInstanceIdentifier;
    }

    public void setExpectedResponseDateTime(ZonedDateTime expectedResponseDateTime) {
        this.expectedResponseDateTime = expectedResponseDateTime;
    }

    public String toString() {
        return "CorrelationInformation(requestingDocumentCreationDateTime=" + this.getRequestingDocumentCreationDateTime() + ", requestingDocumentInstanceIdentifier=" + this.getRequestingDocumentInstanceIdentifier() + ", expectedResponseDateTime=" + this.getExpectedResponseDateTime() + ")";
    }
}
