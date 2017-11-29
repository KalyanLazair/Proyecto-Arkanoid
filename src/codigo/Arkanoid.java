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
import java.util.Random;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class Arkanoid extends acm.program.GraphicsProgram{
	
	//En instancia llamamos a la clase Pelota que hemos creado.
	Pelota pelota1= new Pelota(10,Color.cyan);
	Bonus bonus= new Bonus(4, Color.MAGENTA);
	Pelota pelota2 = new Pelota (10,Color.cyan);

	//Bonus bonus= new Bonus(getWidth()-getWidth()/2, getHeight(), 10, 10, Color.MAGENTA);


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

	Marcador marcador = new Marcador(60,40,Color.white);

	Marcador partida = new Marcador(40,40, Color.white);

	//Pantalla de Game Over.

	GRect gameover = new GRect(400, 100);
	GLabel go = new GLabel("");

	public void init(){
		addMouseListeners();
		setSize(600,600);

		add (pelota1, 0, getHeight()*0.70 - pelota1.getHeight());
		add (barra1, 0, getHeight()*0.80);

		//Barra lateral
		GRect lateral=new GRect(3, getHeight());
		lateral.setFilled(true);
		add (lateral,getWidth()-espacioMenu -lateral.getWidth()-pelota1.getWidth(), 0);

		//Atributos de la pantalla de Game Over.
		gameover.setFilled(true);
		go.setLabel("GAME OVER");
		go.setColor(Color.white);
		go.setFont(new Font("Verdana", Font.BOLD, 46));




	}

	public void run(){
		//dibujaNivel01();
		//dibujaNivel02();
		//Llamamos al método DIBUJA en la clase Marcador. Esto nos mete los DOS add en su orden correcto.
		marcador.dibuja(this);
		partida.dibuja2(this); 
		/*En esta parte está todo el código de ejecución del juego. Nos encontramos con un bucle while que se 
		 * reproduce en tanto en cuanto las vidas permanezcan por debajo de cero. Dentro de ese while
		 * hay dos bucles IF en los que se dibuja el nivel, el comando para que el bucle while inicie y
		 * dentro de cada IF hay un bucle while en los cuales están las funciones de movimiento de pelota
		 * y de los bonuses.
		 * (Los valores que nos encontramos en las variables son de testeo)*/
		while (partida.vidas>0 && marcador.puntuacion < 3){			
			if(marcador.puntuacion < 3){
				dibujaNivel01();
				waitForClick();
				while (partida.vidas>0 && marcador.puntuacion < 3){
					pelota1.muevete(this);
					/*if(chequeaContacto.segundaPelota == true){
					   pelota2.muevete(this);
					}*/
					bonus.cae(this);
					pause(4);
				}
			}
			if(marcador.puntuacion >= 3){
				dibujaNivel02();
				//Al saltar al nivel dos reiniciamos la posición de la pelota.
				remove(pelota1);
				add (pelota1, 0, getHeight()*0.70 - pelota1.getHeight());
				pelota1.yVelocidad*=-1;
				waitForClick();
				while (partida.vidas>0 && marcador.puntuacion >= 3){
					pelota1.muevete(this);
					bonus.cae(this);
					pause(4);
				}
			}
			//Cuando las vidas se acaban nos salta una pantalla de Game Over.
			if(partida.vidas == 0){
				add(gameover, 100, 200);
				add(go, 150, 270);
			}
		}

	}

	
	public void mouseMoved(MouseEvent evento){
		//getWidth es el ancho de la pantalla.
		//getX es la X donde se encuentra el ratón. Es la X del evento, lo que produce que la barra siga al ratón.
		barra1.mueveBarra(evento.getX(),getWidth(),this);
	}
  //Diseño del nivel 1.
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
   //Diseño del nivel 2.
	private void dibujaNivel02(){
		for(int j=0; j<9; j++){
			for(int i=0; i<14; i++){
				Ladrillo miLadrillo = new Ladrillo(getWidth()/2 -(numLadrillos*anchoLadrillo)/2 + i*anchoLadrillo-espacioMenu/2, j*altoLadrillo, anchoLadrillo,altoLadrillo, Color.pink);
				add(miLadrillo);
				pause(7);
			}
		}
	}

	//Unidad de testeo de código.
	public void arrancaJuego(){

		while (partida.vidas>0){
			/*pelota1.muevete(this);
		    bonus.cae(this);
			pause(4);*/
			if(marcador.puntuacion < 3){
				dibujaNivel01();
				while (partida.vidas>0 && marcador.puntuacion < 8){
					pelota1.muevete(this);
					bonus.cae(this);
					pause(4);

					/*if(partida.vidas !=  tempVida){
						 System.out.println("Ha bajado una vida: ");
						 break;
					}*/

				}
				if(marcador.puntuacion >= 3){
					dibujaNivel02();
					while (partida.vidas>0 && marcador.puntuacion > 4){
						pelota1.muevete(this);
						bonus.cae(this);
						pause(4);

						/*if(partida.vidas !=  tempVida){
							 System.out.println("Ha bajado una vida: ");
							 break;
						}*/

					}
				}

			}

			/*if(partida.vidas !=  tempVida){
				 System.out.println("Ha bajado una vida: ");
				 break;
			}*/
			if(partida.vidas == 0){
				add(gameover, 100, 200);
				add(go, 150, 270);
			}
		}

	}
}






