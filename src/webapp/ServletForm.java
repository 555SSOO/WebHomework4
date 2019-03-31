package webapp;

import com.google.gson.Gson;
import shared.Grade;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

@WebServlet(name = "webapp.ServletForm", urlPatterns = {"/grade"})
public class ServletForm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String grade = request.getParameter("grade");
        // Get the assistant name and the grade and try to send it
        if (grade.matches("-?(0|[1-9]\\d*)") && sendGrade(request.getParameter("assistant_name"), grade) == 1) {
            // If the data was sent successfully, print out OK message
            out.println("<body><h1>Grade successfully sent to the database</h1>");
        } else {
            // If not, notify the user
            out.println("<body><h1>There was an issue sending the grade to the database</h1>");
        }
        out.println("</body>");
        out.close();

    }

    // Returns 1 if the sending was ok, 0 if there was an error
    private int sendGrade(String assistant_name, String grade) {
        try {
            // Send the data in json form to the localhost port 12345
            new PrintWriter(new Socket("localhost", 12345).getOutputStream(), true).println(new Gson().toJson(new Grade(assistant_name.toUpperCase(), Integer.valueOf(grade))));
            return 1;
        } catch (IOException e) {
            return 0;
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Map<String, Double> average_grade_map = getAvgGrades();
        out.println("<body><p>");
        if(!average_grade_map.isEmpty()) {
            average_grade_map.entrySet().forEach(grade -> out.println(grade.getKey() + " " + grade.getValue() + "</br>"));
        }
        else{
            out.println("No grades in the database");
        }
        out.println("</p>");
        out.println("</body>");
        out.close();
    }

    private Map<String, Double> getAvgGrades() throws IOException {
        // Send the request to the localhost port 12345
        Socket socket = new Socket("localhost", 12345);
        new PrintWriter(socket.getOutputStream(), true).println("GET");
        // Get the json response from the server and return it as a map
        return new Gson().fromJson(new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine(), Map.class);
    }


}
