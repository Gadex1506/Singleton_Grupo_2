/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.BaseDatos;
import java.util.List;
import model.Usuario;

public class ControladorUsuario {

    public boolean crearUsuario(Usuario usuarioNuevo) {
        BaseDatos bd = BaseDatos.getInstancia();
        List<Usuario> lista = bd.getListaUsuarios();
        boolean resultado = lista.add(usuarioNuevo);
        return resultado;
    }

    //TO DO CRUD (Update, Read, Delete)
}
