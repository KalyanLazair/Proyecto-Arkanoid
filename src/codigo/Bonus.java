package codigo;

import java.awt.Color;
import java.util.Random;

import acm.graphics.GOval;
import acm.util.RandomGenerator;

/*
 * Autor; Marta Márquez Olalla
 * 
 * En esta clase vamos a declarar el sistema de bonus, que estableceremos utilizando una variable
 * random.
 * */

public class Bonus extends GOval{
	
	RandomGenerator aleatorio = new RandomGenerator();

	public Bonus(double x, double y, double width, double height, Color _color) {
		super(x, y, width, height);
		
	}
}
		
		
		
		
		/*int test = 0;
	    int min = 1; 
		int max = 10;
		// Llamamos al metodo bonus para saber si toca o no
		test = getRandom(min, max);
		
		if(test <= 1){ 	
			setFillColor(_color);
			setFilled(true);
		}else{
			System.out.print("Sin bonus !!");
		
		}

		//System.out.print(test);
	
	}
	// Calculo de numero random para saber si toca bonus
	public int getRandom(int from, int to) {
		
	    if (from < to)
	        return from + new Random().nextInt(Math.abs(to - from));
	    return from - new Random().nextInt(Math.abs(to - from));
	}*/
	
/*
	int randomNumber = 0;
	int minRand = 1; 
	int maxRand = 10;
	// Llamamos al metodo bonus para saber si toca o no
	randomNumber = getRandom(minRand, maxRand);
	
	if(randomNumber <= 1){
		_arkanoid.bonus = new Pelota(aleatorio.nextInt(4,4),
				aleatorio.nextInt(4,4));
		yVelocidad=1;
	}else{
		System.out.print("\n" + "Sin bonus !!");
	}		*/
	


