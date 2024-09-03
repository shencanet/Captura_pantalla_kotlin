BONUS: Diferencia entre Hilo y Corrutina

las corrutinas (coroutines) no son hilos (threads) en el sentido tradicional. Aunque ambos conceptos están relacionados con la concurrencia y la ejecución de tareas en paralelo, tienen diferencias fundamentales.

Hilos (Threads):

    Los hilos son unidades básicas de ejecución en un programa. Un programa puede tener múltiples hilos que se ejecutan simultáneamente.

    Cada hilo tiene su propia pila de ejecución y comparte recursos como memoria con otros hilos del mismo proceso.

    Los hilos a menudo se gestionan a nivel del sistema operativo y están sujetos a la planificación del sistema operativo.

Corrutinas (Coroutines):

    Las corrutinas son una construcción de programación que permite la concurrencia sin necesidad de utilizar hilos directamente.

    Las corrutinas son cooperativas y no están ligadas directamente a hilos del sistema operativo. Múltiples corrutinas pueden ejecutarse en un solo hilo.

    A diferencia de los hilos, las corrutinas no requieren cambios de contexto costosos a nivel de sistema operativo, lo que las hace más eficientes en términos de recursos.

Ventajas de las Corutinas:

    Ligereza: Las corrutinas son más ligeras que los hilos, ya que no hay necesidad de asignar un hilo del sistema operativo para cada una.

    Facilidad de Uso: Las corrutinas ofrecen un modelo de programación más sencillo y estructurado para trabajar con tareas asíncronas y concurrentes.

    Escalabilidad: Puedes tener muchas corrutinas ejecutándose en un número limitado de hilos, lo que facilita la gestión de la concurrencia.

Es importante destacar que las corrutinas en Kotlin son manejadas por la biblioteca de Kotlin Coroutines y pueden ejecutarse en varios contextos, incluyendo hilos, pero no están vinculadas directamente a ellos. Las corrutinas permiten una programación asíncrona y concurrente más eficiente y expresiva en comparación con los hilos tradicionales.


Ejemplo de Corutina:

    kotlin

    import kotlinx.coroutines.*
     
    fun main() {
        println("Inicio del programa")
     
        // Lanzamos una corrutina 
        GlobalScope.launch {
            delay(1000) // Simulamos una operación asíncrona que toma 1 segundo
            println("Corutina: Operación completada después de 1 segundo")
        }
     
        println("Fin del programa")
        
        // Aseguramos que la corrutina tenga tiempo de ejecutarse antes de que el programa termine
        Thread.sleep(2000)
    }

En este ejemplo, utilizamos GlobalScope.launch para lanzar una corrutina que simula una operación asíncrona con delay(1000).

Ejemplo de Hilo:

    kotlin

    import kotlin.concurrent.thread
     
    fun main() {
        println("Inicio del programa")
     
        // Creamos un hilo para simular una operación asíncrona
        val miHilo = thread {
            Thread.sleep(1000) // Simulamos una operación asíncrona que toma 1 segundo
            println("Hilo: Operación completada después de 1 segundo")
        }
     
        println("Fin del programa")
     
        // Esperamos a que el hilo termine antes de que el programa finalice
        miHilo.join()
    }

En este caso, creamos un hilo utilizando thread y ejecutamos una operación asíncrona con Thread.sleep(1000).

Comparación:

    En el ejemplo de corrutina , utilizamos GlobalScope.launch para lanzar la corrutina . La función delay suspende la corrutina sin bloquear el hilo, permitiendo que otros trabajos se ejecuten.

    En el ejemplo de hilo, creamos un hilo utilizando thread y usamos Thread.sleep para simular una operación asíncrona. Sin embargo, esta operación bloquea el hilo actual.

En resumen, las corrutinas proporcionan un modelo más liviano y flexible para trabajar con concurrencia y operaciones asíncronas en comparación con los hilos tradicionales. Las corrutinas permiten realizar tareas asíncronas sin la necesidad de crear y gestionar explícitamente hilos del sistema operativo.