/*
 * Autor; Marta M�rquez Olalla
 * 
 * Para el juego del Arkanoid hemos creado una clase nueva que va a englobar
 * el c�digo relacionado con la bola. Todo lo que es ese GOval, con sus dimensiones,
 * velocidad, etc.
 * Para hacer eso extendemos la CLASE, es decir, extendemos ese GOval.
 * */

package codigo;

import java.awt.Color;

import acm.graphics.GObject;
import acm.graphics.GOval;

public class Pelota extends GOval{
	
	double xVelocidad = 1; //velocidad de la bola en el eje x.
	double yVelocidad = -1; //velocidad en el eje y. Menos 3 porque es ascendente.
	
	/**
	 * Este es el constructor b�sico que es id�ntido al de la clase GOval.
	 * @param ancho
	 * @param alto
	 */
	/*El constructor de una clase es un m�todo especial que tienen todas las clases,
	 * es el m�todo que guarda la memoria, la analiza, etc. Es el que se encarga
	 * de decir; voy a crear el objeto y a inicializarlo. Darle dimensiones, color,
	 * etc.
	 * Para poner un constructor definimos public, el nombre de la clase (Pelota),
	 * y dentro ponemos super();. Este m�todo no devuelve nada.
	 * 
	 * La clase GOval tiene dos constructores distintos, uno que define unas medidas
	 * x e y, y un segundo que adem�s crea el GOval con unas medidas en unas 
	 * coordenadas concretas.
	 * 
	 * Nosotros extendemos la clase GOval y definimos un constructor, que llama a la
	 * clase madre, al constructor de la clase principal. Sabemos que GOval necesita al menos
	 * dos par�metros, as� que los definimos en su m�todo constructor.
	 * */
	public Pelota(double _ancho, double _alto){
		super(_ancho,_alto);
	}
	/**
	 * Este es el constructor dabuti que permite pasar el color como par�metro.
	 * @param ancho indica el ancho y el alto de la bola.
	 * @param _color Idica el color.
	 */
	/*Creamos un segundo constructor con un tercer par�metro que es color. Podemos tener todos
	 * los par�metros que queramos siempre y cuando no sean el mismo n�mero y del mismo tipo.
	 * Hasta que no hemos declarado el SUPER no podemos meter los par�metros porque todav�a
	 * no existe la bola.*/
	public Pelota(double _ancho, Color _color){
		super(_ancho,_ancho);
		/*En los constructores se ponen contramedidas para proteger el c�digo. Definimos cosas
		 * que no tendr�an sentido.
		 * Cuanto m�s protegido est� el constructor de una clase m�s f�cil ser� para usar en
		 * otros sitios.*/
		if(_ancho <=0){
			setSize(1, 1);
		}
		setFillColor(_color);
		setFilled(true);
		
	}
	
	/*Podemos crear un m�todo en la bola para chequear los movimientos. En este caso si hay dos
	 * bolas declaradas en el otro programa, las dos chequear�n �nicamente esta parte del c�digo.
	 * 
	 * Le pasamos la clase Arkanoid para que el m�todo pueda saber qu� tama�o de pantalla tiene.
	 * Le pasamos el objeto Arkanoid con todo lo que contiene.*/
	public void muevete(Arkanoid _arkanoid){
		//getX hace referencia a la x del GOval.
		//this.getX() es la X de la bola. El THIS no es necesario porque estamos dentro de la misma clase.
		//this.getWidth() es el ancho de la bola.
		//_arkanoid.getWidth() es el ancho de la pantalla.
		//Chequea colisi�n con las paredes izquierda o derecha.
		if(/*this.*/getX() + getWidth() >_arkanoid.getWidth() - _arkanoid.espacioMenu || getX()<0){
			xVelocidad*=-1;
		}
		//Chequea la colisi�n con el techo.
		if(getY()<0){
			yVelocidad*=-1;
		}
		if(getY() > _arkanoid.getHeight() - getHeight()){
			yVelocidad*=-1;
		}	
		/*Vamos a crear un m�todo gen�rico que sirva para chequear los choques de la bola contra
		 * ladrillos y contra el cursor. Le vamos a pasar un par�metro que van a ser las
		 * coordenadas X o Y donde se genera el choque. Es un m�todo private booleano, porque
		 * devuelve verdadero o no.
		 * Lo que hace es ir chequeando cada uno de los ifs. Si devuelve true (noHaChocado), chequea
		 * la segunda, etc. As� hasta que chequ�e una colisi�n (noHaChocado=false).*/
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

	/*Queremos el m�todo lo m�s gen�rico posible. Le preguntamos si hay algo, entonces rebota. No
	 * hay nada, sigue. GObject en ACM es la madre de todos los objetos gr�ficos. Un GRect es hijo
	 * de GObject. Un GOval es hijo de un GObject. Un ladrillo, que es hijo de un GRect, su abuelo
	 * era un GObject.
	 * Al m�todo le preguntamos si hay un elemento en una coordenada o no, y nos devuelve un true o
	 * un false. Luego le preguntamos el tipo de ese elemento, y nos dice si es true o false. Es barra? No.
	 * Es ladrillo? S�. Lo que queremos es que su comportamiento var�e seg�n el tipo de objeto que sea.*/
	//Los par�metros son las coordenadas de la esquina X y la esquina Y de la bola.
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
			//S� la y del ladrillo (auxiliar) es igual a la posici�n Y de la bola, o la y del ladrillo
			//m�s la altura del ladrillo es igual a la posici�n y de la bola.
			if(auxiliar.getY() ==  posY || auxiliar.getY()+auxiliar.getHeight() == posY){
				yVelocidad*=-1;
			}else if(auxiliar.getX() == posX || auxiliar.getX() + auxiliar.getWidth() == posX){
				xVelocidad*=-1;
			}
			_arkanoid.remove(auxiliar);
			noHaChocado=false;
			//Chequeamos la presencia de la barra.
		}else if(auxiliar instanceof Barra){
			yVelocidad*=-1;
			//Cambiamos el valor del booleano.
			noHaChocado=false;
		}
		return noHaChocado;
	}

}