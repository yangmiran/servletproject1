package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("LoginServlet doGet() run...");
		// 로그인 처리용 컨트롤러
		
		//클라이언트로 부터 로그인 요청을 받았음. 아이디와 암호가 전송옴
		
		//1. 전송온 값에 한글이 있다면, 문자인코딩 처리함
		//2. 전송온 값 꺼내서 변수 또는 객체에 기록 저장함
		//전송온 값들은 모두 request 에 기록되어 전송옴 :
		//String 변수 = request.getParameter("name속성에 설정한 이름");
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		System.out.println(userid + ", " + userpwd);


		
		//3. Model 객체 선언 및 생성하고,
		MemberService mservice = new MemberService();
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
