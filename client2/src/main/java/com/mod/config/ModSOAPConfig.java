package com.mod.config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class ModSOAPConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(final ApplicationContext applicationContext) {
        final MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/service/*");
    }

    @Bean(name = "itemDetailsWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(final XsdSchema wsdlSchema) {
        final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ItemPort");
        wsdl11Definition.setLocationUri("/service/item-details");
        wsdl11Definition.setTargetNamespace("http://localhost:8081/mod/xml/item");
        wsdl11Definition.setSchema(wsdlSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema wsdlSchema()
    {
        return new SimpleXsdSchema(new ClassPathResource("schema/item.xsd"));
    }
}
