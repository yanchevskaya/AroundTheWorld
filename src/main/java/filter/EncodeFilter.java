package filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * add UTF-8 encode
 * @author Ali Yan
 * @version 1.0
 */
@WebFilter("/*")
public class EncodeFilter implements HttpFilter {
    private FilterConfig filterConfig;
    private Logger log;

    public void init (FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        log = LogManager.getLogger(EncodeFilter.class);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (!"UTF-8".equalsIgnoreCase(request.getCharacterEncoding()))
            request.setCharacterEncoding("UTF-8");
        log.info ("Charset was set");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}

