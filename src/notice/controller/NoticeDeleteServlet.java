package notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet("/ndel")
public class NoticeDeleteServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 관리자가 요청한 공지글 삭제처리용 컨트롤러
      int noticeno = Integer.parseInt(request.getParameter("noticeno"));
      
      int result = new NoticeService().deleteNotice(noticeno);
      
      if(result > 0) {
         //공지글 삭제시 저장된 파일도 삭제 처리함
         String renameFileName = request.getParameter("rfile");
         if(renameFileName != null) {
            String savePath = request.getSession().getServletContext().getRealPath("/resources/nupfiles");
            new File (savePath +"\\"+renameFileName).delete();
         }
         response.sendRedirect("nlist.ad"); //서블릿과 서블릿 연결
         
      }else {
         RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
         request.setAttribute("message", noticeno +"번글 삭제 실패");
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