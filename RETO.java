package reto3;

import java.util.Scanner;

public class RETO {
	class ElGordoException extends Exception {
		public ElGordoException(String mensaje) {
		 super(mensaje); 
			}
		}

		public class LoteriaSolucion {	

			

			//devuelve un array de tamaño numBolas con todos los números del sorteo

			public static int[] creaBomboNumeros(int numBolas) {
			int[] bomboNumeros = new int[numBolas];
			for (int i=0;i<numBolas;i++)
			bomboNumeros[i]=i;
			System.out.println("Bombo de números creado...");
			return bomboNumeros;

			}


			//devuelve un array de tamaño numPremios con todos los premios del sorteo

			public static String[] creaBomboPremios(int numPremios) {

			String[] bomboPremios = new String[numPremios];

			

			bomboPremios[0]="PRIMER PREMIO"; // gordo de navidad
			bomboPremios[1]="SEGUNDO PREMIO";	// un segundo premio
			bomboPremios[2]="TERCER PREMIO";	// un tercer premio
			bomboPremios[3]="CUARTO PREMIO"; // dos cuartos premios
			bomboPremios[4]="CUARTO PREMIO";
			

			for (int i=5;i<=12;i++)
			bomboPremios[i]="QUINTO PREMIO";

	
			// el resto hasta completar 1.807 premios son "pedrea"

			for (int i=13;i<numPremios;i++)

			bomboPremios[i]="pedrea";

			System.out.println("Bombo de premios creado...");

			return bomboPremios;

			}


			//devuelve un numero al azar del bombo de números, que será objeto de un premio.

			//Si un número ya ha salido, no debe volver a salir

			public static int dameNumero(int[] bombo) {

			int numAgraciado = (int) (Math.random()*bombo.length);

			// si el número ya había salido, generamos otro al azar

			while (bombo[numAgraciado]==-1)

			numAgraciado = (int) (Math.random()*bombo.length);

			
			// marcamos el número que ha salido con -1 para que no vuelva a salir

			bombo[numAgraciado]=-1;

			return numAgraciado;

			}


			//devuelve un premio al azar del bombo de premios

			public static String damePremio(String[] bombo) {

			String premio;

			int indicePremiado = (int) (Math.random()*bombo.length);

			// si el premio ya había salido, generamos otro al azar

			while (bombo[indicePremiado]=="")

			indicePremiado = (int) (Math.random()*bombo.length);
			premio=bombo[indicePremiado];
			// marcamos el premio que ha salido con una cadena vacía

			bombo[indicePremiado]="";

			return premio;
			}

			public static String heSidoAgraciado(String[] numerosSorteo, String[] premiosSorteo, String miDecimo){

			String premio="Número no premiado"; 

			try {

			int miNumero = Integer.parseInt(miDecimo);
			for (int i=0;i<numerosSorteo.length;i++) {

			String numeroPremiadoStr = numerosSorteo[i];

			String premioAsignado = premiosSorteo[i];

			if (numeroPremiadoStr.equals(miDecimo)) { 

			premio=("Agraciado con: "+premioAsignado);
			return premio; 
			}
			int numeroPremiado = Integer.parseInt(numeroPremiadoStr);

			boolean esAnterior = miNumero == numeroPremiado - 1;

			boolean esPosterior = miNumero == numeroPremiado + 1;

			if (esAnterior || esPosterior) {

			if (premioAsignado.equals("PRIMER PREMIO")) {
			premio = "Agraciado con aproximación al primero premio 2.000 € el décimo";
			return premio;
			}
			if (premioAsignado.equals("SEGUNDO PREMIO")) {
			premio = "Agraciado con aproximación al segundo premio 1.250 € el décimo";
			return premio; 
			}
			}
			}
			} catch (NumberFormatException e) {
			System.err.println("Error al procesar el número de décimo: " + e.getMessage());
			return "Número no premiado (Error de formato)";
			}
			return (premio);
			}

			//Implementa un bucle para comprobar, haciendo uso del método heSidoAgraciado, si nuestro décimo tiene o no premio

			public static void compruebaDecimos(String[] numerosSorteo, String[] premiosSorteo) throws ElGordoException{

			Scanner sc=new Scanner(System.in);	

			String miDecimo;

			String miPremio="";

			boolean terminarDeComprobar=false;

			boolean numeroValido=false;

			System.out.println("\nCOMPROBACION DE DECIMOS:");

			//Compruebo si me ha tocado la lotería

			do {

			do {

			System.out.println("\nIntroduzca los 5 dígitos de su décimo (fin para terminar)");

			miDecimo=sc.next();

			terminarDeComprobar=miDecimo.toLowerCase().equals("fin")?true:false;

			if (!terminarDeComprobar) {

			numeroValido=((miDecimo.length()==5) && (miDecimo.matches("[0-9]+")))?true:false;

			}	

			if (!numeroValido)

			System.out.println("Formato incorrecto.");

			
			} while ( !terminarDeComprobar && !numeroValido); //Valido la entrada y si quiero continuar

			

			if(!terminarDeComprobar)

			miPremio=heSidoAgraciado(numerosSorteo,premiosSorteo, miDecimo);

			System.out.println(miPremio);	

			if (miPremio.contains("PRIMER"))

			throw new ElGordoException("A celebrar el gordo!!");

			}while (!terminarDeComprobar);
			}
			public static void main(String[] args) {

			

			final int MAX_NUMEROS=100000; // números del sorteo

			final int MAX_PREMIOS=1807; // premios del sorteo

			

			int numeroAgraciado; //número que sale del bombo

			String premio=""; //premio que sale del bombo

			int[] bomboBolas = new int[MAX_NUMEROS];

			String[] bomboPremios = new String[MAX_PREMIOS];
			String[] listaNumerosSorteo = new String[MAX_PREMIOS]; // [i][0]: número agraciado [i][1]: premio

			String[] listaPremiosSorteo = new String[MAX_PREMIOS];
			try {
			//Creamos los bombos con sus bolas

			bomboBolas=creaBomboNumeros(MAX_NUMEROS);	

			bomboPremios=creaBomboPremios(MAX_PREMIOS);

			//Mostramos el listado completo de números premiados

			System.out.println("\nLISTADO OFICIAL DE PREMIOS:\n");

			int j=0;
			for (int i=0;i<MAX_PREMIOS;i++) {

			//Sacamos un número del bombo y lo rellenamos con ceros a la izquierda

			numeroAgraciado=dameNumero(bomboBolas);

			String numeroFormateado=String.format("%05d",numeroAgraciado);

			//Sacamos un premio del bombo

			premio=damePremio(bomboPremios);
			//Añado el número y su premio a la lista oficial del sorteo
			listaNumerosSorteo[j]=numeroFormateado;
			listaPremiosSorteo[j]=premio;
			j++;
			System.out.println("Numero:"+numeroFormateado+" agraciado con: "+premio);	

			}
			compruebaDecimos(listaNumerosSorteo, listaPremiosSorteo);
			} catch (ElGordoException e) {
			System.out.println(e.getMessage());
			}

			catch (Exception e) {
			System.out.println("Error:"+e.getMessage());
			} finally {
			System.out.println("\nSorteo finalizado");	
			}	

			}

		}
}
