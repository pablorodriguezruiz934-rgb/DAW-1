package Examen;

import java.util.Scanner;
import java.util.regex.Pattern;



public class ex2 {
	//Este es el inicio de nuestra excepcion
	class DNIException extends Exception {
	    public DNIException(String mensaje) {
	        super(mensaje);
	    }
	}
	
	
	
	
	
	
	
	//Aqui hara la escritura del DNI y en caso de que este mal lanzara la "DNIException"
	public static String DNI() throw DNIException {
		
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el DNI (8 numeros y una letra): ");
        String dni = sc.nextLine();
     // detectara si son letras tanto mayusculas como minusculas de la A a la Z en el 8 caracter
        if (!Pattern.matches( "\\d{8}[a-zA-Z]",dni)) {
            throw new DNIException("");
        }
        return dni;
    }
	public static String cambiarDigito(String dni, int valor, char nom2) throw DNIException {
		//A qui dtectaremos que el dni tenga 8 numeros
        if (valor < 1 || valor > 8) {
            throw new DNIException("El valot no debe ser ni menor que 1 ni mayor de 8");
          
        }
        //Si mi caracter no es un digito dsltara nuestra excepcion
        if (!Character.isDigit(nom2)) {
            throw new DNIException("Debe ser un digito.");
        }
        //paso mi cadena a caracteres
        char[] dnichar = dni.toCharArray();

       
        dnichar[valor - 1] = nom2;

        // PONDREMOS la letra del deni  en mayusculas
        dnichar[8] = Character.toUpperCase(dnichar[8]);

        return new String(dnichar);
    }

	
	public static void main(String[] args) throw DNIException {
		//Aqui haremos un try por si si funciona el dni introducido y el catch que sera el principio del  mensaje que soltara
		try {
            String dni = DNI();
            System.out.println("DNI correcto!!: " + dni.toUpperCase());
            //cambia la posicion por un 9
            String modletra = cambiarDigito(dni, 3, '9');
            System.out.println("DNI modificado: " + modletra);
            //Captura nuestra exceptcion y lanza el mensaje que escribimos previamente junto a este
        } catch (DNIException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

	}

