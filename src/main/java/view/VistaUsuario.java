/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ControladorUsuario;
import java.util.Scanner;
import model.Usuario;

public class VistaUsuario {

    // CREATE
    public void solicitarDatos() {

        Scanner sc = new Scanner(System.in);
        System.out.println("**** BIENVENIDO AL SISTEMA SINGLETON ****");
        System.out.println("Por favor ingresar el usuario:");
        String usuarioDigitado = sc.nextLine();
        System.out.println("Por favor ingresar la contrasena:");
        String contrasenaDigitada = sc.nextLine();

        Usuario usuarioModelo = new Usuario(usuarioDigitado, contrasenaDigitada);

        ControladorUsuario usuarioControlador = new ControladorUsuario();
        boolean respuesta = usuarioControlador.crearUsuario(usuarioModelo);

        if (respuesta) {
            System.out.println("CREACION EXITOSA DEL USUARIO");
        } else {
            System.out.println("No se logro insertar el nuevo usuario");
        }
    }
     private void eliminar() {
        String usuario = JOptionPane.showInputDialog("Ingrese usuario a eliminar:");
        boolean eliminado = controlador.eliminarUsuario(usuario);

        JOptionPane.showMessageDialog(null,
                eliminado ? "Usuario eliminado correctamente."
                        : "Usuario no encontrado.");
    }

    

    // UPDATE
    public void actualizarUsuario() {
        
        ControladorUsuario usuarioControlador = new ControladorUsuario();
        Scanner sc = new Scanner(System.in);
        String usuario = "";
        String nuevaContrasenia = "";
        boolean diferenteContrasenia;

        System.out.println("Ingresa tu nombre de usuario");
        usuario = sc.nextLine(); // Nombre del usuario registrado en la base de datos

        // Validar si el usuario existe y guardar la posicion donde se encuentra el usuario
        int posUsuario = usuarioControlador.existeUsuario(usuario);

        if (posUsuario != -1) {

            // El usuario debera ingresar una contrasenia diferente a la actual
            // De lo contrario se le seguira solicitando que ingrese una nueva contrasenia
            do {

                System.out.println("Ingresa la nueva contraseña para: " + usuario);

                // Guardar nueva contrasenia
                nuevaContrasenia = sc.nextLine();

                // Validar que la nueva contrasenia sea diferente a la actual
                diferenteContrasenia = usuarioControlador.mismaContrasenia(posUsuario, nuevaContrasenia);

                if (diferenteContrasenia) {
                    // Objeto usuario auxiliar con el mismo usuario original pero con la nueva contrasenia
                    Usuario usuarioModelo = new Usuario(usuario, nuevaContrasenia);

                    // Actualizar la contrasenia del usuario en la base de datos
                    boolean actualizado = usuarioControlador.actualizarUsuario(posUsuario, usuarioModelo);

                    if (actualizado) {
                        System.out.println("¡Usuario Actualizado Existosamente!");
                    } else {
                        System.out.println("Algo salio mal al actualizar el usuario. Vuelve a intentarlo.");
                    }
                } else {
                    System.out.println("La nueva contraseña debe ser diferente a la contraseña actual que tienes.");
                }

            } while (!diferenteContrasenia);

        } else {
            System.out.println("El usuario no se encuentra registrado en la base de datos.");
        }
    }
}
