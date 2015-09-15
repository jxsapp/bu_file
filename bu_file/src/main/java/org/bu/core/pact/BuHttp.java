package org.bu.core.pact;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.bu.core.log.BuLog;
import org.bu.core.misc.BuJSON;
import org.json.JSONObject;

public class BuHttp {
	static final BuLog logger = BuLog.getLogger(BuHttp.class);

	public static interface BuHttpListener {
		public void onSuccess(BuJSON json);

		public void onFailuire(int status);
	}

	private String url = "";
	private BuHttpListener httpListener;

	public BuHttp(String url, BuHttpListener httpListener) {
		super();
		this.url = url;
		this.httpListener = httpListener;
	}

	private CloseableHttpClient getClient() {
		CloseableHttpClient client = HttpClients.createDefault();
		return client;
	}

	private void sendData_Post_Put(HttpEntityEnclosingRequestBase httpPut, BuJSON json, BuHttpListener _listener) {
		httpPut.setHeader("Accept", "application/json");
		httpPut.setHeader("Content-Type", "application/json;charset=utf-8");
		CloseableHttpClient httpclient = getClient();
		try {
			httpPut.setEntity(new StringEntity(json.toString(), "utf-8"));
			HttpResponse httpResponse = httpclient.execute(httpPut);
			result(httpResponse, _listener);
		} catch (ClientProtocolException ex) {
			logger.error("ClientProtocolException", ex);
			_listener.onFailuire(ErrorCode.SERVER_ERROR);
		} catch (IOException ex) {
			logger.error("IOException", ex);
			_listener.onFailuire(ErrorCode.SERVER_ERROR);
		}
	}

	private void sendData_Get_Del(HttpRequestBase httpGet, BuHttpListener _listener) {
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json;charset=utf-8");
		CloseableHttpClient httpclient = getClient();
		try {
			HttpResponse httpResponse = httpclient.execute(httpGet);
			result(httpResponse, _listener);
			httpclient.close();
		} catch (ClientProtocolException ex) {
			logger.error("ClientProtocolException", ex);
			_listener.onFailuire(ErrorCode.SERVER_ERROR);
		} catch (IOException ex) {
			logger.error("IOException", ex);
			_listener.onFailuire(ErrorCode.SERVER_ERROR);
		}
	}

	private void result(HttpResponse httpResponse, BuHttpListener _listener) throws IOException {
		int status = httpResponse.getStatusLine().getStatusCode();
		byte[] responseByte = EntityUtils.toByteArray(httpResponse.getEntity());
		String content = new String(responseByte, 0, responseByte.length);

		if (status != HttpURLConnection.HTTP_OK//
				&& status != HttpURLConnection.HTTP_CREATED//
				&& status != HttpURLConnection.HTTP_ACCEPTED //
				&& status != HttpURLConnection.HTTP_NO_CONTENT//
		) {
			_listener.onFailuire(status);
		} else {
			if (StringUtils.isEmpty(content)) {
				content = "";
			}
			try {
				JSONObject json = new JSONObject(content);
				httpListener.onSuccess(new BuJSON(json));
			} catch (Exception e) {

			}
		}
	}

	public void post(BuJSON params) {
		sendData_Post_Put(new HttpPost(url), params, httpListener);
	}

	public void post(HashMap<String, String> params) {
		CloseableHttpClient httpClient = getClient();
		try {
			HttpPost httpPost = new HttpPost(url);
			// 添加所需要的post内容
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				nvps.add(new BasicNameValuePair(key, params.get(key)));
			}
			StringBuilder ul = new StringBuilder(url);
			if (null != params && params.size() > 0) {// 如果是没有参数
				ul.append("?");
				ul.append(formateParams(params));
			}
			System.out.println(ul);
			// HttpEntity sendEntity = new WeiMiEntity(nvps);
			HttpEntity sendEntity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
			httpPost.setEntity(sendEntity);
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String mats = EntityUtils.toString(entity);
				logger.info(mats);
				JSONObject json = new JSONObject(mats);
				httpListener.onSuccess(new BuJSON(json));
			} else {
				httpListener.onFailuire(ErrorCode.SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error("ex:", e);
			httpListener.onFailuire(ErrorCode.SERVER_ERROR);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				httpListener.onFailuire(ErrorCode.SERVER_ERROR);
			}
		}
	}

	static class WeiMiEntity extends StringEntity {

		public WeiMiEntity(final Iterable<? extends NameValuePair> parameters) {
			super(PostParamsUtils.format(parameters, Consts.UTF_8), ContentType.create(URLEncodedUtils.CONTENT_TYPE, Consts.UTF_8));
		}

	}

	private static String formateParams(HashMap<String, String> params) {
		StringBuilder ul = new StringBuilder();
		int i = 0;
		for (String key : params.keySet()) {
			ul.append(key + "=" + params.get(key));
			if (i != params.size() - 1) {
				ul.append("&");
			}
			i++;
		}
		return ul.toString();
	}

	public void get(HashMap<String, String> params) {
		JSONObject jsonObject = new JSONObject(params);
		BuJSON json = new BuJSON(jsonObject);
		sendData_Get_Del(new HttpGet(getUlr(url, json)), httpListener);
	}

	private String getUlr(String uri, BuJSON json) {
		String rst = "";
		StringBuilder urlBd = new StringBuilder(uri);
		if (uri.indexOf("\\?") == -1) {
			urlBd.append("?");
		} else {
			urlBd.append("&");
		}
		Iterator<String> keys = json.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			urlBd.append(key + "=" + json.getString(key));
			urlBd.append("&");
		}

		if (urlBd.lastIndexOf("&") == urlBd.length() - 1) {
			rst = urlBd.substring(0, urlBd.length() - 1);
		} else {
			rst = urlBd.toString();
		}
		return rst;
	}

}
