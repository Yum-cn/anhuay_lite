package com.anhuay.common.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpsConfiguration {
	@Value("${server.port}")
	private Integer tomcatPort;

/*	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};
		tomcat.addAdditionalTomcatConnectors(httpConnector());
		return tomcat;
	}

	@Bean
	public Connector httpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(tomcatPort);// 表示用8080端口来供http访问
		connector.setSecure(false);
		connector.setRedirectPort(80);// 自动重定向到8443端口
		return connector;
	}*/
	
	
/*	@Bean
	public WebSocketContainerCustomizer<JettyEmbeddedServletContainerFactory> webSocketContainerCustomizer(){
	    return new WebSocketContainerCustomizer<JettyEmbeddedServletContainerFactory>(){
	        @Override
	        protected void doCustomize(JettyEmbeddedServletContainerFactory container) {
	            container.addServerCustomizers(server -> {
	                ServerConnector connector = new ServerConnector(server);
	                connector.setPort(8888);
	                server.addConnector(connector);
	            });
	        }
	    };
	}*/
}