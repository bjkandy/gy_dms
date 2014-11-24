package cn.gyyx.framework.spring;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 重写ContextLoader 类
 * @author kandy
 *
 */
public class SpringContextLoader extends ContextLoader {
	public static final String CONTEXT_CLASS_PARAM = "contextClass";
	public static final String CONFIG_LOCATION_PARAM = "contextConfigLocation";
	public static final String LOCATOR_FACTORY_SELECTOR_PARAM = "locatorFactorySelector";
	public static final String LOCATOR_FACTORY_KEY_PARAM = "parentContextKey";
	private static final String DEFAULT_STRATEGIES_PATH = "ContextLoader.properties";
	private static final Properties defaultStrategies;
	private final Log logger;
	private WebApplicationContext context;
	private BeanFactoryReference parentContextRef;

	public SpringContextLoader() {
		this.logger = LogFactory.getLog(ContextLoader.class);
	}
	
	/**
	 * 初始化WebApplicationContext 对象
	 */
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) throws IllegalStateException,BeansException {
		int i = 1;
		
		//判断是否插入时钟锁
		if(i>0){
			//插入时钟锁，且已注册执行
			if (servletContext
					.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE) != null) {
				throw new IllegalStateException(
						"Cannot initialize context because there is already a root application context present - check whether you have multiple ContextLoader* definitions in your web.xml!");
			}

			servletContext.log("Initializing Spring root WebApplicationContext");
			System.out.println("//-->Spring loading 加载");
			if (this.logger.isInfoEnabled()) {
				this.logger
						.info("Root WebApplicationContext: initialization started");
			}
			long startTime = System.currentTimeMillis();
			try {
				ApplicationContext parent = loadParentContext(servletContext);

				this.context = createWebApplicationContext(servletContext, parent);
				servletContext
						.setAttribute(
								WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
								this.context);

				if (this.logger.isDebugEnabled()) {
					this.logger
							.debug("Published root WebApplicationContext as ServletContext attribute with name ["
									+ WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE
									+ "]");
				}

				if (this.logger.isInfoEnabled()) {
					long elapsedTime = System.currentTimeMillis() - startTime;
					this.logger
							.info("Root WebApplicationContext: initialization completed in "
									+ elapsedTime + " ms");
				}

				return this.context;
			} catch (RuntimeException ex) {
				this.logger.error("Context initialization failed", ex);
				servletContext
						.setAttribute(
								WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
								ex);
				throw ex;
			} catch (Error err) {
				this.logger.error("Context initialization failed", err);
				servletContext
						.setAttribute(
								WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
								err);
				throw err;
			}
		}else{
			//未插入时钟锁或未注册用户执行
//			System.out.println(com.souvi.common.util.Constant.COPYRIGHT_INFO);
//			while(true){
//				
//			}
		}
		return this.context;
	}

	protected WebApplicationContext createWebApplicationContext(
			ServletContext servletContext, ApplicationContext parent)
			throws BeansException {
		Class contextClass = determineContextClass(servletContext);
		if (!(ConfigurableWebApplicationContext.class
				.isAssignableFrom(contextClass))) {
			throw new ApplicationContextException("Custom context class ["
					+ contextClass.getName() + "] is not of type ["
					+ ConfigurableWebApplicationContext.class.getName() + "]");
		}

		ConfigurableWebApplicationContext wac = (ConfigurableWebApplicationContext) BeanUtils
				.instantiateClass(contextClass);

		wac.setParent(parent);
		wac.setServletContext(servletContext);
		String configLocation = servletContext
				.getInitParameter("contextConfigLocation");
		if (configLocation != null) {
			wac.setConfigLocations(StringUtils.tokenizeToStringArray(
					configLocation, ",; \t\n"));
		}

		wac.refresh();
		return wac;
	}

	protected Class determineContextClass(ServletContext servletContext)
			throws ApplicationContextException {
		String contextClassName = servletContext
				.getInitParameter("contextClass");
		if (contextClassName != null) {
			try {
				return ClassUtils.forName(contextClassName);
			} catch (ClassNotFoundException ex) {
				throw new ApplicationContextException(
						"Failed to load custom context class ["
								+ contextClassName + "]", ex);
			}

		}

		contextClassName = defaultStrategies
				.getProperty(WebApplicationContext.class.getName());
		try {
			return ClassUtils.forName(contextClassName);
		} catch (ClassNotFoundException ex) {
			throw new ApplicationContextException(
					"Failed to load default context class [" + contextClassName
							+ "]", ex);
		}
	}

	protected ApplicationContext loadParentContext(ServletContext servletContext)
			throws BeansException {
		ApplicationContext parentContext = null;
		String locatorFactorySelector = servletContext
				.getInitParameter("locatorFactorySelector");
		String parentContextKey = servletContext
				.getInitParameter("parentContextKey");

		if (parentContextKey != null) {
			BeanFactoryLocator locator = ContextSingletonBeanFactoryLocator
					.getInstance(locatorFactorySelector);
			if (this.logger.isDebugEnabled()) {
				this.logger
						.debug("Getting parent context definition: using parent context key of '"
								+ parentContextKey
								+ "' with BeanFactoryLocator");
			}

			this.parentContextRef = locator.useBeanFactory(parentContextKey);
			parentContext = (ApplicationContext) this.parentContextRef
					.getFactory();
		}

		return parentContext;
	}

	public void closeWebApplicationContext(ServletContext servletContext) {
		servletContext.log("Closing Spring root WebApplicationContext");
		try {
			if (this.context instanceof ConfigurableWebApplicationContext)
				((ConfigurableWebApplicationContext) this.context).close();
		} finally {
			if (this.parentContextRef != null)
				this.parentContextRef.release();
		}
	}

	static {
		try {
			ClassPathResource resource = new ClassPathResource(
					"ContextLoader.properties", ContextLoader.class);
			defaultStrategies = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException ex) {
			throw new IllegalStateException(
					"Could not load 'ContextLoader.properties': "
							+ ex.getMessage());
		}
	}
}
