package codigo;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;

/*
 * Autor; Marta Márquez Olalla
 * */

public class Boton extends GRect{
	
	GLabel restart = new GLabel ("");

	public Boton(double width, double height) {
		super(width, height);
		restart.setLabel("RESTART");
		restart.setFont(new Font("Verdana", Font.BOLD, 18));
	}
	
	public void dibuja3(Arkanoid _arkanoid){
		_arkanoid.add(this, _arkanoid.getX()+440, _arkanoid.getY()+400);
		_arkanoid.add(restart, getX()+10, getY()+27);
	}
	

	}
