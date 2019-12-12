package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/TestServlet2")

public class TestServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(TestServlet2.class);

	public TestServlet2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE>HelloServlet</TITLE></HEAD>");
		out.println("<BODY>");
		out.println("<H2> Clinet IP: " + request.getRemoteAddr() + "</H2>");
		out.println("<H2> Client Host : " + request.getRemoteHost() + "</H2>");
		out.println("<H2> Request URI : " + request.getRequestURI() + "</H2>");
		out.println("</BODY></HTML>");

		logger.debug("나는 Servlet2야~");
//        Log log = new Log(); //출력스트림 생성         
//        log.debug("로그 테스트!"); //로그 기록하기         
//        log.close(); //출력스트림 닫기    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
