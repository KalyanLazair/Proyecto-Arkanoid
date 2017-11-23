package codigo;


/**
 * 
 * @author Marta M�rquez
 *
 *La clase Barra es la que dibuja el cursor del juego.
 */
import java.awt.event.MouseEvent;
import java.awt.Color;

import acm.graphics.*;

public class Barra extends GRect{
	
	/**
	 * Crea el cursor del juego.
	 * 
	 * @param width ancho del cursor.
	 * @param height alto del cursor.
	 * @param _color define el color del cursor.
	 */
	
	
	public Barra(double width, double height, Color _color) {
		super(width, height);
		setFilled(true);
		setFillColor(_color);
	}
	//Le pasamos un int que es la posici�n X de la barra y otro int que es el ancho de la pantalla.
	public void mueveBarra(int posX, int anchoPantalla, Arkanoid _arkanoid){
		//Posici�n X de la barra (esquina superior izquierda) + ancho de la barra(getWidth) tiene que ser MENOR
		//que el ancho de la pantalla.
		if(posX+getWidth()<anchoPantalla - _arkanoid.espacioMenu){
			//La barra sigue al rat�n a trav�s de la posici�n X. Mantiene la posici�n Y.
			setLocation(posX, getY());
		}
	}



}
