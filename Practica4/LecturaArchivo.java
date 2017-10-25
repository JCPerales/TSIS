/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
/**
 *
 * @author invitado
 */
public class LecturaArchivo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int c,d;
        int[] histograma= new int[256];
	String archivo = ( "/root/Leeme.txt" );
	String archivo2 = ( "/root/Leeme2.txt" );
	File inFile = new File ( archivo ) ;
	File outFile = new File ( archivo2 ) ;
	//Inicializamos histograma
        for (int i=0; i<256; i++){
            histograma[i] = 0;
        }
        
        //Abrimos los archivos
	try {
		
            BufferedInputStream in = new BufferedInputStream( new FileInputStream ( inFile ) ) ;
            BufferedOutputStream salida = new BufferedOutputStream ( new FileOutputStream(outFile));
		
            while (( c = in.read() ) != -1) { // Lectura de los valores del archivo
                d=c;
                //Duplicamos el archivo
		salida.write(d);
                //Contabilizamos el numero de veces que ha aparecido c
                histograma[c]++;
            }
	
            in.close();
            salida.close();
	}catch ( Exception e ) {
        
        }
	
        makegnuplot(histograma);
        
    }
    
    public static void makegnuplot(int[] histograma){

	//Generamos un archivo con los datos del histograma
	//La idea es que despues podamos sacar los datos para graficarlos con otro programa
        File fichero = new File("plot.dat");
        try{
            FileWriter pw = new FileWriter (fichero) ;
            
            for(int i=0; i<256;i++){
                pw.write(i+" "+histograma[i]+"\n");
            }
	    pw.close();
	
	    /* Codigo para hacer que gnuplot grafique el histograma, aun no funciona
	     * String[] cmd = {"gnuplot", "-e", "\"set", "terminal", "png", "size", "800,600;", "set", "output", "'histograma.png';", "set", "xlabel" ,"'Valor", "asccii';", "set", "ylabel", "'Frecuencia'", ";", "set", "title", "'Histograma'", ";", "set", "xrange", "[0:255];", "plot", "'plot.dat'\""};

	System.out.println(cmd);
	Runtime.getRuntime().exec(cmd);
	*/
        }catch(Exception e){
            
        }

    }
}
