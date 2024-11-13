package Hospital.estructuras;

public class PilaGenerica<ELEMENT> {

    private final static Integer defaultDimension = 10; //constante dimension de la pila

    private ELEMENT[] data;   //pila para guardar cualquier cosa
    private Integer count;  //variable que me apunta al proximo lugar done va a ingresar el proximo valor

    public PilaGenerica() {
        this(PilaGenerica.defaultDimension);
    }

    public PilaGenerica(Integer dimension) {
        if (dimension <= 0) {
            throw new RuntimeException("La cantidad de elementos de la pila debe ser positiva...");
        }
        this.data = (ELEMENT[]) new Object[dimension];
        this.count = 0;
    }

    public ELEMENT push(ELEMENT elemento) {
        if (this.count >= this.data.length) {
            throw new RuntimeException("La pila esta llena...");
        }
        //si la pila no esta llena puedo agregar
        this.data[this.count] = elemento; //datos en la posicion que apunta count le voy a asiganr el elemento
        ++this.count; // primero asigno y DESPUES al contador lo sumo en 1
        return elemento;
    }

    public ELEMENT pop() {
        if (this.empty()) {
            throw new RuntimeException("La pila esta vacia..."); // no puedo sacar de donde no hay
        }
        --this.count;  //primero resto, ya que count esta adelantado una posicion
        return this.data[this.count]; //despues saco el elemento
    }

    public boolean empty() {
        return this.count <= 0;
    }

    public ELEMENT peek() {
        if (this.empty()) {
            throw new RuntimeException("La pila esta vacia...");
        }
        return this.data[this.count - 1];
    }
}
