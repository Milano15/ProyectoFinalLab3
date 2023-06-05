import Cliente.Contraseña;
import Cliente.Mail;
import Cliente.TiposMail;
import Cliente.Usuario;
import Excepciones.*;

import java.util.Scanner;

public class ControladoraUsuario {
    static Scanner teclado = new Scanner(System.in);


    public Usuario menu(Inmobiliaria inmobiliaria) {
        Usuario usuario = new Usuario();
        System.out.println("Buen día ¿Qué le gustaría realizar?");
        int opcion = 0;
        System.out.println("1. Loguearse \n2. Registrarse");
        teclado.nextInt(opcion);
        String respuesta = "Si";

        switch (opcion) {
            case 1:
                while (respuesta.equalsIgnoreCase("si")) {
                    try {
                        usuario = login(inmobiliaria);
                        respuesta = "no";
                    } catch (UsuarioNoEncontradoException e) {
                        System.out.println("¿Desea volver a intentar?");
                        respuesta = teclado.nextLine();
                        throw new RuntimeException(e);
                    } catch (MalContraseñaException e) {
                        System.out.println("¿Desea volver a intentar?");
                        respuesta = teclado.nextLine();
                        throw new RuntimeException(e);
                    }
                }

                break;

            case 2:

                 usuario = registrarse();
                inmobiliaria.agregarUsuario(usuario);

                break;

            default:
                System.out.println("Valor ingresado no valido");
                break;
        }
        return usuario;
    }

    public Usuario login(Inmobiliaria inmobiliaria) throws UsuarioNoEncontradoException, MalContraseñaException {
        System.out.println("Ingrese su nombre");
        String nombre = teclado.nextLine();

        Usuario usuario = inmobiliaria.buscarUsuario(nombre);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado");

        }
        System.out.println("Ingrese su contraseña");
        teclado.nextLine();
        String contraseña = teclado.nextLine();
        if (!usuario.getContraseña().equals(contraseña)) {
            throw new MalContraseñaException("Contrasela incorrecta");
        }
        return usuario;

    }


    public Usuario registrarse() {
        String nombre = "";
        String contraseña = "";
        String dni = "";
        String mail = "";
        String auxMail = "";
        int edad = 0;
        boolean validacion = true;
        int tipoMail = 0;

        System.out.println("Ingrese su nombre y apellido");
        nombre = teclado.nextLine();
        Contraseña contra = new Contraseña();

        while (validacion) {
            System.out.println("Ingrese la contraseña (Una mayúscula, un número y 8 digitos como mínumo)");
            contraseña = teclado.nextLine();
            try {
                Contraseña.verificacion(contraseña);
                contra = new Contraseña(contraseña);
                validacion = false;
            } catch (TotalDigitosException e) {
                throw new RuntimeException(e); //Preguntar al profe como hacemos para decirle la cantidad de algo
            } catch (CantNumException e) {
                throw new RuntimeException(e);
            } catch (CantMayusException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Ingrese su DNI");
        dni = teclado.nextLine();

        while(auxMail == ""){
            System.out.println("Ingrese que tipo de mail usa.\n1.Gmail\n2.Hotmail\n3.Yahoo\n4.Otros");
            tipoMail = teclado.nextInt();
            auxMail = manuTipoMail(tipoMail);
        }
        if(tipoMail == 4){
            mail = auxMail;

        } else if (tipoMail == 1 || tipoMail == 2 || tipoMail == 3) {
            System.out.println("Ingrese la parte delantera del mail (Antes de @)");
            String aux = teclado.nextLine();
            mail = aux + auxMail;
        }
        Mail correo = new Mail(mail);

        System.out.println("Ingrese su edad");
        edad = teclado.nextInt();

        Usuario usuario = new Usuario(nombre, contra, dni,correo , edad);

        return usuario;

    }


    public String manuTipoMail(int eleccion) {
        String mail = "";
        boolean valido = false;
        switch (eleccion) {
            case 1:
                mail =  TiposMail.Gmail.getTipomail();
                break;

            case 2:
                mail = TiposMail.Hotmail.getTipomail();
                break;

            case 3:
                mail = TiposMail.Yahoo.getTipomail();
                break;

            case 4:
                if (valido == false) {
                    mail = teclado.nextLine();
                    valido = Mail.validarMail(mail);
                    if (valido == false) {
                        System.out.println("EL mail ingresado es incorrecto");
                    }
                }
                break;
            default:
                System.out.println("La opcion ingresada es invalida");
        }
        return mail;

    }

}



