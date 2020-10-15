package notice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeAdminUpdateServlet
 */
@WebServlet("/nupdate.ad")
public class NoticeAdminUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeAdminUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 관리자용 공지사항 수정 처리용 컨트롤러

		// 1. multipart 방식으로 인코딩되어서 전송되었는지 확인함
		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form 의 enctype='multipart/form-data'속성 누락됨");
			view.forward(request, response);
		}

		// 2. 업로드할 파일의 용량 제한 : 10Mbyte 로 제한한다면
		int maxSize = 1024 * 1024 * 10;

		// 3. 업로드되는 파일의 저장 폴더 지정하기
		String savePath = request.getSession().getServletContext().getRealPath("/resources/nupfiles");

		// 4. request 를 MultipartRequest 로 변환해야 함
		// cos.jar 가 제공하는 클래스를 사용함
		// 전송온 파일은 자동 지정 폴더에 저장됨
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		// 5. 데이터베이스에 기록할 값 꺼내기
		// mrequest 로 추출해야 함
		Notice notice = new Notice();

		notice.setNoticeNo(Integer.parseInt(mrequest.getParameter("no")));
		notice.setNoticeTitle(mrequest.getParameter("title"));
		notice.setNoticeWriter(mrequest.getParameter("writer"));
		notice.setNoticeContent(mrequest.getParameter("content"));

		//첨부 파일 삭제 여부 추출 ---------------------------------------------------------
		String deleteFlag = mrequest.getParameter("deleteFlag");
		
		String originalFileName = mrequest.getParameter("ofile");
		String renameFileName = mrequest.getParameter("rfile");
		
		// 서버에 새로 업로드된 파일명 추출하기
		String newOriginalFileName = mrequest.getFilesystemName("upfile");
		notice.setOriginalFilepath(newOriginalFileName);
		
		//원래 파일과 새로 업로드된 파일의 이름이 같고
		//파일 용량도 동일하면 원래 이름 그대로 객체에 기록함
		//첨부파일이 없었는데 추가된 경우와
		//첨부파일이 있었는데 변경된 경우 둘 다 파일명 바꾸기함

		// 첨부된 파일의 파일명 바꾸기 하려면...
		// 저장 폴더에 같은 이름의 파일이 있을 경우를 대비하기 위함.
		// "년월일시분초.확장자" 형식으로 변경해 봄
		String newRenameFileName = null;
		if (newOriginalFileName != null) {
			// 첨부파일이 있을 때만 이름바꾸기 실행함

			// 바꿀 파일명에 대한 포맷 문자열 만들기 : 년월일시분초 형식으로
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			// 바꿀 파일명 만들기
			newRenameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
			// 업로드된 파일의 확장자를 추출해서, 새 파일명에 붙여줌
			newRenameFileName += "." + newOriginalFileName.substring(newOriginalFileName.lastIndexOf(".") + 1);

			// 원본 파일명 rename 처리를 위해서 File 객체 만들기
			File originFile = new File(savePath + "\\" + newOriginalFileName); // 원본 파일객체
			File renameFile = new File(savePath + "\\" +newRenameFileName); // 이름 바꿀 파일 객체

			// 이름 바꾸기 실행함
			if (!originFile.renameTo(renameFile)) {
				// renameTo 메소드가 실패(false)한 경우에 직접 바꾸기함
				// 원본 파일 내용 읽어서, 복사본에 기록하고
				// 완료되면, 원본 파일 삭제함
				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(originFile);

				int data = -1;
				byte[] buffer = new byte[1024]; // 한번에 배열로 읽음
				while ((data = fin.read(buffer, 0, buffer.length)) != -1) {
					// buffer, 0, buffer.length : 0번부터 buffer의 길이만큼까지
					fout.write(buffer, 0, buffer.length);
				}

				fin.close();
				fout.close();
				originFile.delete(); // 원본파일 삭제
			} // 직접 이름바꾸기
			notice.setRenameFilepath(newRenameFileName); // DB에 바뀐이름 원래이름 둘다들어감
			
			if(originalFileName != null) {
				//원래 첨부파일이 있었다면,
				//원래 첨부파일을 폴더에서 삭제함
				new File(savePath + "\\" + renameFileName).delete();
			}
		}else if(originalFileName != null && deleteFlag != null && deleteFlag.equals("yes")) {
			//원래 첨부파일이 있었고, 파일삭제가 선택된 경우
			notice.setOriginalFilepath(null);
			notice.setRenameFilepath(null);
			//폴더에 저장된 파일 삭제함
			new File(savePath + "\\" + renameFileName).delete();
		
		// 업로드된 새 파일이 있다면 ------------------------------------------------------
		}else if(originalFileName != null && (newOriginalFileName == null ||
				originalFileName.equals(newOriginalFileName) && 
				new File(savePath + "\\" + renameFileName).length() 
				== new File(savePath + "\\" + newRenameFileName).length())) {
			//원래 첨부파일이 있었고, 변경되지 않았을 때
			notice.setOriginalFilepath(originalFileName);
			notice.setRenameFilepath(renameFileName);
		}
		
		// 6. 서비스 객체 생성하고 메소드로 notice 객체 전달하고
		// 처리된 결과 받기
		int result = new NoticeService().updateNotice(notice);

		// 7. 받은 결과로 성공/실패 페이지 내보내기
		if (result > 0) {
			response.sendRedirect("nlist.ad");
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", notice.getNoticeNo() + "번 공지사항 수정 실패!");
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
