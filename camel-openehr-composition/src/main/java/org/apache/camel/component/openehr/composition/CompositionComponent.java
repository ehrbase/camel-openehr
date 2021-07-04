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

import org.apache.camel.Endpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.annotations.Component;
import org.apache.camel.support.DefaultComponent;
import org.ehrbase.client.openehrclient.OpenEhrClient;

import java.util.Map;

/**
 * openEHR Composition component.
 *
 * @author Renaud Subiger
 * @since 1.0.0
 */
@Component("openehr-composition")
public class CompositionComponent extends DefaultComponent {

    @Metadata(label = "advanced", description = "openEHR Client", autowired = true)
    private OpenEhrClient client;

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        var endpoint = new CompositionEndpoint(uri, this, client);
        endpoint.setOperation(CompositionOperation.valueOf(remaining));
        setProperties(endpoint, parameters);
        return endpoint;
    }

    @Override
    protected void doInit() throws Exception {
        super.doInit();

        if (client == null) {
            throw new IllegalArgumentException("OpenEhrClient must be configured");
        }
    }

    public OpenEhrClient getClient() {
        return client;
    }

    public void setClient(OpenEhrClient client) {
        this.client = client;
    }
}
