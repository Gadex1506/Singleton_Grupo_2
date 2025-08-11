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

    // MENU
    public void Menu() {

        Scanner sc = new Scanner(System.in);
        int op = 0;
        boolean entradaValida;

        JOptionPane.showMessageDialog(null, "BIENVENIDO AL SISTEMA SINGLETON");

        do {

            entradaValida = true;

            try {

                do {

                    do {

                        // Mostrar el menu y capturar la opcion digitada por el usuario
                        String opcion = JOptionPane.showInputDialog(
                                "Selecciona una de las opciones del siguiente menú:\n"
                                + "1. Consultar lista de usuarios registrados.\n"
                                + "2. Crear un nuevo usuario.\n"
                                + "3. Actualizar un usuario existente.\n"
                                + "4. Eliminar un usuario existente.\n"
                                + "0. Salir."
                        );

                        if (opcion == null) {
                            JOptionPane.showMessageDialog(null, "No ingresaste una opción. Vuelve a intentarlo.");
                            break;
                        }

                        op = Integer.parseInt(opcion); // Guardar la opcion ingresada y convertirla en int

                        if (op < 0 || op > 4) {
                            JOptionPane.showMessageDialog(null, "La opción que ingresaste no es valida. Vuelve a intentarlo.");
                        }

                        switch (op) {
                            case 0:
                                JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                                break;
                            case 1:
                                consultarUsuarios();
                                break;
                            case 2:
                                crearUsuario();
                                break;
                            case 3:
                                actualizarUsuario();
                                break;
                            case 4:
                                eliminar();
                                break;
                        }

                    } while (op < 0 || op > 4);

                } while (op != 0);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingresa un número válido.");
                entradaValida = false;
            }

        } while (!entradaValida);

    }

    // CREATE
    public void crearUsuario() {

        String usuarioDigitado = "";
        String contrasenaDigitada = "";

        do {

            usuarioDigitado = JOptionPane.showInputDialog("Por favor ingresar el usuario:");
            contrasenaDigitada = JOptionPane.showInputDialog("Por favor ingresar la contraseña:");

            try {

                if (usuarioDigitado.trim().isEmpty() || contrasenaDigitada.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debes ingresar un usuario y una contraseña. Vuelve a intentarlo.");
                } else {
                    break;
                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Debes ingresar un usuario y una contraseña. Vuelve a intentarlo.");

            }

        } while (true);

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

        JOptionPane.showMessageDialog(null, "Usuarios Registrados en la Base de datos:\n\n"
                + usuarioControlador.consultarUsuarios());
    }

    // UPDATE
    public void actualizarUsuario() {

        ControladorUsuario usuarioControlador = new ControladorUsuario();
        String usuario = "";
        int posUsuario;
        String nuevaContrasenia = "";
        boolean diferenteContrasenia;

        do {

            usuario = JOptionPane.showInputDialog("Ingresa tu nombre de usuario");

            try {

                if (!usuario.trim().isEmpty()) {
                    // Validar si el usuario existe y guardar la posicion donde se encuentra el usuario
                    posUsuario = usuarioControlador.existeUsuario(usuario);
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Debes ingresar un nombre de usuario.");
                }

            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, "Debes ingresar un nombre de usuario.");
            }

        } while (true);

        if (posUsuario != -1) {

            // El usuario debera ingresar una contrasenia diferente a la actual
            // De lo contrario se le seguira solicitando que ingrese una nueva contrasenia
            do {

                // Guardar nueva contrasenia
                do {

                    nuevaContrasenia = JOptionPane.showInputDialog("Ingresa la nueva contraseña para: " + usuario);

                    try {

                        if (nuevaContrasenia.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Digita una nueva contraseña.");
                        } else {
                            break;
                        }

                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(null, "Digita una nueva contraseña...");

                    }

                } while (true);

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
