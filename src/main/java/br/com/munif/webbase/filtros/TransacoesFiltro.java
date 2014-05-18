/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.webbase.filtros;

import br.com.munif.util.Persistencia;
import br.com.munif.webbase.entidades.VisualizacaoDePagina;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author munifgebarajunior
 */
@WebFilter(filterName = "TransacoesFiltro", urlPatterns = {"/*"})
public class TransacoesFiltro implements Filter {

    public static final ThreadLocal<EntityManager> tlem = new ThreadLocal<EntityManager>();

    private FilterConfig filterConfig;

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Throwable problem = null;

        try {
            tlem.set(Persistencia.getInstancia().getEmf().createEntityManager());
            tlem.get().getTransaction().begin();
            chain.doFilter(request, response);
            tlem.get().getTransaction().commit();
        } catch (Throwable t) {
            if (tlem.get().getTransaction().isActive()) {
                tlem.get().getTransaction().rollback();
            }
            problem = t;
            t.printStackTrace();
        }

        registraVisualizacao(request, problem);
        
        tlem.get().close();

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
        Persistencia.getInstancia().getEmf().close();
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    private void registraVisualizacao(ServletRequest request, Throwable problem) {
        try {
            tlem.get().getTransaction().begin();
            VisualizacaoDePagina visualizacao = new VisualizacaoDePagina();
            HttpServletRequest hsq = (HttpServletRequest) request;
            visualizacao.setUsuario(hsq.getUserPrincipal() != null ? hsq.getUserPrincipal().getName() : "Não logado");
            visualizacao.setIpRemoto(request.getRemoteAddr());
            visualizacao.setResultado(problem == null ? "OK" : limitaA250(problem.toString()));
            visualizacao.setUrl(limitaA250(hsq.getRequestURL().toString()));
            visualizacao.setSessao(hsq.getSession().getId());
            visualizacao.setInfo(limitaA250(hsq.getHeader("User-Agent")));
            tlem.get().persist(visualizacao);
            tlem.get().getTransaction().commit();
        } catch (Throwable ex) {
            ex.printStackTrace();
            if (tlem.get().getTransaction().isActive()) {
                tlem.get().getTransaction().rollback();
            }
        }
    }
    
    private String limitaA250(String s){
        if (s.length()>250){
            return s.substring(0,250);
        }
        return s;
    }

}
