package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() { //생성자
        System.out.println("EncodingFilter 생성됨...");
    }

   /**
    * @see Filter#destroy()
    */
   public void destroy() { //소멸 메모리에서 사라짐
      System.out.println("EncodingFilter 소멸됨...");
   }

   /**
    * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
    */
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      System.out.println("EncodingFilter doFilter run...");
      // place your code here
      
      // 전송방식이 post일때는 반드시 request 에 대해서 인코딩해야 함
      HttpServletRequest hrequest = (HttpServletRequest)request;
      if(hrequest.getMethod().equalsIgnoreCase("post")) {
         System.out.println("post 전송시에만 인코딩 처리됨");
         request.setCharacterEncoding("utf-8");
      }
      
      // pass the request along the filter chain
      chain.doFilter(request, response);
   }

   /**
    * @see Filter#init(FilterConfig)
    */
   public void init(FilterConfig fConfig) throws ServletException { //init 초기화될때
      // TODO Auto-generated method stub
   }

}