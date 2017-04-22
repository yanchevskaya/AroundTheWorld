package filter;

import model.Traveller;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.Traveller.TRAVELLER;

/**
 * check if user  have session redirect to main page
 * from  some address
 * @author Ali Yan
 * @version 1.0
 */
@WebFilter({"/registration", "/entrance"})
public class LoginFilter implements HttpFilter {
  private FilterConfig filterConfig = null;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
  }

  @Override
  public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

    Traveller traveller = (Traveller) request.getSession().getAttribute(TRAVELLER);
    if (traveller != null && traveller.getId() != 0) {
      request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    } else
      chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    this.filterConfig = null;
  }
}


