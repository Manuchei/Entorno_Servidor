package eventos.dao;

import eventos.entidades.Usuario;

public interface UsuarioDao {

	Usuario buscarPorUsername(String username);

	boolean altaUsuario(Usuario usuario);

}
