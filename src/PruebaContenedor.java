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
        try {
            System.out.print("Pruebas de funcionamiento");
            crearArraysDeDatos();
            insertarElementos();
            extraerElementos();
            busquedaExitosa();
            busquedaInfructuosa();
        }catch(Exception E){
            System.out.println("No se encuentra el fichero");
        }
    }

    private static void crearArraysDeDatos() {
        ArrayDat=new int[100000];
        ArrayNoDat = new int[20000];
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
                calcularTiempo((int)(System.currentTimeMillis() - TiempoInicial) / 10);
            }
            i++;
        }
    }

    private static void calcularTiempo(int tiempo) {
        TiempoTotal = tiempo;
        NElementos = 0;
        tiempoPromedio();
        TiempoInicial = System.currentTimeMillis();
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
                calcularTiempo((int) ((System.currentTimeMillis() - TiempoInicial) /10));
            }
            i++;
        }
    }

    private static void busquedaExitosa() {
        initializedParameters();
        System.out.print("Tiempos de la búsqueda exitosa:     ");
        editarFicheroResultado("\nMétodo Búsqueda Exitosa, resultados de cada 1000 búsquedas exitosas:\n");
        while(i<Vector.tamaño()){
            Vector.insertar(ArrayDat[i]);
            NElementos++;
            if(NElementos==10000){
                int j=0;
                NElementos=0;
                while(j<Vector.cardinal()){
                    Vector.buscar(ArrayDat[j]);
                    NElementos++;
                    if(NElementos==Vector.cardinal()-1){
                        calcularTiempo((int)(System.currentTimeMillis()-TiempoInicial)/(Vector.cardinal()/1000));
                    }
                    j++;
                }
            }
            i++;
        }
    }

    private static void busquedaInfructuosa() {
        Vector.vaciar();
        initializedParameters();
        System.out.print("Tiempos de la búsqueda infructuosa: ");
        editarFicheroResultado("\nMétodo Búsqueda Infructuosa, resultados de cada 1000 búsquedas infructuosas:\n");
        while(i<Vector.tamaño()){
            Vector.insertar(ArrayDat[i]);
            NElementos++;
            if(NElementos==10000){
                int j=0;
                NElementos=0;
                TiempoInicial = System.currentTimeMillis();
                while(j<ArrayNoDat.length){
                    Vector.buscar(ArrayNoDat[j]);
                    NElementos++;
                    if(NElementos==ArrayNoDat.length-1){
                        calcularTiempo((int)(System.currentTimeMillis()-TiempoInicial)/(ArrayNoDat.length/1000));
                    }
                    j++;
                }
            }
            i++;
        }
    }
}