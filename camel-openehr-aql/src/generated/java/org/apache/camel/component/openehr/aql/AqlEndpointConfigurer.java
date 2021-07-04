/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.openehr.aql;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.ExtendedPropertyConfigurerGetter;
import org.apache.camel.spi.PropertyConfigurerGetter;
import org.apache.camel.spi.ConfigurerStrategy;
import org.apache.camel.spi.GeneratedPropertyConfigurer;
import org.apache.camel.util.CaseInsensitiveMap;
import org.apache.camel.support.component.PropertyConfigurerSupport;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@SuppressWarnings("unchecked")
public class AqlEndpointConfigurer extends PropertyConfigurerSupport implements GeneratedPropertyConfigurer, PropertyConfigurerGetter {

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        AqlEndpoint target = (AqlEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": target.setBridgeErrorHandler(property(camelContext, boolean.class, value)); return true;
        case "exceptionhandler":
        case "exceptionHandler": target.setExceptionHandler(property(camelContext, org.apache.camel.spi.ExceptionHandler.class, value)); return true;
        case "exchangepattern":
        case "exchangePattern": target.setExchangePattern(property(camelContext, org.apache.camel.ExchangePattern.class, value)); return true;
        case "lazystartproducer":
        case "lazyStartProducer": target.setLazyStartProducer(property(camelContext, boolean.class, value)); return true;
        case "openehrclient":
        case "openEhrClient": target.setOpenEhrClient(property(camelContext, org.ehrbase.client.openehrclient.OpenEhrClient.class, value)); return true;
        case "outputtype":
        case "outputType": target.setOutputType(property(camelContext, org.apache.camel.component.openehr.aql.AqlOutputType.class, value)); return true;
        case "usemessagebodyforquery":
        case "useMessageBodyForQuery": target.setUseMessageBodyForQuery(property(camelContext, boolean.class, value)); return true;
        default: return false;
        }
    }

    @Override
    public String[] getAutowiredNames() {
        return new String[]{"openEhrClient"};
    }

    @Override
    public Class<?> getOptionType(String name, boolean ignoreCase) {
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return boolean.class;
        case "exceptionhandler":
        case "exceptionHandler": return org.apache.camel.spi.ExceptionHandler.class;
        case "exchangepattern":
        case "exchangePattern": return org.apache.camel.ExchangePattern.class;
        case "lazystartproducer":
        case "lazyStartProducer": return boolean.class;
        case "openehrclient":
        case "openEhrClient": return org.ehrbase.client.openehrclient.OpenEhrClient.class;
        case "outputtype":
        case "outputType": return org.apache.camel.component.openehr.aql.AqlOutputType.class;
        case "usemessagebodyforquery":
        case "useMessageBodyForQuery": return boolean.class;
        default: return null;
        }
    }

    @Override
    public Object getOptionValue(Object obj, String name, boolean ignoreCase) {
        AqlEndpoint target = (AqlEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return target.isBridgeErrorHandler();
        case "exceptionhandler":
        case "exceptionHandler": return target.getExceptionHandler();
        case "exchangepattern":
        case "exchangePattern": return target.getExchangePattern();
        case "lazystartproducer":
        case "lazyStartProducer": return target.isLazyStartProducer();
        case "openehrclient":
        case "openEhrClient": return target.getOpenEhrClient();
        case "outputtype":
        case "outputType": return target.getOutputType();
        case "usemessagebodyforquery":
        case "useMessageBodyForQuery": return target.isUseMessageBodyForQuery();
        default: return null;
        }
    }
}

