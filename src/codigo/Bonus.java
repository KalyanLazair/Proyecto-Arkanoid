package codigo;

import java.awt.Color;
import java.util.Random;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.util.RandomGenerator;

/*
 * Autor; Marta Márquez Olalla
 * 
 * En esta clase vamos a declarar el sistema de bonus, que estableceremos utilizando una variable
 * random.
 * */

public class Bonus extends GOval{
	
	double yVelocidad = 1;
	RandomGenerator aleatorio = new RandomGenerator();

	public Bonus(double width, Color _color) {
		super(width, width);
		setFilled(true);
		setFillColor(_color);
	}
	
	public void cae (Arkanoid _arkanoid){
		move(0,yVelocidad);
		chequeaContacto(getWidth(),getHeight(),_arkanoid);
	}
	
	private boolean chequeaContacto(double posX, double posY, Arkanoid _arkanoid){
		boolean noContacto=true;
		GObject auxiliar;
		auxiliar = _arkanoid.getElementAt(posX,posY);
		
		if(auxiliar instanceof Barra){
			if(auxiliar.getX()+auxiliar.getWidth() >= posX+getWidth()){
				yVelocidad*=-1;
				_arkanoid.barra1.setSize(100, 20);
				
			}
			
		}
		return noContacto;
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
	


