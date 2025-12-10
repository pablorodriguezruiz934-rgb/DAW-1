package reto3;

import java.util.Random;
import java.util.Scanner;

public class retocls2 {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	String[] jugadores = {"perro","gato","pez","pajaro"};
	
	String[][] caracteristicas = {
			{"Messi","Ronaldo","Zapater"},
			{"barcelona","argentino","inter miami"},
			{"madrid","portugues","arabia"},
			{"psg","brasileño","santos"},
			{"zaragoza","español","canterano"}
	};
	
	int sec =(int)(Math.random()*jugadores.length);
	
	
	String adivinar = jugadores[sec];
	
	System.out.println("=== Adivina adivinanza ===");
	System.out.println("He pensado en un jugador de futbol. Haz preguntas usando palabras");
	System.out.println("Cuando creas saberlo, escribe el nombre del jugador");
	
	String palabra;
	boolean es = false;
	
	
		
	
}
}


