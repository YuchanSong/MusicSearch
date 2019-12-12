package test.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

@WebServlet("/uchan")
public class Uchan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map <String, String> servData = new LinkedHashMap<>(ServerData.data);
	Map <String, String> responseData;
	
	public Uchan() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		responseData = new LinkedHashMap<>();
		Boolean search = Boolean.parseBoolean(request.getParameter("search"));
		System.out.println("[doGet] From to Client : search=" + search);
		
		if (servData.isEmpty()) {
			responseData.put("데이터가 없습니다.", "");
		}
		else if (search) {
			for (String key : servData.keySet()) {
				responseData.put(key, "");
	        }
		}
		else {
			responseData.put("데이터 조회 오류", "");
		}
		
		String json = new Gson().toJson(responseData);
	    response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		responseData = new LinkedHashMap<>();
		String data = request.getParameter("requestData");
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = null;
		
		try {
			jsonObj = (JSONObject) jsonParser.parse(data);
		} catch (ParseException e) {
			System.out.println("Parssing Error");
		}
		
		System.out.println("[doPost] From to Client : " + jsonObj.toString());
		String title = (String) jsonObj.get("title");
		
		if (servData.containsKey(title)) {
			responseData.put("title", title);
			responseData.put("lyrics", servData.get(title));
		}
		else {
			responseData.put("title", title);
			responseData.put("lyrics", "찾는 곡이 없습니다.");
		}
		
	    String json = new Gson().toJson(responseData);
	    response.getWriter().write(json);
	}

}
