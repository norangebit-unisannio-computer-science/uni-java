import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.io.PrintStream;

public class Archivio{

    public Archivio(Scanner scMult, Scanner scLibri, Scanner scStampe){
        archivio=new HashSet<Opera>();
 
        Opera op=Libro.read(scLibri);
        while(op!=null){
            archivio.add(op);
            op=Libro.read(scLibri);
        }

        op=OperaMult.read(scMult);
         while(op!=null){
            archivio.add(op);
            op=OperaMult.read(scMult);
        }

        op=Stampa.read(scStampe);
         while(op!=null){
            archivio.add(op);
            op=Stampa.read(scStampe);
        }
    }

    public Archivio(Set<Opera> archivio){
        this.archivio=archivio;
    }

    public void print(PrintStream ps){
        for(Opera o: archivio){
            if(o instanceof Libro)
                ((Libro) o).print(ps);
            if(o instanceof Stampa)
                ((Stampa) o).print(ps);
            if(o instanceof OperaMult)
                ((OperaMult) o).print(ps);
        }
    }

    public Archivio filtroAutore(String autore){
       Set<Opera> nuovoSet = new HashSet<Opera>();
       for(Opera o: archivio)
           if(o.getAutore().equals(autore))
            nuovoSet.add(o);

       return new Archivio(nuovoSet); 
   }
    
    private Set<Opera> archivio;
}