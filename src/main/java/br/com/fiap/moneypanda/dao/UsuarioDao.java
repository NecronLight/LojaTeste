package br.com.fiap.moneypanda.dao;

import br.com.fiap.moneypanda.model.Usuario;

public interface UsuarioDao {

    boolean validarUsuario(Usuario usuario);
}
