/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.57
 * Generated at: 2020-08-17 00:03:24 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.kh_005fweb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class event_005ftest_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js\"></script>\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(document).ready(function() {\r\n");
      out.write("\t\talert(\"시작\")\r\n");
      out.write("\t\t$(\"#myid\").blur(function() {\r\n");
      out.write("\t\t\t$(\"#result\").html(\"아이디가 focus를 잃어버렸습니다. - onblur속성과 동일\");\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\"#myid\").focus(function() {\r\n");
      out.write("\t\t\t$(\"#result\").html(\"아이디가 focus를 받았습니다. - onfocus속성과 동일\");\r\n");
      out.write("\t\t});\r\n");
      out.write("\t})\r\n");
      out.write("\tfunction checkAll(){\r\n");
      out.write("\t\t if (!checkUserId(myform.id.value)) {\r\n");
      out.write("\t\t\t alert(\"checkUserId\")\r\n");
      out.write("\t            return false; //완료되지 않으면 submit하지 않기\r\n");
      out.write("\t           \r\n");
      out.write("\t   \t\t \r\n");
      out.write("\t     }\r\n");
      out.write("\t\t return true;//확인이 완료되었을 때 submit */\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction checkUserId(id) {\r\n");
      out.write("\t\t//Id가 입력되었는지 확인하기\r\n");
      out.write("\t\t\r\n");
      out.write("\t\talert(id)\r\n");
      out.write("\t\t var idRegExp = /^[a-zA-z0-9]{3,}$/; \r\n");
      out.write("\t\t/*  → / : 자바스크립트의 정규표현식의 처음과 끝을 의미한다.\r\n");
      out.write("\t\t → [ ] : 문자셋이다. 예를 들면 [a-z]라고 적을경우 정규표현식에 만족해야하는 값들은 반드시 a~z사이의 값만 넣을 수 있다.\r\n");
      out.write("\t\t → ^ : 문장의 처음을 뜻한다.\r\n");
      out.write("\t\t → $ : 문장의 마지막을 뜻한다.\r\n");
      out.write("\t\t → { } : 문자열 길이를 뜻한다. 예를 들어 {4,12}일 경우 최소 길이 4, 최대 길이 12이다.\r\n");
      out.write(" */\r\n");
      out.write("\r\n");
      out.write("        if (!idRegExp.test(id)) {\r\n");
      out.write("\t\t     alert(\"아이디는 영문 대소문자와 숫자가 입력되어도 좋으며 4~12자리로 입력해야합니다!\");\r\n");
      out.write("\t\t     myform.id.value = \"\";\r\n");
      out.write("\t\t     myform.id.focus();\r\n");
      out.write("\t\t     return false;\r\n");
      out.write("        }\r\n");
      out.write("        return true;\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<form method=\"get\" action=\"result.jsp\" name=\"myform\" onsubmit=\"return checkAll()\">\r\n");
      out.write("\t\t아이디:<input type=\"text\" size=\"50\" maxlength=\"10\" name=\"id\" id=\"myid\"><br />\r\n");
      out.write("\t\t패스워드:<input type=\"password\" name=\"pass\"><br /> 아이디:<input\r\n");
      out.write("\t\t\ttype=\"text\" size=\"50\" maxlength=\"10\" name=\"id2\" id=\"myid2\"\r\n");
      out.write("\t\t\tonblur=\"alert('focus를 잃었습니다.')\"><br />\r\n");
      out.write("\t\t<!-- onfocus=\"alert('focus를 얻었습니다.')\" -->\r\n");
      out.write("\t\t패스워드:<input type=\"password\" name=\"pass2\"><br /> <input\r\n");
      out.write("\t\t\ttype=\"submit\" value=\"로그인\">\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<div id=\"result\"></div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
