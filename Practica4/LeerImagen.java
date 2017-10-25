/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
/**
 *
 * @author invitado
 */
public class LeerImagen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int c,d;
        int[] histograma_r= new int[256];
        int[] histograma_g= new int[256];
        int[] histograma_b= new int[256];
	
	String archivo = ( "/root/bmp.bmp" );
	File inFile = new File(archivo);
	//Inicializamos histogramas
        for (int i=0; i<256; i++){
            histograma_r[i] = 0;
            histograma_g[i] = 0;
            histograma_b[i] = 0;
        }
        
        //Abrimos los archivos
	try {
		
		BufferedImage img=ImageIO.read(inFile);	
            for (int x=0;x<img.getWidth();x++) {
		for(int y=0;y<img.getHeight();y++){
			int rgb = img.getRGB(x,y);
			Color color = new Color(rgb,true);
			int r=color.getRed();
			int g=color.getGreen();
			int b=color.getBlue();
                	
			histograma_r[r]++;
			histograma_g[g]++;
			histograma_b[b]++;
            	}
	    }
	
	}catch ( Exception e ) {
        
        }
	
        makegnuplot(histograma_r,"rojo");
        makegnuplot(histograma_g,"verde");
        makegnuplot(histograma_b,"azul");

        
    }
    
    public static void makegnuplot(int[] histograma, String color){

	//Generamos un archivo con los datos del histograma
	//La idea es que despues podamos sacar los datos para graficarlos con otro programa
        File fichero = new File("plot"+color+".dat");
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
