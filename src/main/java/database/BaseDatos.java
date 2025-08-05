/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class BaseDatos {

    private static BaseDatos instancia;
    private List<Usuario> listaUsuarios;

    public BaseDatos() {
        listaUsuarios = new ArrayList<Usuario>();

        Usuario usuario = new Usuario("clopez", "12345");

        listaUsuarios.add(usuario);

        usuario = new Usuario("admin", "13579");

        listaUsuarios.add(usuario);
    }

    public static BaseDatos getInstancia() {
        if (instancia == null) {
            instancia = new BaseDatos();
        }
        return instancia;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
}
