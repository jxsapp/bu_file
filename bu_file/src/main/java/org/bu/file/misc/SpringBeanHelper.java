package org.bu.file.misc;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 
 * @目的 获取上下文	
 * @时间 May 4, 2010
 * @作者 BestUpon
 * @邮箱 bestupon@foxmail.com
 *
 */
public class SpringBeanHelper {
	/**
	 * 获取上下文
	 * 
	 * @param beanName
	 * @param servletContext
	 * @return 获得的bean
	 */
	public static Object getBean(String beanName, ServletContext servletContext) {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return ac.getBean(beanName);
	}
}
