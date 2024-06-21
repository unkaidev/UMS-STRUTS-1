package auth;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {
	private List<String> excludedUrls;

	public void init(FilterConfig config) throws ServletException {
		excludedUrls = Arrays.asList("/login.html", "/logout.html", "/login.jsp", "/error.jsp",
				"/400.jsp","/404.jsp","/language.html","index.jsp");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();

		if (isExcluded(uri, contextPath)) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = request.getSession(false);
		boolean authenticated = session != null && session.getAttribute("user") != null;
		if (authenticated) {
			Cookie[] cookies = request.getCookies();
			String userRole = null;
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("MS")) {
						userRole = cookie.getValue();
						break;
					}
				}
			}

			if (userRole != null) {
				if (hasAccess(userRole, uri, contextPath)) {
					chain.doFilter(request, response);
				} else {
					throw new AccessDeniedException("ERROR 400");
				}
			} else {
				response.sendRedirect(contextPath + "/login.jsp");
			}
		} else {
			response.sendRedirect(contextPath + "/login.jsp");
		}
	}

	private boolean isExcluded(String uri, String contextPath) {
		return excludedUrls.stream().anyMatch(url -> uri.equals(contextPath + url));
	}

	private boolean hasAccess(String role, String uri, String contextPath) {
		if ("ROLE_ADMIN".equals(role)) {
			return true;
		} else if ("ROLE_USER".equals(role)) {
			return (uri.equals(contextPath + "/list-user.html") || uri.equals(contextPath + "/view-user.html"));
		}
		return false;
	}

	public void destroy() {
	}
}
