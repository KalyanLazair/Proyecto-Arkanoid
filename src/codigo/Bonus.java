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

	RandomGenerator aleatorio = new RandomGenerator();
	//Le damos una velocidad de +1 en el eje Y para que la bolita de bonus caiga.
	double yVelocidad = 1;

	public Bonus(double width, Color _color) {
		super(width, width);
		setFilled(true);
		setFillColor(_color);
	}
	//Función en la que la bola bonus cae a la velocidad indicada en instancia.
	public void cae (Arkanoid _arkanoid){
		move(0,yVelocidad);
		if(chequeaContacto(getX(), getY(), _arkanoid)){//Chequeo esquina superior izquierda.
			if(chequeaContacto(getX()+getWidth(), getY(), _arkanoid)){//Chequeo esquina superior derecha.
				if(chequeaContacto(getX(), getY()+getHeight(), _arkanoid)){//Chequeo esquina inferior izquierda.
					if(chequeaContacto(getX()+getWidth(), getY()+getHeight(), _arkanoid)){//Chequeo esquina inferior derecha.
					}
				}				
			}
		}
	}
	//Aquí chequeamos el contacto con la barra.
	public boolean chequeaContacto(double posX, double posY, Arkanoid _arkanoid){
		boolean noContacto=true;
		GObject auxiliar;
		auxiliar = _arkanoid.getElementAt(posX,posY);

		if(auxiliar instanceof Barra){
			//if(auxiliar.getX()+auxiliar.getWidth() >= posX+getWidth()){
			if(generaRandom(0, 10) == 1){
				_arkanoid.barra1.setSize(100, 20);
			}
			if(generaRandom(0,10) == 2){
				_arkanoid.barra1.setSize(30,20);
			}
			if(generaRandom(0,10) == 3){
				_arkanoid.add(_arkanoid.pelota2, 10,_arkanoid.getHeight()*0.70);
				_arkanoid.segundaPelota = true;
			}
			if(generaRandom(0,10) == 4){

			}
			noContacto=false;

			//}

		}
		return noContacto;
	}

	public int generaRandom(int low, int high){
		int rand=aleatorio.nextInt(low,high);
		return rand;
	}
}
