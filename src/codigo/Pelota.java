/*
 * Autor; Marta Márquez Olalla
 * 
 * Para el juego del Arkanoid hemos creado una clase nueva que va a englobar
 * el código relacionado con la bola. Todo lo que es ese GOval, con sus dimensiones,
 * velocidad, etc.
 * Para hacer eso extendemos la CLASE, es decir, extendemos ese GOval.
 * */

package codigo;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Random;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

public class Pelota extends GOval{

	double xVelocidad = 1; //velocidad de la bola en el eje x.
	double yVelocidad = -1; //velocidad en el eje y. Menos 3 porque es ascendente.	

	RandomGenerator aleatorio = new RandomGenerator();

	/**
	 * Este es el constructor básico que es idéntido al de la clase GOval.
	 * @param ancho
	 * @param alto
	 */
	/*El constructor de una clase es un método especial que tienen todas las clases,
	 * es el método que guarda la memoria, la analiza, etc. Es el que se encarga
	 * de decir; voy a crear el objeto y a inicializarlo. Darle dimensiones, color,
	 * etc.
	 * Para poner un constructor definimos public, el nombre de la clase (Pelota),
	 * y dentro ponemos super();. Este método no devuelve nada.
	 * 
	 * La clase GOval tiene dos constructores distintos, uno que define unas medidas
	 * x e y, y un segundo que además crea el GOval con unas medidas en unas 
	 * coordenadas concretas.
	 * 
	 * Nosotros extendemos la clase GOval y definimos un constructor, que llama a la
	 * clase madre, al constructor de la clase principal. Sabemos que GOval necesita al menos
	 * dos parámetros, así que los definimos en su método constructor.
	 * */
	public Pelota(double _ancho, double _alto){
		super(_ancho,_alto);
	}
	/**
	 * Este es el constructor dabuti que permite pasar el color como parámetro.
	 * @param ancho indica el ancho y el alto de la bola.
	 * @param _color Idica el color.
	 */
	/*Creamos un segundo constructor con un tercer parámetro que es color. Podemos tener todos
	 * los parámetros que queramos siempre y cuando no sean el mismo número y del mismo tipo.
	 * Hasta que no hemos declarado el SUPER no podemos meter los parámetros porque todavía
	 * no existe la bola.*/
	public Pelota(double _ancho, Color _color){
		super(_ancho,_ancho);
		/*En los constructores se ponen contramedidas para proteger el código. Definimos cosas
		 * que no tendrían sentido.
		 * Cuanto más protegido esté el constructor de una clase más fácil será para usar en
		 * otros sitios.*/
		if(_ancho <=0){
			setSize(1, 1);
		}
		setFillColor(_color);
		setFilled(true);

	}

	/*Podemos crear un método en la bola para chequear los movimientos. En este caso si hay dos
	 * bolas declaradas en el otro programa, las dos chequearán únicamente esta parte del código.
	 * 
	 * Le pasamos la clase Arkanoid para que el método pueda saber qué tamaño de pantalla tiene.
	 * Le pasamos el objeto Arkanoid con todo lo que contiene.*/
	public void muevete(Arkanoid _arkanoid){
		//getX hace referencia a la x del GOval.
		//this.getX() es la X de la bola. El THIS no es necesario porque estamos dentro de la misma clase.
		//this.getWidth() es el ancho de la bola.
		//_arkanoid.getWidth() es el ancho de la pantalla.
		//Chequea colisión con las paredes izquierda o derecha.
		if(/*this.*/getX() + getWidth() >_arkanoid.getWidth() - _arkanoid.espacioMenu || getX()<0){
			xVelocidad*=-1;
		}
		//Chequea la colisión con el techo.
		if(getY()<0){
			yVelocidad*=-1;
		}
		//Chequea cuando la pelota cae por la parte de abajo de la pantalla y descuenta una vida cuando eso ocurre.
		//También resetea a la pelota en su posición inicial y le cambia las coordenadas de movimiento en el eje Y.
		if(getY() > _arkanoid.getHeight() - getHeight()){
			_arkanoid.pelota1.setLocation(0,_arkanoid.getHeight()*0.70 - _arkanoid.pelota1.getHeight());
			yVelocidad*=-1;
			_arkanoid.partida.actualizaVidas(1);
		}	
		/*Vamos a crear un método genérico que sirva para chequear los choques de la bola contra
		 * ladrillos y contra el cursor. Le vamos a pasar un parámetro que van a ser las
		 * coordenadas X o Y donde se genera el choque. Es un método private booleano, porque
		 * devuelve verdadero o no.
		 * Lo que hace es ir chequeando cada uno de los ifs. Si devuelve true (noHaChocado), chequea
		 * la segunda, etc. Así hasta que chequée una colisión (noHaChocado=false).*/
		if(chequeaColision(getX(), getY(), _arkanoid)){//Chequeo esquina superior izquierda.
			if(chequeaColision(getX()+getWidth(), getY(), _arkanoid)){//Chequeo esquina superior derecha.
				if(chequeaColision(getX(), getY()+getHeight(), _arkanoid)){//Chequeo esquina inferior izquierda.
					if(chequeaColision(getX()+getWidth(), getY()+getHeight(), _arkanoid)){//Chequeo esquina inferior derecha.
					}
				}				
			}
		}
		move(xVelocidad,yVelocidad);
	}


