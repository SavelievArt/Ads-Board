import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NewAd extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        request.getRequestDispatcher("link.html").include(request, response);
        HttpSession session = request.getSession(false);
        String name = (String)session.getAttribute("name");
        String header = request.getParameter("header");
        String description = request.getParameter("description");

        out.println("<html><body>");
        String p = request.getContextPath();
        out.println("<LINK REL=\"StyleSheet\" HREF=\"/home/artem/IdeaProjects/laba15/web/style.css\" TYPE=\"text/css\">");

        out.println("<p>"+name+"</p>");
        out.println("<p>"+header+"</p>");
        out.println("</p>"+description+"</p>");

        StringBuilder sb = new StringBuilder();
        sb.append("\n"+header+";");
        sb.append(description+";");
        sb.append(name+";");
        sb.append(new java.util.Date().toString());

        String filePath = "/home/artem/IdeaProjects/laba15/src/main/java/ads.txt";
        String text = sb.toString();

        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text);
            bufferWriter.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }

        out.println("</html></body>");
        out.close();
    }

}
