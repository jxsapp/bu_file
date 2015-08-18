package org.bu.core.misc;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class BuGsonHolder {

	public static <T> String getJson(T t, boolean all) {
		String json = "{}";
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		if (!all) {
			gson = getWithoutExpose();
		}
		try {
			json = gson.toJson(t);
		} catch (Exception e) {
			json = new JsonObject().toString();
		}
		return json;
	}

	public static String getJson(List<?> list) {
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	public static <T> T getObj(String json) {
		T list = null;
		java.lang.reflect.Type type = new TypeToken<T>() {
		}.getType();
		Gson gson = new Gson();
		list = gson.fromJson(json, type);
		return list;

	}

	public static Gson getWithoutExpose() {
		return new GsonBuilder().excludeFieldsWithoutExposeAnnotation()//
				.setDateFormat("yyyy-MM-dd HH:mm:ss")//
				.create();
	}

	public static <T> T getObj(String json, Class<T> t) {
		T rst = null;
		try {
			Gson gson = new Gson();
			rst = gson.fromJson(json, t);
		} catch (Exception e) {
			rst = null;
		}
		return rst;
	}
}