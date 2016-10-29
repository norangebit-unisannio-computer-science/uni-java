import java.io.*;
import java.util.Scanner;

public class Matrice{

    public Matrice(int a, int b){
        matrice = new double[a][b];
        this.a=a;
        this.b=b;
    }

    public double getElement(int a, int b){
        return matrice[a][b];
    }

    public void setElement(int a, int b, double c){
        this.matrice[a][b]=c;
    }

    public int getA(){
        return a;
    }

    public int getB(){
        return b;
    }

    public String toString(){
        int i, j;
        String string = "";
        for(i=0; i<a; i++){
            string+="|  ";
            for(j=0; j<b; j++)
                string+=getElement(i,j) + "  ";
            string+="|\n";
        }
        return string;
    }

    public void print(PrintStream ps){
        ps.println(toString());
    }

    public static Matrice read(Scanner sc) throws Exception{
        int a, b, i, j;
        Matrice m1;

        if(!sc.hasNextInt()) return null;
        a=sc.nextInt();
        if(!sc.hasNextInt()) return null;
        b=sc.nextInt();

        m1 = new Matrice(a, b);

        for(i=0; i<a; i++)
            for(j=0; j<b; j++){
                if(!sc.hasNextDouble()) return null;
                m1.setElement(i, j, sc.nextDouble());
            }

        return m1;
    }

    private boolean isSummable(Matrice m2){
        return this.a==m2.getA() && this.b==m2.getB();
    }

    private boolean isMoltiplicabile(Matrice m2){
        return getB()==m2.getA();
    }

    private boolean isSquare(){
        return this.a==this.b;
    }

    public boolean equlas(Matrice m2){
        int i, j;
        boolean equals = true;

        if(!isSummable(m2)) return false;
        for(i=0;i<a && equals;i++)
            for(j=0;j<b && equals;j++)
                equals &= this.getElement(i, j)==m2.getElement(i, j);

        return equals;
    }

    public Matrice transposed(){
        Matrice m2 = new Matrice(b, a);
        int i, j;
        for(i=0; i<a; i++)
            for(j=0; j<b; j++)
                m2.setElement(j, i, getElement(i, j));

        return m2;
    }

    /*
    public static double det(m){
        if(this.a==2 && this.b==2)
            return m.getElement(0,0)*m.getElement(1,1)-m.getElement(1,0)*m.getElement(0,1);
        else{

        }

    }*/

    public Matrice product(Matrice m2){
        int a=this.a;
        int b=m2.getB();
        int c=m2.getA();
        int i, j, k;
        double element;
        Matrice m3 = new Matrice(a, b);

        if(!isMoltiplicabile(m2)) return null;

        for(i=0;i<a;i++)
            for(j=0;j<b;j++){
                element=0;
                for(k=0;k<c;k++)
                    element+=getElement(i, k)*m2.getElement(k, j);
                m3.setElement(i, j, element);
            }

        return m3;
    }

    public Matrice sum(Matrice m2){
        Matrice m3 = new Matrice(getA(), getB());
        int i, j;

        if(!isSummable(m2)) return null;
        for(i=0; i<getA(); i++)
            for(j=0; j<getB(); j++)
                m3.setElement(i, j, getElement(i, j)+m2.getElement(i, j));

        return m3;
    }

    private double[][] matrice;
    private int a, b;
}
