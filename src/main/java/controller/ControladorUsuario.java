/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.BaseDatos;
import java.util.List;
import model.Usuario;

public class ControladorUsuario {

    BaseDatos bd = BaseDatos.getInstancia();

    // Create 
    public boolean crearUsuario(Usuario usuarioNuevo) {
        List<Usuario> lista = bd.getListaUsuarios();
        boolean resultado = lista.add(usuarioNuevo);
        return resultado;
    }

    //TO DO CRUD (Update, Read, Delete)
  
    // DELETE
    public boolean eliminarUsuario(String usuario) {
        BaseDatos bd = BaseDatos.getInstancia();
        return bd.getListaUsuarios().removeIf(u -> u.getUsuario().equalsIgnoreCase(usuario));
    }
    
    // Read
    public String consultarUsuarios() {
        StringBuilder listaUsuarios = new StringBuilder();
        int contador = 1;

        for (Usuario usuario : bd.getListaUsuarios()) {
            listaUsuarios.append("Usuario ").append(contador).append(":").append(usuario.toString()).append("\n\n");
            contador++;
        }

        return listaUsuarios.toString();
    }
    
    // Validar si el usuario se encuentra registrado en la lista de usuarios y guardar su posicion en la lista
    public int existeUsuario(String usuario) {

        List<Usuario> lista = bd.getListaUsuarios();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getUsuario().equals(usuario)) {
                return i; // Retorna la posicion donde se encuentra el usuario registrado en la lista
            }
        }

        return -1; // Retorna -1 si no encuentra el usuario en la lista
    }

    // Hacer que la contrasenia no sea la misma que el usuario tiene registrada
    public boolean mismaContrasenia(int posUsuario,String contrasenia)
    {
        List<Usuario> lista = bd.getListaUsuarios();
        
        // Si la contrasenia es la misma, retronara false
        if (lista.get(posUsuario).getContrasena().equals(contrasenia)) {
            return false;
        }
        
        // Si la nueva contrasenia es diferente a la actual retornara true
        return true;
    }
    
    // Update
    public boolean actualizarUsuario(int posUsuario, Usuario usuarioActualizado) {

        List<Usuario> lista = bd.getListaUsuarios();

        try {
            
            // Actualizar la contrasenia del usuario registrado
            lista.set(posUsuario, usuarioActualizado);
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }
}
