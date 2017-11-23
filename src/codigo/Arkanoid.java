/*
 * Autor; Marta Márquez Olalla
 * 
 * Los paquetes los usamos para dividir código. Los métodos públicos los podemos ver desde nuestro
 * propio paquete, pero no desde otros paquetes.
 * 
 * Arkanoid orientado a objetos.
 * */

package codigo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.*;

public class Arkanoid extends acm.program.GraphicsProgram{
	
	//En instancia llamamos a la clase Pelota que hemos creado.
	Pelota pelota1= new Pelota(10,Color.cyan);
	Pelota pelota2= new Pelota(30,30);
	//crea el cursor del juego.
	Barra barra1 = new Barra(60, 20, Color.red);
	//Declaramos los ladrillos en instancia en Arkanoid por si queremos modificar las dimensiones.
	//Si todos los ladrillos van a ser iguales, podemos declarar las dimensiones en la clase Ladrillo.
	int anchoLadrillo= 25;
	int altoLadrillo= 15;
	int numLadrillos=14;
	
	//Barra lateral.
	int espacioMenu=200;
	
	//El sistema del marcador.
	
	Marcador marcador = new Marcador(20,40,Color.white);
	
	
	
	

	
	public void init(){
		addMouseListeners();
		setSize(600,600);
		
		add (pelota1, 0, getHeight()*0.70 - pelota1.getHeight());
		add (barra1, 0, getHeight()*0.80);
		
		//Barra lateral
		GRect lateral=new GRect(3, getHeight());
		lateral.setFilled(true);
		add (lateral,getWidth()-espacioMenu -lateral.getWidth()-pelota1.getWidth(), 0);
		
		
	}
	
	public void run(){
		dibujaNivel01();
		//dibujaNivel02();
		//Llamamos al método DIBUJA en la clase Marcador. Esto nos mete los DOS add en su orden correcto.
		marcador.dibuja(this);
		//add (marcador, 5,0);
		//add(marcador.texto, 0,20);
		while (true){
			pelota1.muevete(this);
			pause(5);
		}
	}
	
	//El movimiento de la barra no tiene sentido que esté aquí. Hay que ponerlo en la clase barra.
	public void mouseMoved(MouseEvent evento){
		//getWidth es el ancho de la pantalla.
		//getX es la X donde se encuentra el ratón. Es la X del evento, lo que produce que la barra siga al ratón.
		barra1.mueveBarra(evento.getX(),getWidth(),this);
	}
	
	private void dibujaNivel01(){
		for(int j=0; j<14; j++){
			for(int i=j; i<14; i++){               //Calcular la posición media de la pirámide.
				Ladrillo miLadrillo = new Ladrillo(getWidth()/2 -(numLadrillos*anchoLadrillo)/2+ anchoLadrillo*i-anchoLadrillo*j/2 - espacioMenu/2,
						                           altoLadrillo*j+altoLadrillo, 
						                           anchoLadrillo,
						                           altoLadrillo,
						                           Color.pink);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	
	private void dibujaNivel02(){
		for(int j=0; j<9; j++){
			for(int i=0; i<14; i++){
				Ladrillo miLadrillo = new Ladrillo(getWidth()/2 -(numLadrillos*anchoLadrillo)/2 + i*anchoLadrillo-espacioMenu/2, j*altoLadrillo, anchoLadrillo,altoLadrillo, Color.pink);
				add(miLadrillo);
				pause(7);
			}
		}
	}

}
