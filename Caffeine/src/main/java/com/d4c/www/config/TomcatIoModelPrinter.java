package com.d4c.www.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.apache.catalina.connector.Connector;
import org.springframework.stereotype.Component;

@Component
public class TomcatIoModelPrinter implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(connector -> {
            // 获取 Tomcat 的 I/O 模型
            //Integer.M
            String protocolHandlerClassName = connector.getProtocolHandler().getClass().getName();
            System.out.println("Current Tomcat I/O Model: " + protocolHandlerClassName);
        });
    }
}
