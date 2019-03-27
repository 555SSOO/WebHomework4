import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletForm", urlPatterns = {"/ser1"})
public class ServletForm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<body><h1>Evo sta ste uneli:</h1>");
        out.println("Ime: "+ime+"<br />");
        out.println("Prezime: "+prezime+"<br />");
        out.println("</body>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<body><h1>Evo sta ste uneli:</h1>");
        out.println("Ime: "+ime+"<br />");
        out.println("Prezime: "+prezime+"<br />");
        out.println("</body>");
        out.close();
    }
}
