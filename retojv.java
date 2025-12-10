package reto3;
import java.util.Scanner;

class ElGordoException extends Exception {
public ElGordoException(String mensaje) {
   super(mensaje); 
	}
}

public class retojv{	
	
	public static int[] creaBomboNumeros(int numBolas) {
		int[] bomboNumeros = new int[numBolas];
		
		for (int i=0;i<numBolas;i++)
			bomboNumeros[i]=i;
		
		System.out.println("Bombo de números creado...");
		return bomboNumeros;
	}
	
	public static String[] creaBomboPremios(int numPremios) {
		String[] bomboPremios = new String[numPremios];
		
		bomboPremios[0]="PRIMER PREMIO";
		bomboPremios[1]="SEGUNDO PREMIO";
		bomboPremios[2]="TERCER PREMIO";
		bomboPremios[3]="CUARTO PREMIO";
		bomboPremios[4]="CUARTO PREMIO";
		
		for (int i=5;i<=12;i++)
			bomboPremios[i]="QUINTO PREMIO";
		
		for (int i=13;i<numPremios;i++)
			bomboPremios[i]="pedrea";
		
		System.out.println("Bombo de premios creado...");
		return bomboPremios;
	}
	
	public static int dameNumero(int[] bombo) {
		
		int numAgraciado = (int) (Math.random()*bombo.length);

		while (bombo[numAgraciado]==-1)
			numAgraciado = (int) (Math.random()*bombo.length);
		
		bombo[numAgraciado]=-1;
		
		return numAgraciado;
	}
	
	public static String damePremio(String[] bombo) {
		
		String premio;
		
		int indicePremiado = (int) (Math.random()*bombo.length);

		while (bombo[indicePremiado]=="")
			indicePremiado = (int) (Math.random()*bombo.length);		
		premio=bombo[indicePremiado];		
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
					
					if (premioAsignado.equals("PRIMER PREMIO"))
						premio = "Agraciado con: PRIMER PREMIO 400.000 € el décimo";
					
					else if (premioAsignado.equals("SEGUNDO PREMIO"))
						premio = "Agraciado con: SEGUNDO PREMIO 100.000 € el décimo";
					
					else if (premioAsignado.equals("TERCER PREMIO"))
						premio = "Agraciado con: TERCER PREMIO 50.000 € el décimo";
					
					else if (premioAsignado.equals("CUARTO PREMIO"))
						premio = "Agraciado con: CUARTO PREMIO 20.000 € el décimo";
					
					else if (premioAsignado.equals("QUINTO PREMIO"))
						premio = "Agraciado con: QUINTO PREMIO 6.000 € el décimo";

					else if (premioAsignado.equals("pedrea"))
						premio = "Agraciado con: PEDREA 100 € el décimo";
					
					else
						premio = "Agraciado con: " + premioAsignado;

					return premio;
				}
				
				int numeroPremiado = Integer.parseInt(numeroPremiadoStr);
				
				boolean esAnterior = miNumero == numeroPremiado - 1;
				boolean esPosterior = miNumero == numeroPremiado + 1;
				
				if (esAnterior || esPosterior) {
					
					if (premioAsignado.equals("PRIMER PREMIO"))
						premio = "Agraciado con aproximación al primero premio 2.000 € el décimo";
					
					else if (premioAsignado.equals("SEGUNDO PREMIO"))
						premio = "Agraciado con aproximación al segundo premio 1.250 € el décimo";

					else if (premioAsignado.equals("TERCER PREMIO"))
						premio = "Agraciado con aproximación al tercer premio 960 € el décimo";

					return premio;
				}
			}
		} catch (NumberFormatException e) {
			System.err.println("Error al procesar el número de décimo: " + e.getMessage());
			return "Número no premiado (Error de formato)";
		}
			
		return premio;
	}
	
	public static void compruebaDecimos(String[] numerosSorteo, String[] premiosSorteo) throws ElGordoException{
		Scanner sc=new Scanner(System.in);	
		String miDecimo;
		String miPremio="";
		boolean terminarDeComprobar=false;
		boolean numeroValido=false;
		
		System.out.println("\nCOMPROBACION DE DECIMOS:");
		
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
				
			} while ( !terminarDeComprobar && !numeroValido);
		
			if(!terminarDeComprobar)
				miPremio=heSidoAgraciado(numerosSorteo,premiosSorteo, miDecimo);
				System.out.println(miPremio);		
			    if (miPremio.contains("PRIMER"))
			    	throw new ElGordoException("A celebrar el gordo!!");
		
		}while (!terminarDeComprobar);
		
	}
		
		

	public static void main(String[] args) {
		
		final int MAX_NUMEROS=100000;
		final int MAX_PREMIOS=1807;
		
		int numeroAgraciado;
		String premio="";
		
				
		int[] bomboBolas = new int[MAX_NUMEROS];
		String[] bomboPremios = new String[MAX_PREMIOS];

		String[] listaNumerosSorteo = new String[MAX_PREMIOS];
		String[] listaPremiosSorteo = new String[MAX_PREMIOS];
		
		try {
		
			bomboBolas=creaBomboNumeros(MAX_NUMEROS);		
			bomboPremios=creaBomboPremios(MAX_PREMIOS);
			
			System.out.println("\nLISTADO OFICIAL DE PREMIOS:\n");
		
			int j=0;
			for (int i=0;i<MAX_PREMIOS;i++) {
				numeroAgraciado=dameNumero(bomboBolas);
				String numeroFormateado=String.format("%05d",numeroAgraciado);
			
				premio=damePremio(bomboPremios);
				
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
