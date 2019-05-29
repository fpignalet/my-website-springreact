package com.core.ejb;

import org.springframework.context.annotation.Bean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class EJBContext {

    @Bean
    public Context context() throws NamingException {
        final Properties jndiProps = new Properties();

        jndiProps.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put("jboss.naming.client.ejb.context", true);
        jndiProps.put("java.naming.provider.url", "http-remoting://localhost:8080");

        return new InitialContext(jndiProps);
    }

    /**
     * @param classType
     * @return
     */
    protected String getFullName(Class classType) {
        final String moduleName = "ejb-remote-for-spring/";
        final String beanName = classType.getSimpleName();
        final String viewClassName = classType.getName();
        return moduleName + beanName + "!" + viewClassName;
    }

    /**
     * @param context
     * @return
     * @throws NamingException
     */
    @Bean
    public IEJBStatelessTest tellStatelessWorld(Context context) throws NamingException {
        return (IEJBStatelessTest)context.lookup(this.getFullName(IEJBStatelessTest.class));
    }

    /**
     * @param context
     * @return
     * @throws NamingException
     */
    @Bean
    public IEJBStatefulTest testStatefulWorld(Context context) throws NamingException {
        return (IEJBStatefulTest) context.lookup(this.getFullName(IEJBStatefulTest.class));
    }

}
