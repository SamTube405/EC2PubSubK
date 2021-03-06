package com.operation.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.compute.Engine;

/**
 * Servlet implementation class PublishTopic
 */
@WebServlet("/publish")
public class PublishTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GsonWriter gsonwrt;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublishTopic() {
        super();
        gsonwrt=new GsonWriter();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> map=new HashMap<String,Object>();
		boolean isValid=false;
		String _snsPTopic=request.getParameter("_snsPTopic");
   	 	String _snsPMsg=request.getParameter("_snsPMsg");
		if(_snsPTopic!=null && _snsPMsg!=null){
			System.out.println("Published: "+_snsPTopic+" "+_snsPMsg);
			new Engine().getSns_drv().publish(_snsPTopic, _snsPMsg);
			isValid=true;
			map.put("snsTopic", _snsPTopic);
			map.put("snsProtocol", _snsPMsg);			
		}
		map.put("isValid", isValid);
		gsonwrt.write(response,map);
	}

}
