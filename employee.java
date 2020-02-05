package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/employee")
public class employee extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			response.setContentType("text/html");
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres","M10023");
			//System.out.println("step1");
			PreparedStatement ps=con.prepareStatement("select * from employee");
			//System.out.println("step2");
			ResultSet rs=ps.executeQuery();
			PrintWriter ps1=response.getWriter();
			ps1.println("<html><body><table><tr><td>id</td><td>name</td><td>role</td></tr>");
			while(rs.next())
			{
				ps1.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");
			}
			ps1.println("</table></body></html>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}