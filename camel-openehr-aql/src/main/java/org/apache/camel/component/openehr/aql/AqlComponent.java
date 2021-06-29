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

import org.apache.camel.Endpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.annotations.Component;
import org.apache.camel.support.DefaultComponent;
import org.ehrbase.client.openehrclient.OpenEhrClient;

import java.util.Map;

/**
 * The openEHR AQL component is used to execute AQL queries using the openEHR REST Query API.
 *
 * @author Renaud Subiger
 * @since 1.0.0
 */
@Component("openehr-aql")
@SuppressWarnings("java:S6212")
public class AqlComponent extends DefaultComponent {

    @Metadata(autowired = true, description = "OpenEHR Client")
    private OpenEhrClient openEhrClient;

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        AqlEndpoint endpoint = new AqlEndpoint(uri, this);
        setProperties(endpoint, parameters);

        OpenEhrClient client = openEhrClient;
        if (endpoint.getOpenEhrClient() != null) {
            client = endpoint.getOpenEhrClient();
        }
        if (client == null) {
            throw new IllegalArgumentException("OpenEhrClient must be configured");
        }

        endpoint.setOpenEhrClient(client);
        return endpoint;
    }

    public OpenEhrClient getOpenEhrClient() {
        return openEhrClient;
    }

    public void setOpenEhrClient(OpenEhrClient openEhrClient) {
        this.openEhrClient = openEhrClient;
    }
}
