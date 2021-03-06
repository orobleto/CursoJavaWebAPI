package com.curso.java.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.curso.java.entidades.Usuario;

/**
 * @author Octavio Robleto
 * @see https://orobleto.github.io/octaviorobleto.github.io/
 * 
 */

@WebServlet(name = "validacion", urlPatterns = "/sesiones")
public class Validacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Validacion.class);

	/*
	 * Creamos la lista inicial de usuarios del sistema Esto deberia ser una
	 * busqueda a la base de datos
	 * 
	 */
	private List<Usuario> listaUsuarios = Arrays.asList(new Usuario("administrador", "administrador.1234"),
			new Usuario("usuario1", "usuario1.1234"), new Usuario("usuario2", "usuario2.1234"),
			new Usuario("usuario3", "usuario3.1234"), new Usuario("usuario4", "usuario4.1234"));
	private Usuario usuarioSesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Validacion() {
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

		// si entra por el metodo get a la URL invalidamos la sesion si existe
		if (request.getSession().getId() != null) {
			request.getSession().invalidate();

			// seteamos el atributo mensaje
			request.setAttribute("alerta", "SESION");

			// reenviamos la peticion con los objetos request y response
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// tomamos los parametros que vienen del formulario
		String usuario = request.getParameter("usuario");
		String clave = request.getParameter("clave");

		HttpSession sesionUsuario = null;

		// si existe el usuario se crean los atribustos de la sesion
		if (existeUsuario(usuario, clave)) {
			// creamos la sesion
			sesionUsuario = request.getSession();

			// seteamos el atributo con el usuario
			sesionUsuario.setAttribute("sesionUsuario", getUsuarioSesion());

			logger.debug("Se creo el ID -> " + sesionUsuario.getId());
			// codificamos la URL previendo que no soporte cookies el navegador
			@SuppressWarnings("deprecation")
			String encodeRedirectUrl = response.encodeRedirectUrl("bienvenido.jsp");
			response.sendRedirect(encodeRedirectUrl);

		} else {
			// seteamos el atributo mensaje
			request.setAttribute("alerta", "CREDENCIALES");

			// reenviamos la peticion con los objetos request y response
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @return true si existe usuario en la lista sino false
	 */
	@SuppressWarnings("static-access")
	private boolean existeUsuario(String usuario, String clave) {

		// bucle for each que recorre la lista
		boolean existe = false;

		for (Usuario usuarioTMP : listaUsuarios) {
			if (usuarioTMP.getUsuario().equalsIgnoreCase(usuario) && usuarioTMP.getClave().equals(clave)) {
				setUsuarioSesion(usuarioTMP);
				existe = true;
				break;
			}
		}
		logger.info("existe Usuario ->" + usuario + " - " + clave + "? " + existe);
		return existe;
	}

	/**
	 * @return the usuarioSesion
	 */
	public Usuario getUsuarioSesion() {
		return usuarioSesion;
	}

	/**
	 * @param usuarioSesion the usuarioSesion to set
	 */
	public void setUsuarioSesion(Usuario usuarioSesion) {
		this.usuarioSesion = usuarioSesion;
	}

}
