package codigo;
import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
/*
 * Autor; Marta M�rquez Olalla
 * 
 * Aqu� se encuentra el marcador de Arkanoid.
 * */
import acm.graphics.GObject;
import acm.graphics.GRect;

public class Marcador extends GRect{
	//Sistema del Marcador.
	GLabel points = new GLabel("");
	GLabel texto = new GLabel("");
	int puntuacion=0;

	//Sistema de vidas.
	GLabel lives = new GLabel ("");
	GLabel partida = new GLabel("");
	int vidas=3;

	public Marcador(double width, double height, Color _color) {
		super(width, height);

		setFillColor(_color);
		setFilled(true);
		//Fuente del Marcador.
		texto.setLabel("0");
		texto.setFont(new Font("Verdana", Font.BOLD, 18));
		//Fuente del marcador de vidas.
		partida.setLabel("3");
		partida.setFont(new Font("Verdana", Font.BOLD, 18));
		//fuente de los t�tulos.
		points.setLabel("MARCADOR");
		points.setFont(new Font("Verdana", Font.BOLD, 18));
		lives.setLabel("VIDAS");
		lives.setFont(new Font("Verdana", Font.BOLD, 18));
	}
	//Vamos a hacer un m�todo para que el texto del GLabel se ponga delante del rect�ngulo del marcador.
	//Hacemos una llamada a Arkanoid.
	public void dibuja(Arkanoid _arkanoid){
		_arkanoid.add(points, getX()+440, getY()+40);
		//A�adimos el rect�ngulo.
		_arkanoid.add(this, getX()+460, getY()+60);
		//A�adimos el texto.
		_arkanoid.add(texto, getX()+10, getY()+30);
	}
	//M�todo para actualizar el marcador cuando se elimine un ladrillo.
	public void actualizaMarcador(int puntos){
		puntuacion+=puntos;
		//con la cadena vac�a (""+ puntuaci�n, lo que hacemos es convertir un n�mero a un string.
		texto.setLabel("" + puntuacion);
	}
	//M�todo donde vamos a controlar las vidas.
	public void actualizaVidas(int a){
		vidas-=a;
		partida.setLabel("" + vidas);
	}
	//M�todo que nos situa el marcador de vidas en la pantalla.
	public void dibuja2(Arkanoid _arkanoid){
		_arkanoid.add(lives, getX()+440, getY()+140);
		_arkanoid.add(this, getX()+460, getY()+160);
		_arkanoid.add(partida, getX()+10, getY()+30);
	}




}
