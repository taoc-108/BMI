package com.application.health;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ComputeBMI
 */
@WebServlet("/ComputeBMI")
public class ComputeBMI extends HttpServlet {
	private static final double PER_POUND = 0.45359237;
	private static final double PER_INCHES = 0.0254;
	private static final long serialVersionUID = 1L;
    private int totalViews = 0;
    private double convertWeight = 0.0d;
    private double convertHeight = 0.0d;
    private double bmi = 0.0d;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputeBMI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Compute bmi is running...");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("Compute bmi process has been destroyed, thread clean.");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		try {
			totalViews++;
		double weight = Double.parseDouble(request.getParameter("user_weight"));
		double height = Double.parseDouble(request.getParameter("user_height"));
		String weightCap = request.getParameter("weight_select");
		String heightCap = request.getParameter("height_select");
		System.out.println("Weight cap : " + weightCap);
		System.out.println("Height cap : " + heightCap);
		if(weightCap.contains("pounds")) {
			convertWeight = weight * PER_POUND;
		}
		if(weightCap.contains("kilograms")) {
			convertWeight = weight;
		}
		if(heightCap.contains("inches")) {
			convertHeight = height * PER_INCHES;
		}
		if(heightCap.contains("meters")) {
			convertHeight = height;
		}
		double bmi = convertWeight / (convertHeight * convertHeight);
		out.println("<p class='alert alert-success'>Your BMI (Body Mass Index) is : " + bmi + " </p>");
		RequestDispatcher rd = request.getRequestDispatcher("/index.html");
		rd.include(request, response);
		out.println(messageRoll(bmi));
		out.println("<p style='color:white; background-color:black; font-size:2em; font-family:verdana; text-align:center;'>Total Views : " + totalViews + "</p>");
		} catch(Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.include(request, response);
			out.println("<p class='alert alert-danger'>" + e.toString() + "</p>");
		}
		
	}
	public static String messageRoll(double bmi) {
		String msg = "<p class='alert alert-";
		if(bmi < 16) {
			msg += "danger'>You're seriously in underweight condition.</p>";
		}
		else if(bmi >= 16 && bmi < 18) {
			msg += "warning'>You're in underweight condition.</p>";
		}
		else if(bmi >= 18 && bmi < 24) {
			msg += "success'>You're in normal condition.</p>";
		}
		else if(bmi >= 24 && bmi < 29) {
			msg += "warning'>You're in overweight condition.</p>";
		}
		else if(bmi >= 29 && bmi < 35) {
			msg += "danger'>You're seriously in overweight condition.</p>";
		}
		else {
			msg += "danger'>You're gravely in overerweight condition.</p>";
		}
		return msg;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
