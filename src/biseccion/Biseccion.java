/**
 * Paquete biseccion.
 */
package biseccion;

/**
 * Importación del Scanner, que sirve para los valores de entrada
 */
import java.util.Scanner;

/*Nombre del archivo: asignacion03_00000207256
Nombre de alumno: Carlos Antonio Valenzuela Valdez
Matricula: 00000207256
Fecha de creación: 10/09/2020 */
/**
 * El programa bisección ayuda a encontrar las raices de una función ya
 * establecida. Al ingresar datos de entrada iniciales y finales, ademas de un
 * error que se usara para aproximarse a la respuesta. Este nos ayuda a mejorar
 * la velocidad al momento de querer encontrar raices, ya que es muy rapido y
 * facil hacerlo funcionar. Nos brinda todos los datos con los que opera para
 * dar el resultado así como las iteraciones que necesito para dar con dicha
 * respuesta.
 */

/*
Pasos a seguir en el programa.
1.- Escójanse dos valores iniciales
xi y xf de forma que tal que la
función cambie de signo en el
intervalo.
2.- Con los datos ingresados se trabajara en la proxima aproximacion
3.- Se comparara los resultados para obtener un resultado 
4.- Se calcula una nueva aproximación a la raíz.
5.- Decídase si la nueva aproximación es tan exacta como se
desea. Si es así los cálculos terminan, de otra manera
regrese al paso 3.
 */
/**
 * Clase donde se mandaran a llamar los métodos así como se registraran los
 * datos.
 *
 * @author CarlosValenzuela
 */
public class Biseccion {

    /**
     * Método main de la clase biseccion para dar ejecución al programa
     *
     * @param args Argumentos del parametro
     */
    public static void main(String[] args) {
        //Declaracion del método Scanner
        Scanner teclado = new Scanner(System.in);

        //Variables para los datos de entrada
        double valorInicial = 0.0, valorFinal = 0.0, errorAprox = 0.0;

        //Funcionalidad del programa.
        System.out.println("El programa bisección ayuda a encontrar las raices de una función ya\n"
                + "establecida. Al ingresar datos de entrada iniciales y finales, ademas de un\n"
                + "error que se usara para aproximarse a la respuesta. Este nos ayuda a mejorar\n"
                + "la velocidad al momento de querer encontrar raices, ya que es muy rapido y\n"
                + "facil hacerlo funcionar. Nos brinda todos los datos con los que opera para\n"
                + "dar el resultado así como las iteraciones que necesito para dar con dicha\n"
                + "respuesta.");

        System.out.println("\n F(x) = x³ + 3X - 1");

        //Solicitud de datos
        System.out.println("\nSolicitud de los datos de entrada");
        System.out.print("Ingrese el valor inicial: ");
        valorInicial = teclado.nextDouble();

        System.out.print("Ingrese el valor final: ");
        valorFinal = teclado.nextDouble();

        System.out.print("Ingrese el error aproximado máximo: ");
        errorAprox = teclado.nextDouble();

        //Llamaada al método de bisección
        Biseccion main = new Biseccion();
        main.biseccion(valorInicial, valorFinal, errorAprox);
    }

