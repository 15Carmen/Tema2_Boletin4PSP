import java.util.concurrent.Semaphore;

public class ColaCarniceria implements Runnable {
    /*
    Programa un sistema multihilo que gestione la cola de una carnicería mediante semáforos. La carnicería tiene 4
    empleados, por lo que puede haber 4 clientes pidiendo a la vez. Una vez estén ocupados los 4 el resto de clientes
    deben esperar.

    El método main deberá lanzar 10 hilos. Establece un nombre para cada hilo. Una vez que ese hilo esté siendo atendido
    en la carnicería, debe mostrar el mensaje:

    "El cliente x está siendo atendido".

    Tras una espera de 10 segundos (porque el carnicero tarda un tiempo en prepararle la carne), se debe mostrar el mensaje:

    "El cliente x ha terminado en la carnicería".
     */


    public static Semaphore semaforoCarniceria = new Semaphore(4);

    @Override
    public void run() {
        try {
            semaforoCarniceria.acquire();
            System.out.println("El "+ Thread.currentThread().getName() +" esta siendo atendido");
            Thread.sleep(10000);
            System.out.println("El "+ Thread.currentThread().getName() +" ha terminado en la carniceria");
            semaforoCarniceria.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ColaCarniceria cc = new ColaCarniceria();
        for (int i = 0; i < 10; i++) {
            Thread hilo = new Thread(cc);
            hilo.setName("cliente "+i);
            hilo.start();
        }

    }

}
