/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.camel.component.openehr.composition;

import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;
import org.apache.camel.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * openEHR REST Composition API producer.
 *
 * @author Renaud Subiger
 * @since 1.0.0
 */
public class CompositionProducer extends DefaultProducer {

    private static final Logger LOG = LoggerFactory.getLogger(CompositionProducer.class);

    private final CompositionEndpoint endpoint;

    public CompositionProducer(CompositionEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    @Override
    public void process(Exchange exchange) {
        UUID ehrId = getEhrId(exchange);

        Object result;
        switch (endpoint.getOperation()) {
            case mergeCompositionEntity:
                result = endpoint.getClient().compositionEndpoint(ehrId).mergeCompositionEntity(exchange.getIn().getBody());
                break;
            case find:
                result = endpoint.getClient().compositionEndpoint(ehrId).find(getCompositionId(exchange), getType(exchange));
                break;
            default:
                throw new IllegalArgumentException("Unsupported operation");
        }
        exchange.getMessage().setBody(result);
    }

    private UUID getEhrId(Exchange exchange) {
        return ObjectHelper.isNotEmpty(exchange.getIn().getHeader(CompositionConstants.EHR_ID))
                ? exchange.getIn().getHeader(CompositionConstants.EHR_ID, UUID.class)
                : endpoint.getEhrId();
    }

    private UUID getCompositionId(Exchange exchange) {
        return ObjectHelper.isNotEmpty(exchange.getIn().getBody())
                ? exchange.getIn().getBody(UUID.class)
                : endpoint.getCompositionId();
    }

    private Class<?> getType(Exchange exchange) {
        return ObjectHelper.isNotEmpty(exchange.getIn().getHeader(CompositionConstants.TYPE))
                ? exchange.getIn().getHeader(CompositionConstants.TYPE, Class.class)
                : endpoint.getType();
    }
}
