package view;

import java.util.Scanner;

import DAO.UsuarioDAO;
import model.Usuario;

public class Main {

	public static void main(String[] args) {
		Utilidades utilidades = new Utilidades();
		Scanner sc = new Scanner(System.in);
		Usuario usuario = new Usuario();
		UsuarioDAO dao = new UsuarioDAO();

		utilidades.apresentacao();

		utilidades.digitarLogin();
		usuario.setLogin(sc.nextLine());
		utilidades.digitarSenha();
		usuario.setSenha(sc.nextLine());
		utilidades.digitarPerfil();
		usuario.setPerfil(sc.nextLine());
		sc.close();

		dao.adicionarusuario(usuario);

		dao.fecharConexao();

	}

}
