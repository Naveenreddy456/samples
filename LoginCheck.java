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
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
{
		String ml=req.getParameter("email");
		System.out.println(ml);
		String pswd=req.getParameter("password");
		System.out.println(pswd);
		String query="select * from medinovix where email=? and  password=?";
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres","naveen456");
			//System.out.println("step1");
			PreparedStatement preparedStatement=connection.prepareStatement(query);
		//	System.out.println("step2");
			preparedStatement.setString(1, ml);
			preparedStatement.setString(2, pswd);
			ResultSet resultSet=preparedStatement.executeQuery();
		//	System.out.println("step3");
			
			if(resultSet.next())
			{
				PrintWriter printwriter=resp.getWriter();
				printwriter.println("Succesful");
			}
			else
			{
				PrintWriter printwriter=resp.getWriter();
				printwriter.println("unsuccesful");
			}
			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}