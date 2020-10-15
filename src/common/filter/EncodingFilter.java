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
    public EncodingFilter() { //������
        System.out.println("EncodingFilter ������...");
    }

   /**
    * @see Filter#destroy()
    */
   public void destroy() { //�Ҹ� �޸𸮿��� �����
      System.out.println("EncodingFilter �Ҹ��...");
   }

   /**
    * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
    */
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      System.out.println("EncodingFilter doFilter run...");
      // place your code here
      
      // ���۹���� post�϶��� �ݵ�� request �� ���ؼ� ���ڵ��ؾ� ��
      HttpServletRequest hrequest = (HttpServletRequest)request;
      if(hrequest.getMethod().equalsIgnoreCase("post")) {
         System.out.println("post ���۽ÿ��� ���ڵ� ó����");
         request.setCharacterEncoding("utf-8");
      }
      
      // pass the request along the filter chain
      chain.doFilter(request, response);
   }

   /**
    * @see Filter#init(FilterConfig)
    */
   public void init(FilterConfig fConfig) throws ServletException { //init �ʱ�ȭ�ɶ�
      // TODO Auto-generated method stub
   }

}