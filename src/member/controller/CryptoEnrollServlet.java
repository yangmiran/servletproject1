package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.model.service.CryptoService;
import test.model.vo.CryptoMember;

/**
 * Servlet implementation class CryptoEnrollServlet
 */
@WebServlet("/minsert.cp")
public class CryptoEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CryptoEnrollServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 암호화된 패스워드를 받아서 회원 가입 처리용 컨트롤러
		// 회원가입 처리용 컨트롤러
		// 1. 전송온 값에 한글이 있다면 인코딩 처리함
		request.setCharacterEncoding("utf-8"); // 연결하는 enrollPage.html 의 <meta charset="UTF-8"> 부분의 utf-8이랑 맞춰서 작성

		// 2. 전송온 값 꺼내서, 변수 또는 객체에 기록 저장 처리함
		CryptoMember member = new CryptoMember();
		member.setUsername(request.getParameter("username"));
		member.setUserid(request.getParameter("userid"));
		member.setUserpwd(request.getParameter("userpwd"));
		member.setAge(Integer.parseInt(request.getParameter("age")));
		member.setGender(request.getParameter("gender"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setEtc(request.getParameter("etc"));

		// 같은 이름으로 여러 개의 값이 전송온 경우
		// hobby=game&hobby=climb&hobby=sports
//				String[] hobbies = request.getParameterValues("hobby");
//				for(String hobby : hobbies) {
//					System.out.println(hobby);
//				}
		// , 를 구분자로 문자배열을 하나의 문자열로 합치기
//				String joinHobby = String.join(",", hobbies);
//				System.out.println(joinHobby);
//				member.setHobby(joinHobby);
		member.setHobby(String.join(",", request.getParameterValues("hobby")));

		// 3. 서비스 객체 생성하고, 서비스 메소드를 이용해서 객체 전달하고
		// 처리 결과받기
		int result = new CryptoService().insertMember(member);

		// 4. 받은 결과에 따라 성공/실패 뷰 선택해서 내보내기
		if (result > 0) {
			response.sendRedirect("/test1/views/test/loginPage.html");
		} else {
			// response.sendRedirect("/test1/views/common/error.jsp");

			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "회원 가입 실패!");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
