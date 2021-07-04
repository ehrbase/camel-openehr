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

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.openehr.composition.CompositionConstants;
import org.apache.camel.spi.Registry;
import org.apache.camel.support.SimpleRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Renaud Subiger
 * @since 1.0.0
 */
class CompositionFindIT extends CompositionTestSupport {

    @Override
    protected void setupResources() throws Exception {
        super.setupResources();
    }

    @Override
    protected Registry createCamelRegistry() {
        Registry registry = new SimpleRegistry();
        registry.bind("client", client);
        return registry;
    }

    @Test
    void findCompositionWithWrongCompositionId() {
        Optional<?> result = template.requestBodyAndHeader("direct:findComposition", UUID.randomUUID(), CompositionConstants.EHR_ID, ehrId, Optional.class);
        Assertions.assertTrue(result.isEmpty());
    }


    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:findComposition")
                        .to("openehr-composition:find");
            }
        };
    }
}
