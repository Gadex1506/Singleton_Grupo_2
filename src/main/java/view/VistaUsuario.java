/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ControladorUsuario;
import java.util.Scanner;
import model.Usuario;

public class VistaUsuario {

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

}
