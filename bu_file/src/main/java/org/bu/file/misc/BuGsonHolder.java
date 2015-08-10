package org.bu.file.misc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class BuGsonHolder {

	public static String getJson(Object list) {
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
		return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
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
