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

import org.apache.camel.Category;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.apache.camel.support.DefaultEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClient;

import java.util.UUID;

/**
 * Send requests to an openEHR REST Composition API.
 *
 * @author Renaud Subiger
 * @since 1.0.0
 */
@UriEndpoint(firstVersion = "1.0.0", scheme = "openehr-composition", title = "OpenEHR Composition",
        syntax = "openehr-composition:operation", category = {Category.REST}, consumerOnly = true)
public class CompositionEndpoint extends DefaultEndpoint {

    @UriPath(description = "The operation to perform.")
    @Metadata(required = true)
    private CompositionOperation operation;

    @UriParam(description = "The EHR ID to use.")
    private UUID ehrId;

    @UriParam(description = "The composition ID to use.")
    private UUID compositionId;

    @UriParam(description = "The type of the composition returned.")
    private Class<?> type;

    private OpenEhrClient client;

    public CompositionEndpoint(String uri, CompositionComponent component, OpenEhrClient client) {
        super(uri, component);
        this.client = client;
    }

    @Override
    public Producer createProducer() {
        return new CompositionProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) {
        throw new UnsupportedOperationException("Cannot consume from a CompositionEndpoint: " + getEndpointUri());
    }

    public CompositionOperation getOperation() {
        return operation;
    }

    public void setOperation(CompositionOperation operation) {
        this.operation = operation;
    }

    public UUID getEhrId() {
        return ehrId;
    }

    public void setEhrId(UUID ehrId) {
        this.ehrId = ehrId;
    }

    public UUID getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(UUID compositionId) {
        this.compositionId = compositionId;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public OpenEhrClient getClient() {
        return client;
    }

    public void setClient(OpenEhrClient client) {
        this.client = client;
    }
}
