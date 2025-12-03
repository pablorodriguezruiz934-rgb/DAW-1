package Examen;

import java.util.Scanner;

public class ex1 {
	
	static void rellenaEncuesta(String[][] encuesta) {
        Scanner sc = new Scanner(System.in);
        int j = 0, i = 0, k = 0;

        while (j < encuesta.length) {
            System.out.print("Introduce codigo postal \t('fin' para terminar) : ");
            String cod = sc.nextLine();
            if (cod.equalsIgnoreCase("fin")) {
                break;
            }

            System.out.print("Introduce edad: ");
            String ed = sc.nextLine();

            System.out.print("Introduce sexo (M/F): ");
            String sx = sc.nextLine();

            encuesta[i][0] = cod;
            i++;
            encuesta[k][1] = ed;
            k++;
            encuesta[j][2] = sx;
            j++;
        }
    }
	 static void muestraResultados(String[][] encuesta) {
		 
	        System.out.println("\n Resultados de la encuesta ");
	        for (String[] persona : encuesta) {
	            if (persona[0] != "") {
	                System.out.println("Codigo Postal: " + persona[0] + ", Edad: " + persona[1] + ", Sexo: " + persona[2]);
	            }
	        }
	    }
	 static int calculaMediaEdad( String[][] encuesta, char sexo, int codigoPostal) {
		 int s=0;
		 int cont;
		for(int v=0;v<encuesta.length;v++) {
			for(int p=0;p<encuesta[v].length;p++) {
				System.out.println(encuesta[v][p]);
			}
		}
		 
		 
		return codigoPostal;
		 
	 }



	
	
	
public static void main(String[] args) {
	//En caso de que llegasen mas habitantes se deberia modificar el tamaño de la matriz y el mismo caso si queremos introducir ms datos
	String[][] encuesta = new String[501][3];

    rellenaEncuesta(encuesta);
   muestraResultados(encuesta);
  // calculaMediaEdad(encuesta);
}
}
