package cn.gyyx.framework.spring;


import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {

	private SpringContextLoader contextLoader;
	private static Logger logger = Logger.getLogger("SpringContextLoaderListener");
	
	public void contextInitialized(ServletContextEvent event) {
	    this.contextLoader = createContextLoader();
	    this.contextLoader.initWebApplicationContext(event.getServletContext());
	    
	    ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		SpringContextHolder.setInitApplicationContext(context);
		
		//Log config File
		PropertyConfigurator.configure( this.getClass().getClassLoader().getResource("log4j.properties"));
	  }

	  protected SpringContextLoader createContextLoader() {
	    return new SpringContextLoader();
	  }

	  public SpringContextLoader getContextLoader() {
	    return this.contextLoader;
	  }

	  public void contextDestroyed(ServletContextEvent event) {
	    if (this.contextLoader != null)
	      this.contextLoader.closeWebApplicationContext(event.getServletContext());
	  }
}
