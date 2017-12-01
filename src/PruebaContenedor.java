import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class PruebaContenedor {
    public static void main(String[] args) {
        ContenedorDeEnteros a = new ContenedorDeEnteros(10);
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
        }
        // Creo un array de datos y otro de No datos
        int[] ArrayDat=new int[100000];
        int[] ArrayNoDat=new int[20000];
        try{
            File FicheroDatos=new File("datos.dat");
            File Fichero_NoDatos=new File("datos_no.dat");
            RandomAccessFile FicheroDat=new RandomAccessFile(FicheroDatos,"r");
            RandomAccessFile FicheroNoDat=new RandomAccessFile(Fichero_NoDatos,"r");
            // Recorremos ArrayDat para almacenar los elementos del fichero de datos
            for(int i=0;i<ArrayDat.length;i++){
                ArrayDat[i]= FicheroDat.readInt();
            }
            // Recorremos ArrayNoDat para almacenar los elementos del fichero de datos
            for(int i=0;i<ArrayNoDat.length;i++){
                ArrayNoDat[i]= FicheroNoDat.readInt();
            }
            // Cerrar los ficheros datos.dat y datos_no.dat
            FicheroDat.close();
            FicheroNoDat.close();
        }catch(Exception E){
            System.out.println("No se encuentra el fichero");
        }
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
            for (int j = 0; j < Vector.elementos().length; j++) {
                System.out.println(Vector.elementos()[j]);
            }
            System.out.println();
        }catch(Exception E){
            System.out.println("No se encuentra el fichero");
        }
    }
}