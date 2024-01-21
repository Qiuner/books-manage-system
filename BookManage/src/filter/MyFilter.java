package filter;


//
// import javax.servlet.Filter;
// import javax.servlet.FilterChain;
// import javax.servlet.FilterConfig;
// import javax.servlet.ServletException;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import javax.servlet.annotation.WebFilter;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
// import java.io.IOException;
//
// @WebFilter("/*")
// public class MyFilter implements Filter {
//
//     @Override
//     public void init(FilterConfig filterConfig)  {
//     }
//
//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//             throws IOException, ServletException {
//         HttpServletRequest httpRequest = (HttpServletRequest) request;
//         HttpServletResponse httpResponse = (HttpServletResponse) response;
//         HttpSession session = httpRequest.getSession(false);
//
//         // 排除index.jsp
//         if (httpRequest.getRequestURI().endsWith("/index.jsp")||httpRequest.getRequestURI().endsWith("/main")
//                 ||httpRequest.getRequestURI().endsWith("/registerUser.jsp")||httpRequest.getRequestURI().endsWith("/Login.jsp")
//                 ||httpRequest.getRequestURI().endsWith("/login")) {
//             chain.doFilter(request, response); // 放行
//             return;
//         }
//
//         if (session == null ) {
//             // 用户未登录，重定向到登录页面
//             httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
//         } else {
//             // 用户已登录，继续请求的处理
//             chain.doFilter(request, response);
//         }
//     }
//
//     @Override
//     public void destroy() {
//     }
// }
//
