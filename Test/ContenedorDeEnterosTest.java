import org.junit.Before;
import org.junit.jupiter.api.Test;

public class ContenedorDeEnterosTest {
    private ContenedorDeEnteros contenedor1;

    @Before
    public void setUp() {
        contenedor1 = new ContenedorDeEnteros(10);
    }

    @Test
    public void insertarElementos() {
        /*boolean insert1= this.contenedor.insertar(3);
        assertEquals("Verdadero", true, insert1);*/
    }

    @Test
    public void numElementos() {
        //assertThat(Integer.toString(0), is("0"));
        //assertThat("0", is(Integer.toString(new ContenedorDeEnteros(0).numElementos())));

        //assertThat(Integer.toString(new ContenedorDeEnteros(0).numElementos()), is("0"));
        //assertThat(Integer.toString(new ContenedorDeEnteros(5).numElementos()), is("5"));

        //contenedor.insertar(5);
        //contenedor.insertar(2);
        //assertEquals(contenedor.cardinal(), is(0));
    }
}
