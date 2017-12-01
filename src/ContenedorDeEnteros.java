public class ContenedorDeEnteros {
    private int[] contenedor;
    private int numElem;

    public ContenedorDeEnteros(int numElem) {
        this.contenedor = new int[numElem];
        this.numElem = 0;
    }

    public int tamaño() {
        return contenedor.length;
    }

    public void incremento() {
        this.numElem++;
    }

    public void decremento() {
        this.numElem--;
    }

    public boolean insertar(int valor) {
        if (!buscar(valor) && this.numElem < this.contenedor.length) {
            this.contenedor[this.numElem] = valor;
            incremento();
            return true;
        }
        return false;
    }

    public boolean buscar(int valor) {
        for (int i = 0; i < this.numElem; i++) {
            if (this.contenedor[i] == valor)
                return true;
        }
        return false;
    }

    public boolean extraer(int valor) {
        if (buscar(valor)) {
            for (int i = 0; i < this.numElem; i++) {
                if (this.contenedor[i] == valor && !(this.contenedor[this.numElem - 1] == valor)) {
                    for (int j = i + 1; j < this.numElem; j++) {
                        this.contenedor[j - 1] = this.contenedor[j];
                    }
                }
            }
            decremento();
            return true;
        }
        return false;
    }

    public void vaciar() {
        this.numElem = 0;
    }

    public int cardinal() {
        return this.numElem;
    }

    public int[] elementos() {
        int[] contenedorAux = new int[this.numElem];
        for (int i = 0; i < this.numElem; i++) {
            contenedorAux[i] = contenedor[i];
        }
        return contenedorAux;
    }
}