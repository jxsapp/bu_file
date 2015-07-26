package org.bu.file.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.bu.file.misc.Error;
import org.bu.file.misc.PropertiesHolder;
import org.bu.file.model.BuError;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONStringer;
import org.codehaus.jettison.json.JSONWriter;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class BasicController implements ServletContextAware {

	protected ServletContext servletContext;// Servlet 上下文
	protected String basePath;// 基本路径
	/**
	 * 文件在数据库中找不见，从磁盘中获取文件
	 */
	static boolean READ_FOR_DISC = PropertiesHolder.getBoolean("get.file.redis.notfound.get.from.disc");

	// 过期时间
	static final long EXPIRE = PropertiesHolder.getLongValue("put.file.expire.seconds");
	static String ROOT_PATH = PropertiesHolder.getValue("root.file.path");

	private static List<String> FILE_TYPES = new ArrayList<String>();
	private static List<String> AUTHS = new ArrayList<String>();
	static {
		AUTHS.add(PropertiesHolder.getValue("key_zhanqun"));
		AUTHS.add(PropertiesHolder.getValue("key_qlsx"));
		AUTHS.add(PropertiesHolder.getValue("key_banjian"));
		AUTHS.add(PropertiesHolder.getValue("platform_banjian"));

		FILE_TYPES.add(PropertiesHolder.getValue("type_tysb"));
		FILE_TYPES.add(PropertiesHolder.getValue("type_qlsx"));
		FILE_TYPES.add(PropertiesHolder.getValue("type_bjsb"));

	}

	/**
	 * 导航栏
	 */

	public String getBasePath() {
		String path = initRequest().getContextPath();
		String baseContext = initRequest().getScheme() + "://" + initRequest().getServerName() + ":" + initRequest().getServerPort() + path;
		return baseContext;
	}

	public HttpServletRequest initRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public HashMap<String, Object> defaultModel() {
		HashMap<String, Object> model = new HashMap<String, Object>();
		model.put("basePath", getBasePath());
		return model;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	protected void success(HttpServletResponse response, String fid) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		try {
			JSONWriter jsonWriter = new JSONStringer().object().key("rst").value(Error.SUCCESS.index).key("fid").value(fid).key("msg").value(Error.SUCCESS.desc).endObject();
			response.getWriter().write(jsonWriter.toString());
		} catch (JSONException e) {
		}
	}

	protected void error(HttpServletResponse response, String fid) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		try {
			JSONWriter jsonWriter = new JSONStringer().object().key("rst").value(Error.FAILURE.index).key("fid").value(fid).key("msg").value(Error.FAILURE.desc).endObject();
			response.getWriter().write(jsonWriter.toString());
		} catch (JSONException e) {
		}
	}

	protected void fileNotFound(HttpServletResponse response, String fid) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		try {
			JSONWriter jsonWriter = new JSONStringer().object().key("rst").value(Error.FILE_NOT_FOUND.index).key("fid").value(fid).key("msg").value(Error.FILE_NOT_FOUND.desc).endObject();
			response.getWriter().write(jsonWriter.toString());
		} catch (JSONException e) {
		}
	}

	protected void noPermissions(HttpServletResponse response, String secret_key) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		try {
			JSONWriter jsonWriter = new JSONStringer().object()//
					.key("rst").value(Error.NO_PERMISSIONS.index)//
					.key("key").value(secret_key)//
					.key("msg").value(Error.NO_PERMISSIONS.desc).endObject();
			response.getWriter().write(jsonWriter.toString());
		} catch (JSONException e) {
		}
	}

	public BuError noPermissions(String key) {
		return new BuError(Error.NO_PERMISSIONS, key);
	}

	protected void exception(HttpServletResponse response, Error error) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		try {
			JSONWriter jsonWriter = new JSONStringer().object().key("rst").value(error.index).key("fid").value("").key("msg").value(error.desc).endObject();
			response.getWriter().write(jsonWriter.toString());
		} catch (JSONException e) {
		}
	}

	boolean validate(HttpServletResponse response, String secret_key, boolean redirect) {
		if (StringUtils.isEmpty(secret_key) || !AUTHS.contains(secret_key)) {// 不还有本Key
			try {
				if (redirect) {
					noPermissions(response, secret_key);
				}
				return false;
			} catch (IOException e) {
			}
		}
		return true;
	}

}