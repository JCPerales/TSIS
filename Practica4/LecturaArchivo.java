/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaarchivo;
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
	String archivo = ( "/home/invitado/Descargas/archivos/DOC.doc" );
	String archivo2 = ( "/home/invitado/Descargas/archivos/DOC2.doc" );
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
        FileWriter fichero = null ;
        PrintWriter pw = null ;
        try{
            fichero = new FileWriter ("/home/invitado/Descargas/plot") ;
            pw = new PrintWriter ( fichero ) ;
            pw.println ("") ;
            for(int i=0; i<256;i++){
                pw.println(i+"\t"+histograma[i]);
            }
        }catch(Exception e){
            
        }
    }
}