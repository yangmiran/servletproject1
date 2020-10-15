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
 * Servlet implementation class MemberUpdateViewServlet
 */
@WebServlet("/mupview")
public class MemberUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 정보 수정페이지를 내보내는 컨트롤러
		// 조회한 회원 정보도 함께 전달함
		
		//1.
		//2.
		String userid = request.getParameter("userid");
		
		//3.
		Member member = new MemberService().selectMember(userid);
		
		//4.
		RequestDispatcher view = null;
		if(member != null) { //조회 성공시
			view = request.getRequestDispatcher("views/member/memberUpdatePage.jsp");
			request.setAttribute("member", member);
			view.forward(request, response);
		}else { //조회 실패시
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", userid + " 회원 조회 실패.");
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
