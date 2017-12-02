import java.io.*;

public class PruebaContenedor {
    private static int[] ArrayDat;
    private static int[] ArrayNoDat;
    private static File FicheroDatos;
    private static File Fichero_NoDatos;
    private static RandomAccessFile FicheroDat;
    private static RandomAccessFile FicheroNoDat;

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

    private static void crearFicheros() {
        FicheroDatos = new File("datos.dat");
        Fichero_NoDatos = new File("datos_no.dat");
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

    public static void main(String[] args) {
        /*ContenedorDeEnteros a = new ContenedorDeEnteros(10);
        int[] v;
        System.out.println("El contenedor a tiene "+a.cardinal()+" elementos.");
        for(int i=0; i<10; i++){
            a.insertar(i);
            a.buscar(i);
        }
        System.out.println("El contenedor a tiene "+a.cardinal()+" elementos.");
        v = a.elementos();
        System.out.print("Vector v = [");
        for (int i = 0; i <a.cardinal(); i++) {
            System.out.print(v[i]);
        }
        System.out.println("]");
        a.vaciar();
        for(int i=0; i<100; i++){
            a.insertar(i);
            a.extraer(i);
        }*/

        crearArraysDeDatos();

        // Fichero que almacenará los resultados
        PrintWriter WriteFicheroResultado;
        try{
            FileWriter FicheroFinal=new FileWriter("Resultado.txt");
            WriteFicheroResultado=new PrintWriter(FicheroFinal);
            WriteFicheroResultado.println("Resultados de la prueba:");
            // Creo un contenedor de enteros
            ContenedorDeEnteros Vector = new ContenedorDeEnteros(100000);
            /***********************************************
             ****************** INSERTAR ********************
             ***********************************************/
            // Inicializo el comienzo del recorrido
            int i=0;
            // Inicializo el número de elementos a 0
            int NElementos=0;
            // Calculo el tiempo inicial en milisegundos
            long TiempoInicial = System.currentTimeMillis();
            System.out.print("Tiempos de inserción:               ");
            WriteFicheroResultado.println("Método insertar, resultados de cada 1000 inserciones:");
            // Comenzamos a insertar los elementos
            while(i<Vector.tamaño()){
                // Inserta los 10.000 elementos en el contenedor
                Vector.insertar(ArrayDat[i]);
                // Llevar la cuenta del número de elementos
                NElementos=NElementos+1;
			   /* Si he insertado los 10.000 elementos, calculo el tiempo de
			   inserción, pongo el contador a cero y obtengo el tiempo promedio*/
                if(NElementos==10000){
                    // Calculo el tiempo final en milisegundos
                    long TiempoFinal = System.currentTimeMillis();
                    // Calculo el tiempo promedio
                    int TiempoTotal=(int)(TiempoFinal-TiempoInicial)/10;
                    // Inicializo el número de elementos a 0
                    NElementos=0;
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
                    TiempoInicial = System.currentTimeMillis();
                }
                // Incremento el índice
                i++;
            }
            /***********************************************
             ******************* EXTRAER ********************
             ***********************************************/
            System.out.println();
            // Inicializo el comienzo del recorrido
            i=0;
            // Inicializo el número de elementos a 0
            NElementos=0;
            // Calculo el tiempo inicial en milisegundos
            TiempoInicial = System.currentTimeMillis();
            System.out.print("Tiempos de extracción:              ");
            WriteFicheroResultado.println("");
            WriteFicheroResultado.println("Método extraer, resultados de cada 1000 extracciones:");
            // Comenzamos a extraer los elementos
            while(i<Vector.tamaño()){
                // Extrae los 10.000 elementos en el contenedor
                Vector.extraer(ArrayDat[i]);
                // Llevo la cuenta del número de elementos
                NElementos=NElementos+1;
				/* Si he extraido los 10.000 elementos, calculo el tiempo de
				extracción, pongo el contador a cero y obtengo el tiempo promedio*/
                if(NElementos==10000){
                    // Calculo el tiempo final en milisegundos
                    long TiempoFinal = System.currentTimeMillis();
                    // Calculo el tiempo promedio
                    int TiempoTotal=(int)(TiempoFinal-TiempoInicial)/10;
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
                    // Inicializo el número de elementos a 0
                    NElementos=0;
                    // Calculo el tiempo inicial en milisegundos
                    TiempoInicial = System.currentTimeMillis();
                }
                // Incremento el índice
                i++;
            }
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
            /*for (int j = 0; j < Vector.elementos().length; j++) {
                System.out.println(Vector.elementos()[j]);
            }
            System.out.println();*/
        }catch(Exception E){
            System.out.println("No se encuentra el fichero");
        }
    }
}