package br.com.fabricadeprogramador;

import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		//testCadastrar();
		//testAlterar();
		//testExcluir();
		//testSalvar();
		//testBuscarPorId(1);
		//testBuscarTodos();
		
		testAutenticar();
	}

	public static void testBuscarPorId(Integer id) {
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usuario = usuDAO.buscaPorId(id);
		
		System.out.println(usuario);
	}
	
	public static void testBuscarTodos() {
		UsuarioDAO usuDAO = new UsuarioDAO();
		List<Usuario> usuarioList = usuDAO.buscaTodos();
		
		for (Usuario usuario : usuarioList)
			System.out.println(usuario);
	}

	public static void testCadastrar() {
		Usuario usu = new Usuario();
		usu.setNome("Jãozão");
		usu.setLogin("jzao");
		usu.setSenha("123");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);
		
		System.out.println("Cadastrado com sucesso!");
	}

	public static void testAlterar() {
		Usuario usu = new Usuario();
		usu.setId(4);
		usu.setNome("Jãozão da Silva");
		usu.setLogin("jzaos");
		usu.setSenha("123456");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usu);
		
		System.out.println("Alterado com sucesso!");
	}
	
	public static void testExcluir() {
		Usuario usu = new Usuario();
		usu.setId(4);
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluido com sucesso!");
	}
	
	public static void testSalvar() {
		Usuario usu = new Usuario();
		usu.setId(2);
		usu.setNome("Maria de Souza");
		usu.setLogin("mar");
		usu.setSenha("123");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);
		
		System.out.println("Salvo com sucesso!");
	}
	
	public static void testAutenticar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		Usuario usuario = new Usuario();
		usuario.setLogin("jzao");
		usuario.setSenha("123");
		
		Usuario usuRetorno = usuarioDAO.autenticar(usuario);
		
		System.out.println(usuRetorno);
	}

}
