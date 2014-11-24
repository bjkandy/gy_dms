package cn.gyyx.framework.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gyyx.framework.utils.Constant;

	/**
	 * 未登录过滤
	 * @author bjkandy
	 *
	 */
public class LoginFilter implements Filter {
	String nofilter;
	String nofilterFiles[];
	String filePath;

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		//获取当前文件路径
		String path = request.getServletPath();
		
		if (this.isInArray(path, nofilterFiles)) {
			chain.doFilter(servletRequest, servletResponse);
		} else {
			if (request.getSession().getAttribute(Constant.LOGIN_USER) != null) {
				chain.doFilter(servletRequest, servletResponse);
			} else {
				response.sendRedirect(request.getContextPath() + filePath);
				return;
			}
		}
	}
	
	/**
	 * 判断当前文件是否在不过滤文件列表中
	 * @param path
	 * @param nofilterFiles
	 * @return
	 */
	private boolean isInArray(String path, String nofilterFiles[]) {
		for (int i = 0; i < nofilterFiles.length; i++) {
			String nofilterFile = nofilterFiles[i];
			if(nofilterFile.indexOf("**") > -1){
				if(path.toUpperCase().indexOf(nofilterFile.substring(0, nofilterFile.length()-3).toUpperCase()) > -1){
					return true;
				}
			}else if(nofilterFile.indexOf("*") > -1){
				//
			}else{
				if (nofilterFile.equals(path)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 过滤器初始化
	 * @param filePath 跳转文件路径
	 * @param nofilter 不过滤文件
	 */
	public void init(FilterConfig arg0) throws ServletException {
		filePath = arg0.getInitParameter("filePath");
		nofilter = arg0.getInitParameter("nofilter");
		
		nofilterFiles = nofilter.split(",");
	}

}