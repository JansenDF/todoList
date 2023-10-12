package br.com.jansenlima.todolist.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  )
    throws ServletException, IOException {
    var authorization = request.getHeader("Authorization");
    var authEncoded = authorization.substring("Basic".length()).trim();
    byte[] authDecode = Base64.getDecoder().decode(authEncoded);
    var authString = new String(authDecode);
    var credentials = authString.split(":");

    String username = credentials[0];
    String password = credentials[1];

    System.out.println(username);
    System.out.println(password);

    filterChain.doFilter(request, response);
  }
}
