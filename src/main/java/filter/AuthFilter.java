package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.Traveller.TRAVELLER;

/**
 * check if user doesn't have session redirect to authorisation page
 * exclude some addresses of URI
 * @author Ali Yan
 * @version 1.0
 */
@WebFilter("/*")
public class AuthFilter implements HttpFilter {
    private FilterConfig filterConfig = null;

    @Override
    public void init (FilterConfig filterConfig) throws ServletException{
        this.filterConfig = filterConfig;
            }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

            if (request.getSession().getAttribute(TRAVELLER)==null &&
                !(request.getRequestURI().contains("registration")
                         || request.getRequestURI().contains("entrance")
                         || request.getRequestURI().contains("local")))

                request.getRequestDispatcher("/WEB-INF/log in/index.jsp").forward(request, response);

            else
            chain.doFilter(request, response);
        }

    @Override
    public void destroy() {
        this.filterConfig=null;
    }
}



