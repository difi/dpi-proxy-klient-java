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
package no.difi.sdp.client2.internal;

import no.difi.sdp.client2.ExceptionMapper;
import no.difi.sdp.client2.KlientKonfigurasjon;
import no.difi.sdp.client2.domain.Databehandler;
import no.difi.sdp.client2.domain.Dokumentpakke;
import no.difi.sdp.client2.domain.exceptions.SendException;
import no.difi.sdp.client2.domain.sbd.StandardBusinessDocument;
import no.difi.sdp.client2.internal.http.IntegrasjonspunktKvittering;
import no.difi.sdp.client2.internal.http.MessageSender;
import org.apache.http.HttpRequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public class IntegrasjonspunktMessageSenderFacade {

    private final MessageSender messageSender;
    private ExceptionMapper exceptionMapper = new ExceptionMapper();

    public IntegrasjonspunktMessageSenderFacade(final KlientKonfigurasjon klientKonfigurasjon) {
        final int connectTimeoutInMillis = (int) klientKonfigurasjon.getConnectTimeoutInMillis();
        final int socketTimeoutInMillis = (int) klientKonfigurasjon.getSocketTimeoutInMillis();
        final int connectionRequestTimeoutInMillis = (int) klientKonfigurasjon.getConnectionRequestTimeoutInMillis();

        MessageSender.Builder messageSenderBuilder = MessageSender.create(klientKonfigurasjon.getMiljo().getIntegrasjonspunktRoot())
            .withConnectTimeout(connectTimeoutInMillis)
            .withSocketTimeout(socketTimeoutInMillis)
            .withConnectionRequestTimeout(connectionRequestTimeoutInMillis)
            .withDefaultMaxPerRoute(klientKonfigurasjon.getMaxConnectionPoolSize())
            .withMaxTotal(klientKonfigurasjon.getMaxConnectionPoolSize());


        if (klientKonfigurasjon.useProxy()) {
            messageSenderBuilder.withHttpProxy(klientKonfigurasjon.getProxyHost(), klientKonfigurasjon.getProxyPort(), klientKonfigurasjon.getProxyScheme());
        }

        // Legg til http request interceptors fra konfigurasjon pluss vår egen.
        HttpRequestInterceptor[] httpRequestInterceptors = Arrays.copyOf(klientKonfigurasjon.getHttpRequestInterceptors(), klientKonfigurasjon.getHttpRequestInterceptors().length + 1);
        httpRequestInterceptors[httpRequestInterceptors.length - 1] = new AddClientVersionInterceptor();
        messageSenderBuilder.withHttpRequestInterceptors(httpRequestInterceptors);

        messageSenderBuilder.withHttpResponseInterceptors(klientKonfigurasjon.getHttpResponseInterceptors());

        messageSender = messageSenderBuilder.build();
    }

    public void send(StandardBusinessDocument forsendelse, final Dokumentpakke sbdForsendelse) {
        performRequest(() -> messageSender.send(forsendelse, sbdForsendelse));
    }

    public void bekreft(final long id) {
        performRequest(() -> messageSender.bekreftKvittering(id));
    }

    private void performRequest(final Runnable request) {
        this.performRequest(() -> {
            request.run();
            return null;
        });
    }

    private <T> T performRequest(final Supplier<T> request) throws SendException {
        try {
            return request.get();
        } catch (RuntimeException e) {
            throw exceptionMapper.mapException(e);
        }
    }

    public void setExceptionMapper(final ExceptionMapper exceptionMapper) {
        this.exceptionMapper = exceptionMapper;
    }

    public Optional<IntegrasjonspunktKvittering> hentKvittering() {
        return performRequest(messageSender::hentKvittering);
    }
}
