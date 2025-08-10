/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ControladorUsuario;
import java.util.Scanner;
import javax.swing.JOptionPane;
import model.Usuario;

public class VistaUsuario {

    // CREATE
    public void solicitarDatos() {

        Scanner sc = new Scanner(System.in);
        //System.out.println("**** BIENVENIDO AL SISTEMA SINGLETON ****");
        //System.out.println("Por favor ingresar el usuario:");
        //String usuarioDigitado = sc.nextLine();
        //System.out.println("Por favor ingresar la contrasena:");
        //String contrasenaDigitada = sc.nextLine();

        String usuarioDigitado = JOptionPane.showInputDialog("Por favor ingresar el usuario:");
        String contrasenaDigitada = JOptionPane.showInputDialog("Por favor ingresar la contraseña:");
        
        Usuario usuarioModelo = new Usuario(usuarioDigitado, contrasenaDigitada);

        ControladorUsuario usuarioControlador = new ControladorUsuario();
        boolean respuesta = usuarioControlador.crearUsuario(usuarioModelo);

        if (respuesta) {
            //System.out.println("CREACION EXITOSA DEL USUARIO");
            JOptionPane.showMessageDialog(null, "CREACION EXITOSA DEL USUARIO");
        } else {
            //System.out.println("No se logro insertar el nuevo usuario");
            JOptionPane.showMessageDialog(null, "No se logro insertar el nuevo usuario");
        }
    }

    // DELETE
    private void eliminar() {

        ControladorUsuario controlador = new ControladorUsuario();

        String usuario = JOptionPane.showInputDialog("Ingresa el usuario a eliminar:");
        boolean eliminado = controlador.eliminarUsuario(usuario);

        JOptionPane.showMessageDialog(null,
                eliminado ? "Usuario eliminado correctamente."
                        : "Usuario no encontrado.");
    }

    // READ
    public void consultarUsuarios() {
        ControladorUsuario usuarioControlador = new ControladorUsuario();

        //System.out.println("Lista de Usuarios:");
        System.out.println(usuarioControlador.consultarUsuarios());

        JOptionPane.showMessageDialog(null, "Usuarios Registrados en la Base de datos:");
        JOptionPane.showMessageDialog(null, usuarioControlador.consultarUsuarios());
    }

    // UPDATE
    public void actualizarUsuario() {

        ControladorUsuario usuarioControlador = new ControladorUsuario();
        Scanner sc = new Scanner(System.in);
        String usuario = "";
        String nuevaContrasenia = "";
        boolean diferenteContrasenia;

        //System.out.println("Ingresa tu nombre de usuario");
        //usuario = sc.nextLine(); // Nombre del usuario registrado en la base de datos
        usuario = JOptionPane.showInputDialog("Ingresa tu nombre de usuario");

        // Validar si el usuario existe y guardar la posicion donde se encuentra el usuario
        int posUsuario = usuarioControlador.existeUsuario(usuario);

        if (posUsuario != -1) {

            // El usuario debera ingresar una contrasenia diferente a la actual
            // De lo contrario se le seguira solicitando que ingrese una nueva contrasenia
            do {

                //System.out.println("Ingresa la nueva contraseña para: " + usuario);
                // Guardar nueva contrasenia
                //nuevaContrasenia = sc.nextLine();
                // Guardar nueva contrasenia
                nuevaContrasenia = JOptionPane.showInputDialog("Ingresa la nueva contraseña para: " + usuario);

                // Validar que la nueva contrasenia sea diferente a la actual
                diferenteContrasenia = usuarioControlador.mismaContrasenia(posUsuario, nuevaContrasenia);

                if (diferenteContrasenia) {
                    // Objeto usuario auxiliar con el mismo usuario original pero con la nueva contrasenia
                    Usuario usuarioModelo = new Usuario(usuario, nuevaContrasenia);

                    // Actualizar la contrasenia del usuario en la base de datos
                    boolean actualizado = usuarioControlador.actualizarUsuario(posUsuario, usuarioModelo);

                    if (actualizado) {
                        //System.out.println("¡Usuario Actualizado Existosamente!");
                        JOptionPane.showMessageDialog(null, "¡Usuario Actualizado Existosamente!");
                    } else {
                        //System.out.println("Algo salio mal al actualizar el usuario. Vuelve a intentarlo.");
                        JOptionPane.showMessageDialog(null, "Algo salio mal al actualizar el usuario. Vuelve a intentarlo.");
                    }
                } else {
                    //System.out.println("La nueva contraseña debe ser diferente a la contraseña actual que tienes.");
                    JOptionPane.showMessageDialog(null, "La nueva contraseña debe ser diferente a la contraseña actual que tienes.");
                }

            } while (!diferenteContrasenia);

        } else {
            //System.out.println("El usuario no se encuentra registrado en la base de datos.");
            JOptionPane.showMessageDialog(null, "El usuario no se encuentra registrado en la base de datos.");
        }
    }
}
