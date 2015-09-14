package org.bu.core.misc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BuJSONArray {

	private JSONArray jsonArray;

	public BuJSONArray(JSONArray jsonArray) {
		super();
		this.jsonArray = jsonArray;
	}

	public JSONArray getJSONArray(int index) {
		JSONArray array = new JSONArray();
		try {
			array = jsonArray.getJSONArray(index);
		} catch (JSONException e) {
			array = new JSONArray();
		}
		return array;
	}

	public JSONObject getJSONObject(int index) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject = jsonArray.getJSONObject(index);
		} catch (JSONException e) {
			jsonObject = new JSONObject();
		}
		return jsonObject;
	}

	public void put(Object value) {
		jsonArray.put(value);
	}

	@Override
	public String toString() {
		return jsonArray.toString();
	}

}
