import java.io.*;

public class PruebaContenedor {
    private static int[] ArrayDat;
    private static int[] ArrayNoDat;
    private static File FicheroDatos;
    private static File Fichero_NoDatos;
    private static RandomAccessFile FicheroDat;
    private static RandomAccessFile FicheroNoDat;
    private static long TiempoInicial;
    private static int NElementos;
    private static int i;
    private static FileWriter FicheroFinal;
    private static PrintWriter WriteFicheroResultado;
    private static ContenedorDeEnteros Vector;
    private static int TiempoTotal;

    public static void main(String[] args) {
        System.out.print("Pruebas de funcionamiento");
        crearArraysDeDatos();
        insertarElementos();
        extraerElementos();
        try {
            /***********************************************
             ************** BÚSQUEDA EXITOSA ****************
             ***********************************************/
            System.out.println();
            // Inicializo el comienzo del recorrido
            i=0;
            // Inicializo el número de elementos a 0
            NElementos=0;
            // Calculo el tiempo inicial en milisegundos
            TiempoInicial = System.currentTimeMillis();
            System.out.print("Tiempos de la búsqueda exitosa:     ");
            WriteFicheroResultado.println("");
            WriteFicheroResultado.println("Método Búsqueda Exitosa, resultados de cada 1000 búsquedas exitosas:");
            // Comenzamos a extraer los elementos
            while(i<Vector.tamaño()){
                // Inserto los elementos del array de datos en el vector
                Vector.insertar(ArrayDat[i]);
                // Llevo la cuenta del número de elementos
                NElementos++;
                /* Si he insertado los 10.000 elementos, buscaré los elementos
                 * insertados para luego calcular su tiempo promedio*/
                if(NElementos==10000){
                    // Incializo el comienzo del recorrido
                    int j=0;
                    // Inicializo el número de elementos
                    NElementos=0;
                    // Recorre todos los elementos del vector
                    while(j<Vector.cardinal()){
                        // Buscamos los elementos en el vector
                        Vector.buscar(ArrayDat[j]);
                        // Llevo la cuenta del número de elementos
                        NElementos++;
                        /*Compruebo que se han buscado todos los elementos para
                         * luego calcular el tiempo promedio de búsqueda*/
                        if(NElementos==Vector.cardinal()-1){
                            // Calculo el tiempo final en milisegundos
                            long TiempoFinal=System.currentTimeMillis();
                            // Calculo el tiempo promedio
                            int TiempoTotal=(int)(TiempoFinal-TiempoInicial)/(Vector.cardinal()/1000);
                            // Muestra el tiempo promedio y escribe en el fichero resultado el tiempo promedio
                            if(TiempoTotal<10){
                                System.out.print(TiempoTotal+"   ");
                                WriteFicheroResultado.print(TiempoTotal+"  ");
                            }else{
                                if(TiempoTotal<100){
                                    System.out.print(TiempoTotal+"  ");
                                    WriteFicheroResultado.print(TiempoTotal+" ");
                                }else{
                                    System.out.print(TiempoTotal+" ");
                                    WriteFicheroResultado.print(TiempoTotal+" ");
                                }
                            }
                            // Calculo el tiempo inicial en milisegundos
                            TiempoInicial=System.currentTimeMillis();
                            // Inicializo el número de elementos
                            NElementos=0;
                        }
                        // Incrementa el índice
                        j++;
                    }
                }
                // Incremento el índice
                i++;
            }
            /***********************************************
             ************ BÚSQUEDA INFRUCTUOSA **************
             ***********************************************/
            Vector.vaciar();
            System.out.println();
            // Inicializo el comienzo del recorrido
            i=0;
            // Inicializo el número de elementos a 0
            NElementos=0;
            System.out.print("Tiempos de la búsqueda infructuosa: ");
            WriteFicheroResultado.println("");
            WriteFicheroResultado.println("Método Búsqueda Infructuosa, resultados de cada 1000 búsquedas infructuosas:");
            // Comenzamos a extraer los elementos
            while(i<Vector.tamaño()){
                // Inserto los elementos del array de datos en el vector
                Vector.insertar(ArrayDat[i]);
                // Llevo la cuenta del número de elementos
                NElementos++;
                /*Si se han insertado los 10.000 elementos, buscaré los elementos
                 * del fichero datos_no.dat para luego calcular el promedio*/
                if(NElementos==10000){
                    // Inicializo el comienzo del recorrido
                    int j=0;
                    // Inicializo el número de elementos a 0
                    NElementos=0;
                    // Calculo el tiempo inicial en milisegundos
                    TiempoInicial = System.currentTimeMillis();
                    // Recorremos todos los elementos del vector
                    while(j<ArrayNoDat.length){
                        // Busco los elementos del array de no datos en el vector
                        Vector.buscar(ArrayNoDat[j]);
                        // Llevo la cuenta del número de elementos
                        NElementos++;
                        /*Compruebo que se han buscado todos los elementos del arraynodat
                         * para luego calcular el tiempo promedio de búsqueda*/
                        if(NElementos==ArrayNoDat.length-1){
                            // Calculo el tiempo final en milisegundos
                            long TiempoFinal=System.currentTimeMillis();
                            // Calculo el tiempo promedio
                            int TiempoTotal=(int)(TiempoFinal-TiempoInicial)/(ArrayNoDat.length/1000);
                            // Muestra el tiempo promedio y escribe en el fichero resultado el tiempo promedio
                            if(TiempoTotal<10){
                                System.out.print(TiempoTotal+"   ");
                                WriteFicheroResultado.print(TiempoTotal+"  ");
                            }else{
                                if(TiempoTotal<100){
                                    System.out.print(TiempoTotal+"  ");
                                    WriteFicheroResultado.print(TiempoTotal+" ");
                                }else{
                                    System.out.print(TiempoTotal+" ");
                                    WriteFicheroResultado.print(TiempoTotal+" ");
                                }
                            }
                            // Calculo el tiempo inicial en milisegundos
                            TiempoInicial=System.currentTimeMillis();
                            // Inicializo el número de elementos
                            NElementos=0;
                        }
                        // Incrementa el índice
                        j++;
                    }
                }
                // Incremento el índice
                i++;
            }
        }catch(Exception E){
            System.out.println("No se encuentra el fichero");
        }
    }

