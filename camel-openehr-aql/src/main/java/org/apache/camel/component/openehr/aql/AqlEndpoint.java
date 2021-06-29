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

package org.apache.camel.component.openehr.aql;

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

/**
 * OpenEHR AQL component which does bla bla.
 *
 * @author Renaud Subiger
 * @since 1.0.0
 */
@UriEndpoint(firstVersion = "1.0.0", scheme = "openehr-aql", title = "OpenEHR AQL", syntax = "openehr-aql:name",
        category = {Category.JAVA})
@SuppressWarnings("java:S2160")
public class AqlEndpoint extends DefaultEndpoint {

    @UriPath(description = "Name")
    @Metadata(required = true)
    private String name;

    @Metadata(autowired = true)
    @UriParam(description = "Sets the OpenEhrClient to use to communicate with the openEHR REST Query API.")
    private OpenEhrClient openEhrClient;

    @UriParam(defaultValue = "SelectList", description = "Make the output of consumer to SelectList as List, or SelectOne as single Object.")
    private AqlOutputType outputType = AqlOutputType.SelectList;

    @UriParam(description = "Whether to use the message body as the AQL and then headers for parameters.",
            label = "producer")
    private boolean useMessageBodyForQuery;

    public AqlEndpoint(String uri, AqlComponent component) {
        super(uri, component);
    }

    @Override
    public Producer createProducer() {
        return new AqlProducer(this, isUseMessageBodyForQuery());
    }

    @Override
    public Consumer createConsumer(Processor processor) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpenEhrClient getOpenEhrClient() {
        return openEhrClient;
    }

    public void setOpenEhrClient(OpenEhrClient openEhrClient) {
        this.openEhrClient = openEhrClient;
    }

    public AqlOutputType getOutputType() {
        return outputType;
    }

    public void setOutputType(AqlOutputType outputType) {
        this.outputType = outputType;
    }

    public boolean isUseMessageBodyForQuery() {
        return useMessageBodyForQuery;
    }

    public void setUseMessageBodyForQuery(boolean useMessageBodyForQuery) {
        this.useMessageBodyForQuery = useMessageBodyForQuery;
    }
}
