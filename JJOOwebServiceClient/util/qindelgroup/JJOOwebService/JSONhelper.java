package qindelgroup.JJOOwebService;

import java.util.ArrayList;
import java.util.HashMap;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONhelper {
	public static ArrayList<HashMap<String, String>> json2listmap(String json){
		Gson gson = new Gson();
		ArrayList<HashMap<String, String>> result = gson.fromJson(json, new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType());
		return result;
	}

	public static String[] json2stringArray(String json) {
		Gson gson = new Gson();
		String[] result = gson.fromJson(json, new TypeToken<String[]>(){}.getType());
		return result;
	}

	public static HashMap<String, Integer> json2stringIntMap(String json) {
		Gson gson = new Gson();
		HashMap<String, Integer> toret =  gson.fromJson(json, new TypeToken<HashMap<String, Integer>>(){}.getType());
		return toret;
	}
}
