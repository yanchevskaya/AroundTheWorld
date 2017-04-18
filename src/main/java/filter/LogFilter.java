package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import model.Traveller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Filter for logging
 * @author Ali Yan
 * @version 1.0
 */
@WebFilter("/*")
public class LogFilter implements HttpFilter {
    private FilterConfig config;
    private Logger log;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        log = LogManager.getLogger(LogFilter.class);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String infoTraveller;
        log.info(request.getRemoteAddr() + " " + request.getRequestURI());
        Traveller user = (Traveller)request.getSession().getAttribute("traveller");
        if (user!=null) {
            infoTraveller = user.getFirstName() + " " + user.getLastName();
            log.info(request.getRemoteHost() + " " +request.getRequestURI()+". User "+ infoTraveller);
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String name = params.nextElement();
                String value = request.getParameter(name);
                log.info(request.getRemoteHost() + " " +request.getRequestURI()+". User "+ infoTraveller+". Request Params {" + name + "=" + value + "}");
            }
            chain.doFilter(request,response);
        }else
            chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        this.config=null;
    }
}
