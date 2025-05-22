package webSocket;

import java.io.IOException;
import java.util.Base64;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*") // ou restrinja para /ws/* se o WebSocket estiver nesse caminho
public class BasicAuthFilter implements Filter {
	private static final String AUTH_USER = "admin";
    private static final String AUTH_PASS = "1234";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String authHeader = req.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String base64Credentials = authHeader.substring("Basic ".length());
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String[] values = credentials.split(":", 2);

            if (values.length == 2 && AUTH_USER.equals(values[0]) && AUTH_PASS.equals(values[1])) {
                chain.doFilter(request, response);
                return;
            }
        }

        res.setHeader("WWW-Authenticate", "Basic realm=\"Acesso restrito\"");
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}