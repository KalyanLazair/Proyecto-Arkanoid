package codigo;
import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
/*
 * Autor; Marta Márquez Olalla
 * 
 * Aquí se encuentra el marcador de Arkanoid.
 * */
import acm.graphics.GObject;
import acm.graphics.GRect;

public class Marcador extends GRect{

	GLabel texto = new GLabel("");
	int puntuacion=0;
	
	public Marcador(double width, double height, Color _color) {
		super(width, height);
		
		setFillColor(_color);
		setFilled(true);
		
		texto.setLabel("0");
		texto.setFont(new Font("Verdana", Font.BOLD, 18));
	}
	//Vamos a hacer un método para que el texto del GLabel se ponga delante del rectángulo del marcador.
	//Hacemos una llamada a Arkanoid.
	public void dibuja(Arkanoid _arkanoid){
		//Añadimos el rectángulo.
		_arkanoid.add(this, getX()+500, getY()+20);
		//Añadimos el texto.
		_arkanoid.add(texto, getX(), getY()+30);
	}
	//Método para actualizar el marcador cuando se elimine un ladrillo.
	public void actualizaMarcador(int puntos){
		puntuacion+=puntos;
		//con la cadena vacía (""+ puntuación, lo que hacemos es convertir un número a un string.
		texto.setLabel("" + puntuacion);
	}
	


}
