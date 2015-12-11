package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {

	private Connection conn = ConexaoFactory.getConnection();

	public void cadastrar(Usuario usu) {
		String sql = "insert into usuario (nome, login, senha) values (?, ?, ?)";

		try (PreparedStatement preparador = conn.prepareStatement(sql)) {
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar(Usuario usu) {
		String sql = "update usuario set nome=?, login=?, senha=? where id=?";

		try (PreparedStatement preparador = conn.prepareStatement(sql)) {
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			preparador.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(Usuario usu) {
		String sql = "delete from usuario where id=?";

		try (PreparedStatement preparador = conn.prepareStatement(sql)) {
			preparador.setInt(1, usu.getId());
			preparador.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void salvar(Usuario usuario) {
		if (usuario.getId() != null && usuario.getId() != 0) {
			alterar(usuario);
		} else {
			cadastrar(usuario);
		}
	}

	/**
	 * Busca de um registro no banco de dados por ID de usuario
	 * 
	 * @param id
	 *            É um inteiro que representa o numero do id do usuario a ser
	 *            recuperado
	 * @return Retorna um objeto Usuario quando encontra ou null quando nao
	 *         encontra
	 */
	public Usuario buscaPorId(Integer id) {
		String sql = "select * from usuario where id=?";

		try (PreparedStatement preparador = conn.prepareStatement(sql)) {
			preparador.setInt(1, id);

			ResultSet result = preparador.executeQuery();
			if (result.next()) {
				Usuario usu = new Usuario();
				usu.setId(id);
				usu.setNome(result.getString("nome"));
				usu.setLogin(result.getString("login"));
				usu.setSenha(result.getString("senha"));

				return usu;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Realiza a busca de todos os registros da tabela Usuario
	 * 
	 * @return Uma lista de objetos do tipo Usuario
	 */
	public List<Usuario> buscaTodos() {
		String sql = "select * from usuario order by id";
		List<Usuario> lista = new ArrayList<>();

		try (PreparedStatement preparador = conn.prepareStatement(sql)) {

			ResultSet result = preparador.executeQuery();
			while (result.next()) {
				Usuario usu = new Usuario();
				usu.setId(result.getInt("id"));
				usu.setNome(result.getString("nome"));
				usu.setLogin(result.getString("login"));
				usu.setSenha(result.getString("senha"));

				lista.add(usu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Usuario autenticar(Usuario usuConsulta) {
		String sql = "select * from usuario where login=? and senha=?";

		try (PreparedStatement preparador = conn.prepareStatement(sql)) {
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());

			ResultSet result = preparador.executeQuery();
			if (result.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(result.getInt("id"));
				usuario.setNome(result.getString("nome"));
				usuario.setLogin(result.getString("login"));
				usuario.setSenha(result.getString("senha"));

				return usuario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