	/*Queremos el método lo más genérico posible. Le preguntamos si hay algo, entonces rebota. No
	 * hay nada, sigue. GObject en ACM es la madre de todos los objetos gráficos. Un GRect es hijo
	 * de GObject. Un GOval es hijo de un GObject. Un ladrillo, que es hijo de un GRect, su abuelo
	 * era un GObject.
	 * Al método le preguntamos si hay un elemento en una coordenada o no, y nos devuelve un true o
	 * un false. Luego le preguntamos el tipo de ese elemento, y nos dice si es true o false. Es barra? No.
	 * Es ladrillo? Sí. Lo que queremos es que su comportamiento varíe según el tipo de objeto que sea.*/
	//Los parámetros son las coordenadas de la esquina X y la esquina Y de la bola.
	private boolean chequeaColision(double posX, double posY, Arkanoid _arkanoid){
		boolean noHaChocado = true;
		//Declaramos el objeto.
		GObject auxiliar;
		//getElementAt nos apunta a un elemento concreto, en este caso a los ladrillos.
		auxiliar = _arkanoid.getElementAt(posX,posY);
		//instanceof devuelve verdadero si una clase es instancia de la otra.
		//Auxiliar es instancia de ladrillo? Devuelve verdadero.
		//Auxiliar es instancia de barra? Devuelve falso.
		if(auxiliar instanceof Ladrillo){
			//Sí la y del ladrillo (auxiliar) es igual a la posición Y de la bola, o la y del ladrillo
			//más la altura del ladrillo es igual a la posición y de la bola.

			if(auxiliar.getY() <= posY && auxiliar.getX() <= posX || 
					auxiliar.getY()+auxiliar.getHeight() >= posY && auxiliar.getX()+auxiliar.getWidth() <= posX){
				yVelocidad*=-1;
				xVelocidad*=-1;
				//Cuando se rompe un ladrillo cae un bonus.
				if(generaRandom(1,6) == 4){
					_arkanoid.add(_arkanoid.bonus, auxiliar.getX(), auxiliar.getY());
				}
			}
			//Cuando entra en contacto con un ladrillo éste desaparece.
			_arkanoid.remove(auxiliar);				
			//esta parte hace la operación que suma los puntos en el marcador.
			_arkanoid.marcador.actualizaMarcador(1);

			noHaChocado=false;

			//Chequeamos la presencia de la barra.
		}else if(auxiliar instanceof Barra){
			//Vamos a modificar el rebote de la bola con el cursor para que no sea siempre el mismo.
			//Vamos a dividir el cursor en cinco partes.
			//Calculamos la posición X del punto central de la bola.
			double centroBola=getX() + getWidth()/2;

			if(centroBola<auxiliar.getX()+auxiliar.getWidth()/5 ||
					centroBola>auxiliar.getX()+2*auxiliar.getWidth()/5){
				yVelocidad=-0.3;
				xVelocidad=-1.5;
			}else if(centroBola>auxiliar.getX()+auxiliar.getWidth()/5 && centroBola<auxiliar.getX()+auxiliar.getWidth()/3 ||
					centroBola<auxiliar.getX()+2*auxiliar.getWidth()/5 && centroBola>auxiliar.getX()+2*auxiliar.getWidth()/3){
				yVelocidad=-0.6;
				xVelocidad=-1.5;
			}else{
				yVelocidad=-1;
			}
			//Cambiamos el valor del booleano.
			noHaChocado=false;
			//Chequea lo que ocurre cuando la bola choca contra una instancia de bonus.
		}else if(auxiliar instanceof Bonus){
			if(auxiliar.getX() <= posX || auxiliar.getY() <= posY){
				yVelocidad*=-1;
				xVelocidad*=-1;
			}
		}

		return noHaChocado;

	}

	public int generaRandom(int low, int high){
		int rand=aleatorio.nextInt(low,high);
		return rand;
	}


}
