package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet("/usuariocontroller")
public class UsuarioController extends HttpServlet {

	public UsuarioController() {
		System.out.println("Chamou UsuarioController()");
	}
	
	private UsuarioDAO getDAO() {
		return new UsuarioDAO();
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Chamou init()");
		super.init();
	}

	@Override
	public void destroy() {
		System.out.println("Chamou destroy()");
		super.destroy();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Chamou doGet() " + req);
		resp.setContentType("text/html");

		String acao = req.getParameter("acao");
		if (acao.equals("excluir")) {
			String id = req.getParameter("id");
			Usuario usuario = new Usuario();
			if (id != null && !id.equals(""))
				usuario.setId(Integer.parseInt(id));
			
			getDAO().excluir(usuario);

			resp.sendRedirect("usuariocontroller?acao=listar");
		} else if (acao.equals("listar")) {
			List<Usuario> lista = getDAO().buscaTodos();
			
			req.setAttribute("lista", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausuarios.jsp");
			dispatcher.forward(req, resp);
		} else if (acao.equals("alterar")) {
			Usuario usuario = getDAO().buscaPorId(Integer.parseInt(req.getParameter("id")));
			
			req.setAttribute("usuario", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
		} else if (acao.equals("cadastrar")) {
			Usuario usuario = new Usuario();
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			
			req.setAttribute("usuario", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Chamou doPost() " + req);

		String id = req.getParameter("id");
		Usuario usuario = new Usuario();
		if (id != null && !id.equals(""))
			usuario.setId(Integer.parseInt(id));
		usuario.setNome(req.getParameter("nome"));
		usuario.setLogin(req.getParameter("login"));
		usuario.setSenha(req.getParameter("senha"));

		getDAO().salvar(usuario);

		String mensagem = "Sucesso ao cadastrar o usuario " + req.getParameter("nome");
		System.out.println(mensagem);
		resp.getWriter().println(mensagem);
	}

}
