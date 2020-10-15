package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MyinfoServlet
 */
@WebServlet("/myinfo")
public class MyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 내정보 보기(My Page) 처리용 컨트롤러
		
		// 1. 전송온 값에 한글이 있다면 인코딩 처리함
		// 2. 전송온 값 꺼내서 변수 또는 객체에 기록 저장함
		String userid = request.getParameter("userid");
		
		// 3. 서비스 객체 생성하고, 메소드 실행할 때 값 전달하고 결과받기
		Member member = new MemberService().selectMember(userid);
		
		//4. 받은 결과를 가지고 성공/실패 페이지를 내보냄
		//뷰페이지와 페이지에 출력할 정보도 함께 보내려면, RequestDispatcher 사용
		RequestDispatcher view = null;
		if(member != null) { //성공시
			view = request.getRequestDispatcher("views/member/myInfoPage.jsp");
			request.setAttribute("member", member);
			view.forward(request, response);
		}else { //실패시
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "My page 상세조회 요청 실패");
			view.forward(request, response);
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
