package Hospital.estructuras;

import java.util.Arrays;



public class QueueCircular <ELEMENT>{
	private final static Integer defaultDimension=10;
	
	//Atributos
	private ELEMENT[] dato;
	private int head;
	private int tail;
	private int count;
	
	//Constructores
	QueueCircular() {
		this(QueueCircular.defaultDimension);
	}
	
	QueueCircular(int dimension){
		this.dato=(ELEMENT[]) new Object [dimension];
		this.head=0;
		this.tail=0;
		this.count=0;
	}
	
	 private int next(int pos) {
	        if (++pos >= this.dato.length) {
	            pos = 0;
	        }
	        return pos;
	    }
	
	public boolean add(ELEMENT element) {
		 
        if (this.size() >= this.dato.length) {
            throw new IllegalStateException("Cola llena ...");
        }
 
        this.dato[this.tail] = element;
        this.tail = this.next(this.tail);
        ++this.count;
 
        return true;
    }
	
	public boolean offer(ELEMENT element) {
		 
        if (this.size() >= this.dato.length) {
            return false;
        }
 
        this.dato[this.tail] = element;
        this.tail = this.next(this.tail);
        ++this.count;
 
        return true;
    }
	
	public ELEMENT element() {
		 
        if (this.size() <= 0) {
            throw new IllegalStateException("Cola vacía ...");
        }
 
        return this.dato[this.head];
    }
	
	public ELEMENT pool() {
        if (this.size() <= 0) {
            return null;
        }
 
        ELEMENT result = this.dato[this.head];
        this.head = this.next(this.head);
        --this.count;
 
        return result;
    }
	
	public ELEMENT remove() {
        if (this.size() <= 0) {
            throw new IllegalStateException("Cola vacía ...");
        }
 
        ELEMENT result = this.dato[this.head];
        this.head = this.next(this.head);
        --this.count;
 
        return result;
    }
	
	public ELEMENT peek() {
        if (this.size() <= 0) {
            return null;
        }
 
        return this.dato[this.head];
    }
	
	public boolean isEmpty() {
        return this.count <= 0;
    }
 
    public int size() { //cuantos elementos tiene la cola
        return this.count;
    }

	@Override
	public String toString() {
		return "ColaCircularGenerica [dato=" + Arrays.toString(dato) + ", head=" + head + ", tail=" + tail + ", count="
				+ count + "]";
	}
 
    

}