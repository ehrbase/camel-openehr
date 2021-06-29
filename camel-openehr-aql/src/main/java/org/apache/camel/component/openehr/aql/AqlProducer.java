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

import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Renaud Subiger
 * @since 1.0.0
 */
public class AqlProducer extends DefaultProducer {

    private static final Logger LOG = LoggerFactory.getLogger(AqlProducer.class);

    private final AqlEndpoint endpoint;

    private final boolean useMessageBodyForQuery;

    public AqlProducer(AqlEndpoint endpoint, boolean useMessageBodyForQuery) {
        super(endpoint);
        this.endpoint = endpoint;
        this.useMessageBodyForQuery = useMessageBodyForQuery;
    }

    @Override
    public void process(Exchange exchange) {
        Query<?> query;
        ParameterValue<?>[] parameters;

        if (useMessageBodyForQuery) {
            query = exchange.getIn().getBody(Query.class);
            parameters = exchange.getIn().getHeader(AqlConstants.AQL_PARAMETERS, ParameterValue[].class);
        } else {
            query = exchange.getIn().getHeader(AqlConstants.AQL_QUERY, Query.class);
            parameters = exchange.getIn().getBody(ParameterValue[].class);
        }

        if (parameters == null) {
            parameters = new ParameterValue[0];
        }

        List<? extends Record> result = endpoint.getOpenEhrClient().aqlEndpoint()
                .execute(query, parameters);

        exchange.getMessage().setBody(result);
    }
}
