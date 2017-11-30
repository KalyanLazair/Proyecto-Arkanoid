package codigo;
/*
 * Autor; Marta Márquez Olalla
 * 
 * La clase Ladrillo sirve para dibujar los ladrillos del juego.
 * */

import java.awt.Color;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class Ladrillo extends GRect{



	/**
	 * Construye un ladrillo
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param _color
	 * 
	 * Futuros Cambios; Que admita número de golpes.
	 */

	public Ladrillo(double x, double y, double width, double height, Color _color) {
		super(x, y, width, height);
		setFilled(true);
		setFillColor(_color);

	}




}
