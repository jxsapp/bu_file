package org.bu.file.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bu.file.misc.Error;
import org.bu.file.misc.PropertiesHolder;
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
	 * 放置当前文件到跟目录，或者到子目录
	 */
	static boolean PUT_FILE_TO_SUB_DIR = PropertiesHolder.getBoolean("put.file.save.to.sub");
	/**
	 * 放置在子文件夹下的目录数
	 */
	static int PUT_FILE_TO_SUB_DIR_SIZE = PropertiesHolder.getIntValue("put.file.save.to.sub.size");
	/**
	 * 将长传的文件保存到磁盘上
	 */
	static boolean PUT_SAVE_TO_DISC = PropertiesHolder.getBoolean("put.file.save.to.disc");
	/**
	 * 文件在数据库中找不见，从磁盘中获取文件
	 */
	static boolean GET_FILE_RIDIS_NOT_FOUNT_GET_FROM_DISC = PropertiesHolder
			.getBoolean("get.file.redis.notfound.get.from.disc");

	/**
	 * 导航栏
	 */

	public String getBasePath() {
		String path = initRequest().getContextPath();
		String baseContext = initRequest().getScheme() + "://" + initRequest().getServerName() + ":"
				+ initRequest().getServerPort() + path;
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
			JSONWriter jsonWriter = new JSONStringer().object().key("rst").value(Error.SUCCESS.index).key("fid")
					.value(fid).key("msg").value(Error.SUCCESS.desc).endObject();
			response.getWriter().write(jsonWriter.toString());
		} catch (JSONException e) {
		}
	}

	protected void error(HttpServletResponse response, String fid) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		try {
			JSONWriter jsonWriter = new JSONStringer().object().key("rst").value(Error.FAILURE.index).key("fid")
					.value(fid).key("msg").value(Error.FAILURE.desc).endObject();
			response.getWriter().write(jsonWriter.toString());
		} catch (JSONException e) {
		}
	}

	protected void fileNotFound(HttpServletResponse response, String fid) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		try {
			JSONWriter jsonWriter = new JSONStringer().object().key("rst").value(Error.FILE_NOT_FOUND.index).key("fid")
					.value(fid).key("msg").value(Error.FILE_NOT_FOUND.desc).endObject();
			response.getWriter().write(jsonWriter.toString());
		} catch (JSONException e) {
		}
	}

	protected void noPermissions(HttpServletResponse response, String fid) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		try {
			JSONWriter jsonWriter = new JSONStringer().object().key("rst").value(Error.NO_PERMISSIONS.index).key("fid")
					.value(fid).key("msg").value(Error.NO_PERMISSIONS.desc).endObject();
			response.getWriter().write(jsonWriter.toString());
		} catch (JSONException e) {
		}
	}

	protected void exception(HttpServletResponse response, Error error) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		try {
			JSONWriter jsonWriter = new JSONStringer().object().key("rst").value(error.index).key("fid").value("")
					.key("msg").value(error.desc).endObject();
			response.getWriter().write(jsonWriter.toString());
		} catch (JSONException e) {
		}
	}

	boolean validate(HttpServletResponse response, String session, String fid) {
		if (null == session || "".equals(session.trim())) {
			try {
				noPermissions(response, fid);
				return false;
			} catch (IOException e) {
			}
		}
		return true;
	}

}