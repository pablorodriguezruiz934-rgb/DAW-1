package Ud1;

import java.util.Scanner;



public class Loteriaequip{

    // Excepción propia(PRR)
    static class GordoException extends Exception {
        public GordoException(String mensaje) {
            super(mensaje);
        }
    }

	//devuelve un array de tamaño numBolas con todos los números del sorteo(MMC)
    public static int[] creaBomboNumeros(int numBolas) {
        int[] bombo = new int[numBolas];
        for (int i=0; i<numBolas; i++)
            bombo[i] = i;

        System.out.println("Bombo de números creado...");
        return bombo;
    }

  //devuelve un array de tamaño numPremios con todos los premios del sorteo(PRR)
    public static String[] creaBomboPremios(int numPremios) {
        String[] bomboPremios = new String[numPremios];

        //completa(MMC)
        bomboPremios[0] = "PRIMER PREMIO";//gordo de navidad
        bomboPremios[1] = "SEGUNDO PREMIO";//un segundo premio
        bomboPremios[2] = "TERCER PREMIO";//un tercer premio

        // Dos cuartos premios
        bomboPremios[3] = "CUARTO PREMIO";//un primer cuarto premio
        bomboPremios[4] = "CUARTO PREMIO";//un segundo cuarto premio

        // Ocho quintos premios(PRR)
        for (int i = 5; i < 13; i++)
            bomboPremios[i] = "QUINTO PREMIO";

        // Resto pedrea
        for (int i = 13; i < numPremios; i++)
            bomboPremios[i] = "PEDREA";

        System.out.println("Bombo de premios creado...");
        return bomboPremios;
    }

  //devuelve un numero al azar del bombo de números, que será objeto de un premio.
  	//Si un número ya ha salido, no debe volver a salir(MMC)
    public static int dameNumero(int[] bombo) {
        int numAgraciado = (int) (Math.random() * bombo.length);

        while (bombo[numAgraciado] == -1) {
            numAgraciado = (int) (Math.random() * bombo.length);
        }
     // marcamos el número que ha salido con -1 para que no vuelva a salir(MMC)
        int numero = bombo[numAgraciado];
        bombo[numAgraciado] = -1;
        return numero;
    }

    // Devuelve un premio al azar(PRR)
    public static String damePremio(String[] bombo) {
        int indice = (int)(Math.random() * bombo.length);

        while (bombo[indice] == null) {
            indice = (int)(Math.random() * bombo.length);
        }

        String premio = bombo[indice];
        bombo[indice] = null;
        return premio;
    }
	//Comprueba si un décimo ha sido agraciado y en tal caso indica por consola el premio(MMC)
    public static String heSidoAgraciado(String[] nums, String[] premios, String decimo) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i].equals(decimo))
                return premios[i];
        }
        String premio = "Número no premiado";
        return premio;
    }

  
	//Implementa un bucle para comprobar, haciendo uso del método heSidoAgraciado, si nuestro décimo tiene o no premio
	// Debe comprobar que se introducen exactamente 5 dígitos entre el 0 y el 9, por ejemplo 04544
	// Al introducir fin, finaliza la comprobación de décimos(PRR)
    public static void compruebaDecimos(String[] numerosSorteo, String[] premiosSorteo)
            throws GordoException {

        Scanner sc = new Scanner(System.in);
        String miDecimo;
        
        System.out.println("\nCOMPROBACIÓN DE DÉCIMOS:");
        miDecimo=sc.nextLine();
        if(miDecimo.length()==5) {
        	
        }

        while (true) {
            System.out.print("Introduce tu décimo (5 dígitos) o 'fin': ");
            miDecimo = sc.nextLine();

            if (miDecimo.equalsIgnoreCase("fin"))
                break;

            if (!miDecimo.matches("\\d{5}"))
                throw new GordoException("El décimo no es válido. Deben ser 5 dígitos.");
            
            System.out.println("Resultado: " +  heSidoAgraciado(numerosSorteo, premiosSorteo, miDecimo));
        }
    }
	
				
            public static void main(String[] args) {

                final int MAX_NUMEROS = 100000;//numeros del sorteo
                final int MAX_PREMIOS = 1807;//premios del sorteo

                int[] bomboBolas;
                String[] bomboPremios;

                String[] listaNumerosSorteo = new String[MAX_PREMIOS];//numero que sale del bombo(mmc)
                String[] listaPremiosSorteo = new String[MAX_PREMIOS];//premio que sale del bombo(mmc) 

                try {//creamos los bombos con sus bolas
                    bomboBolas = creaBomboNumeros(MAX_NUMEROS);
                    bomboPremios = creaBomboPremios(MAX_PREMIOS);
                    //Mostramos el listado completo de números premiados
                    System.out.println("\nLISTADO OFICIAL DE PREMIOS:\n");
                    	int j= 0;
                    for (int i = 0; i < MAX_PREMIOS; i++) {
                    	//Sacamos un número del bombo y lo rellenamos con ceros a la izquierda
                    	//sacamos un premio del bombo
                    	////Añado el número a la lista oficial del sorteo en la posición j
                        int numeroAgraciado = dameNumero(bomboBolas);
                        String numeroFormateado = String.format("%05d", numeroAgraciado);
                        String premio = damePremio(bomboPremios);
                      //Añado el premio a la lista oficial del sorteo en la posición j(prr)
        				//COMPLETAR
                        listaNumerosSorteo[j] = numeroFormateado;
                        listaPremiosSorteo[j] = premio;
                        j++;
                        System.out.println("Número: " + numeroFormateado +
                                           " agraciado con: " + premio);
                    }
                	//COMPLETAR (capturar excepción propia)(Completar alguno de 2)
            		
                    compruebaDecimos(listaNumerosSorteo, listaPremiosSorteo);

              
                } catch (Exception e) {
                    System.out.println("Error general: " + e.getMessage());
                } finally {
                    System.out.println("\nSorteo finalizado");
                }
            }
        }