    /**
     * Método que hara el proceso de cambio de variables mediante el uso de otro
     * método para calcular la ordenada de Y
     *
     * @param valorInicial Valor X izquierdo
     * @param valorFinal Valor X derecho
     * @param errorAproxMax Error aproximado
     */
    public void biseccion(double valorInicial, double valorFinal, double errorAproxMax) {

        //Declaración de variables
        errorAproxMax = errorAproxMax / 100;
        int iteraciones = 1;
        double funcionXi = 0.0, funcionXf = 0.0, funcionXr = 0.0, xR = 0.0, xRNueva = 0.0, errorAprox = 0.0, multiplicacionValores = 0.0;
        double aux = 0.0, aux2 = 0.0;
        boolean continuar = true;

        System.out.println("\n\n \t\t\t\t\t Tabla de valores ");
        System.out.print("\n\nIteración     Xi\t   xF\t\txR       F(Xi)\t\tF(Xr)       F(Xi)*F(Xf)      ea");
        //Ciclo para saber iteraciones, así como para proseguir con los pasos del algoritmo
        while (continuar) {

            //Asignación de valores
            funcionXi = f(valorInicial);
            funcionXf = f(valorFinal);
            xR = (valorInicial + valorFinal) / 2;
            funcionXr = f(xR);

            //Multiplicamos funcion xI * funcion xR. Para saber con que paso seguir, según el resultado.
            multiplicacionValores = funcionXi * funcionXr;

            if (iteraciones == 1) {
                imprimirValores2(iteraciones, valorInicial, valorFinal, xR, funcionXi, funcionXr, multiplicacionValores);
                iteraciones++;
            }
            if (multiplicacionValores == 0) {
                imprimirValores(iteraciones, valorInicial, valorFinal, xR, funcionXi, funcionXr, multiplicacionValores, errorAprox);

                break;
            }
            if (multiplicacionValores < 0) {
                //Variables para guardar dato a cambiar
                aux = xR;
                aux2 = valorInicial;
            }

            if (multiplicacionValores > 0) {
                //Variables para guardar dato a cambiar
                aux = valorFinal;
                aux2 = xR;

            }

            xRNueva = (aux2 + aux) / 2;
            if (xRNueva == 0) {
                imprimirValores(iteraciones, valorInicial, valorFinal, xR, funcionXi, funcionXr, multiplicacionValores, errorAprox);

                break;

            }

            errorAprox = (Math.abs(xRNueva - xR) / xRNueva);

            //Si el error aproximado es menor al error aproximado maximo, se finaliza el programa y se despliega la tabla.
            if (Math.abs(errorAprox) < errorAproxMax) {
                imprimirValores(iteraciones, valorInicial, valorFinal, xR, funcionXi, funcionXr, multiplicacionValores, errorAprox);

                break;
            }
            imprimirValores(iteraciones, valorInicial, valorFinal, xR, funcionXi, funcionXr, multiplicacionValores, errorAprox);

            //Dar el valor que cambio a las variables.
            valorFinal = aux;
            valorInicial = aux2;
            iteraciones++;

        }

        System.out.println("\n\nResultados obtenidos: ");
        System.out.println("Número de iteraciones requeridas para encontrar raíz: " + iteraciones);
        System.out.printf("Valor de la raíz: %.6f \n", xR);
        System.out.printf("Valor de la función para la raíz: %.6f \n", funcionXr);

    }

    /**
     * Método que regresa la ordenada de Y recibiendo X como parametro
     *
     * @param x Valor al que se le sacara la ordenada
     * @return Valor de la función
     */
    public double f(double x) {
        double funcion = 0.0;

        funcion = Math.pow(x, 3) + (3 * x) - 1;

        return funcion;

    }

    /**
     * Método para imprimir la tabla de valores
     *
     * @param iteraciones Número de iteraciones necesarias para dar con el
     * resultado
     * @param vI Valor inicial
     * @param vF Valor final
     * @param xR xR
     * @param fXi Funcion de valor inicial
     * @param FxR Funcion de xR
     * @param mult Multiplicacion de las funciones xI y xR
     * @param ea Error aproximado.
     */
    public void imprimirValores(int iteraciones, double vI, double vF, double xR, double fXi, double FxR, double mult, double ea) {
        System.out.printf("\n%d     \t    %5.6f    %8.6f    %8.6f    %8.6f    %10.6f     %10.6f    %8.6f", iteraciones, vI, vF,
                xR, fXi, FxR, mult, ea);

    }

    /**
     * Método para imprimir la tabla de valores
     *
     * @param iteraciones Número de iteraciones necesarias para dar con el
     * resultado
     * @param vI Valor inicial
     * @param vF Valor final
     * @param xR xR
     * @param fXi Funcion de valor inicial
     * @param FxR Funcion de xR
     * @param mult Multiplicacion de las funciones xI y xR
     */
    public void imprimirValores2(int iteraciones, double vI, double vF, double xR, double fXi, double FxR, double mult) {
        System.out.printf("\n%d     \t    %5.6f    %8.6f    %8.6f    %8.6f    %10.6f     %10.6f    ", iteraciones, vI, vF,
                xR, fXi, FxR, mult);

    }
}
