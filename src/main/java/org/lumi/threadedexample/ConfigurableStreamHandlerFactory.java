package org.lumi.threadedexample;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by John Tsantilis (A.K.A lumi) on 17/12/2015.
 */
public class ConfigurableStreamHandlerFactory implements URLStreamHandlerFactory {
    private final Map<String, URLStreamHandler> protocolHandlers;

    public ConfigurableStreamHandlerFactory(String protocol, URLStreamHandler urlHandler) {
        protocolHandlers = new HashMap<String, URLStreamHandler>();
        addHandler(protocol, urlHandler);

    }

    public void addHandler(String protocol, URLStreamHandler urlHandler) {
        protocolHandlers.put(protocol, urlHandler);

    }

    public URLStreamHandler createURLStreamHandler(String protocol) {
        return protocolHandlers.get(protocol);

    }

}