    private static void crearArraysDeDatos() {
        ArrayDat=new int[100000];
        ArrayNoDat=new int[20000];
        try{
            crearFicheros();
            tipoAcceso();

            rellenarArray(ArrayDat, FicheroDat);
            rellenarArray(ArrayNoDat, FicheroNoDat);

            cerrarFicheros();

        }catch(Exception E){
            System.out.println("No se encuentra el fichero");
        }
    }

    private static void crearFicheros() throws IOException {
        FicheroDatos = new File("datos.dat");
        Fichero_NoDatos = new File("datos_no.dat");
        FicheroFinal=new FileWriter("Resultado.txt");
    }

    private static void tipoAcceso() throws FileNotFoundException {
        FicheroDat = new RandomAccessFile(FicheroDatos, "r");
        FicheroNoDat = new RandomAccessFile(Fichero_NoDatos, "r");
    }

    private static void rellenarArray(int[] array, RandomAccessFile file) throws Exception {
        for (int i = 0; i < array.length; i++) {
            array[i] = file.readInt();
        }
    }

    private static void cerrarFicheros() throws IOException {
        FicheroDat.close();
        FicheroNoDat.close();
    }

    private static void insertarElementos() {
        crearFicheroResultado();
        initializedParameters();
        crearContenedor();
        System.out.print("Tiempos de inserción:               ");
        editarFicheroResultado("Método insertar, resultados de cada 1000 inserciones:\n");
        while (i < Vector.tamaño()) {
            Vector.insertar(ArrayDat[i]);
            NElementos++;
            if (NElementos == 10000) {
                long tiempoFinal = System.currentTimeMillis();
                TiempoTotal = (int) (tiempoFinal - TiempoInicial) / 10;
                NElementos = 0;
                tiempoPromedio();
                TiempoInicial = System.currentTimeMillis();
            }
            i++;
        }
    }

    private static void initializedParameters() {
        System.out.println();
        i=0;
        NElementos=0;
        TiempoInicial = System.currentTimeMillis();
    }

    private static void crearContenedor() {
        Vector = new ContenedorDeEnteros(100000);
    }

    private static void crearFicheroResultado() {
        WriteFicheroResultado = new PrintWriter(FicheroFinal);
        editarFicheroResultado("Resultados de la prueba:\n");
    }

    private static void editarFicheroResultado(String result) {
        WriteFicheroResultado.println(result);
    }

    private static void tiempoPromedio() {
        if (TiempoTotal < 10) {
            System.out.print(TiempoTotal + "   ");
            editarFicheroResultado(TiempoTotal + "   ");
        } else {
            if (TiempoTotal < 100) {
                System.out.print(TiempoTotal + "  ");
                editarFicheroResultado(TiempoTotal + "  ");
            } else {
                System.out.print(TiempoTotal + " ");
                editarFicheroResultado(TiempoTotal + " ");
            }
        }
    }

    private static void extraerElementos() {
        initializedParameters();
        System.out.print("Tiempos de extracción:              ");
        editarFicheroResultado("\n");
        editarFicheroResultado("Método extraer, resultados de cada 1000 extracciones:");
        editarFicheroResultado("\n");
        while (i < Vector.tamaño()) {
            Vector.extraer(ArrayDat[i]);
            NElementos++;
            if (NElementos == 10000) {
                long TiempoFinal = System.currentTimeMillis();
                TiempoTotal = (int) (TiempoFinal - TiempoInicial) / 10;
                tiempoPromedio();
                NElementos=0;
                TiempoInicial = System.currentTimeMillis();
            }
            i++;
        }
    }
}