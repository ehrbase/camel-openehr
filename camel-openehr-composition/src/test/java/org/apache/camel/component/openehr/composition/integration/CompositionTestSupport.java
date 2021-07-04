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

package org.apache.camel.component.openehr.composition.integration;

import org.apache.camel.test.junit5.CamelTestSupport;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.junit.jupiter.api.TestInstance;

import java.net.URI;
import java.util.UUID;

/**
 * @author Renaud Subiger
 * @since 1.0.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompositionTestSupport extends CamelTestSupport {

    protected static OpenEhrClient client;

    protected static UUID ehrId;

    @Override
    protected void setupResources() throws Exception {
        super.setupResources();

        OpenEhrClientConfig config = new OpenEhrClientConfig(new URI("http://localhost:8080/ehrbase/rest/openehr/v1/"));
        client = new DefaultRestClient(config);

        ehrId = client.ehrEndpoint().createEhr();
    }
}
