package test.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import test.model.service.CryptoService;

/**
 * Servlet implementation class CryptoLoginServlet
 */
@WebServlet("/login.cp")
public class CryptoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CryptoLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 암호화된 패스워드로 로그인 처리용 컨트롤러
		
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		System.out.println(userid + ", " + userpwd);

		//3. Model 객체 선언 및 생성하고,
		CryptoService mservice = new CryptoService();
		//로그인 처리용 매소드로 값 전달하고, 처리 결과 받기
		Member loginMember = mservice.loginCheck(userid, userpwd);
		
		//4. 받은 결과로 성공이면 성공페이지를, 실패이면 실패페이지를 내보냄
		if(loginMember != null) { //성공시
			//로그인 상태를 확인하기 위한 세션 객체 만들기
			HttpSession session = request.getSession();
			//System.out.println("생성된 세션객체의 id : " + session.getId());
			
			//지정한 시간(초)동안 서비스 요청이 없으면,
			//자동 로그아웃(세션객체를 없앰)
//			session.setMaxInactiveInterval(10 * 60); //10분 설정
			
			//로그인한 동안 여러 페이지와 서블릿들이 공유하고자 원하는 정보 저장함
			session.setAttribute("loginMember", loginMember); //선택사항
			
			response.sendRedirect("index.jsp");
		}else { //실패시
			//로그인 요청한 클라이언트 브라우저로 에러페이지 내보냄
			//response.sendRedirect("views/common/error.jsp");
			
			//에러페이지로 오류 메세지로 같이 보내려면
			RequestDispatcher view = 
					request.getRequestDispatcher("views/common/error.jsp");
			//절대경로 사용 못 함, 상대경로만 사용할 수 있음
			request.setAttribute("message", "로그인 실패 또는 로그인 제한상태입니다!");
			view.forward(request, response); //forward 전달하는 방식
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
