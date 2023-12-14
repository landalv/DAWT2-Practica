package es.studium.ServletPractica;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletPractica
 */
@WebServlet("/ServletPractica")
public class ServletPractica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletPractica() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		//Construimos la estructura basica de la Pagina Web
		String pageContent = """
				<!DOCTYPE html>
				<html lang="es">
				<head>
				<meta charset="UTF-8">
				<title>PracticaCLV</title>
				<link rel="icon" type="image/x-icon" href="Favicon.png">
				<!-- BOOSTRAP -->
				<link
					href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
					rel="stylesheet"
					integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
					crossorigin="anonymous">
				</head>
				<body>
				""";

		// Sacamos valores del formulario
		String numerosStr = request.getParameter("numeros");
		int numero = Integer.parseInt(request.getParameter("numero"));
		System.out.println("Numeros: "+numerosStr);
		System.out.println("Numero a evaluear: "+numero);

		// Creamos el array de numeros
		
		try {
			//Creamos la lista de numero
			Modelo listaNumeros = new Modelo(numerosStr);
			pageContent += """
					<div class="container">
						<div class="row justify-content-center">
							<h1 class="col-12 text-center my-5">PracticaCLV</h1>
						</div>
					</div>
					<div class="container">
					<div class="row justify-content-center">		
					<div class='alert alert-primary col-12 my-5' role='alert'>
					"""+listaNumeros.getMsg(numero)+"""
					</div>
					</div>
					</div>
					""";
			
		} catch (NumberFormatException e) {
			System.err.println("Error: "+e);
			// La cadena no se puede convertir a un número
			pageContent += datosErroneos(pageContent);
		}
		//Cerramos la estructura basica de la Pagina Web
		pageContent += """
						<div class="container">
							<div class="row justify-content-center">
								<a href=index.html class="btn btn-primary col-12 col-lg-8 mb-1 mx-1">Volver</a>
							</div>
						</div>
						<script
						src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
						integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
						crossorigin="anonymous"></script>
						</body>
						</html>
						""";
		//Enviamos a FrontEnd la web construida
				response.getWriter().append(pageContent);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// Funcion de control de errores
	private String datosErroneos(String pageContent) {
		//devolver pagina de error.
		pageContent += """
				<div class="container">
					<div class="row justify-content-center">
						<h1 class="col-12 text-center my-5">PracticaCLV</h1>
					</div>
				</div>
				<div class="container">
				<div class="row justify-content-center">		
				<div class='alert alert-danger col-12 my-5' role='alert'>
				"""+"Solo puede introducir numeros separados por coma y el numero a buscar."+"""
				</div>
				</div>
				</div>
				""";
		return pageContent;
	}

}
