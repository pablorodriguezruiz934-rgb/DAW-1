package ud3.ejemplos;

import java.util.Scanner;

public class InviertePalabraDebug {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce una palabra: ");
        String palabra = scanner.nextLine();

        char[] caracteres = palabra.toCharArray();

        // Invertir el array de caracteres
        for (int i = 0; i < caracteres.length / 2; i++) {
            char temp = caracteres[i];
            caracteres[i] = caracteres[caracteres.length - 1 - i];
            caracteres[caracteres.length - 1 - i] = temp;
        }

        // Mostrar la palabra invertida
        String palabraInvertida = new String(caracteres);
        System.out.println("Palabra invertida: " + palabraInvertida);
    }
}
