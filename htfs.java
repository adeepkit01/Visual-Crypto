	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.*;
	import java.awt.image.Raster;
	import javax.imageio.ImageIO;
        import java.util.*;

	public class htfs {
	    
	    
	    public static void 	main(String args[])throws IOException
	    {
                String fname=args[0]; String type=args[1];
                System.out.println(fname);
                System.out.println(type);
	        Image image = ImageIO.read(new File(fname+"."+type));
	        /*BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);*/
	        BufferedImage img = (BufferedImage)image;
	        int w = img.getWidth();
	        int h = img.getHeight();
                int ws=0, bs=0;
                Raster raster = ((BufferedImage)image).getData();
                int[][] array = new int[w/2][h/2];
                for (int j = 0; j < w/2; j++)
	        {
	            for (int k = 0; k < h/2; k++)
	            {
                        array[j][k]=0;
                     }
                 }
	        /*int values;
	        MersenneTwister rand = new MersenneTwister();
	        
	        int[][] array = new int[w][h];
	        int[][] value=new int[w][h];
	        int[][] S1=new int[w][h];
	        int[][] S2=new int[w][h];
	        int[][] S3=new int[w][h];*/
	        //Raster raster = ((BufferedImage)image).getData();
                /*BufferedImage img1 = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
	        for (int j = 0; j < w; j++)
	        {
	            for (int k = 0; k < h; k++)
	            {
                        if((img.getRGB(j,k)+16777216+array[j][k])>(-1*((img.getRGB(j,k)+1+array[j][k]))))
                        { 
	                img1.setRGB(j,k, -1);
                        array[j+1][k]=(int)(0.5*(img.getRGB(j,k)+1));
                        array[j][k+1]=(int)(0.25*(img.getRGB(j,k)+1));
                        array[j+1][k+1]=(int)(0.25*(img.getRGB(j,k)+1));
                        }
                        else
                        {
                        img1.setRGB(j,k, -16777216);
                        array[j+1][k]=(int)(0.5*(img.getRGB(j,k)+16777216));
                        array[j][k+1]=(int)(0.25*(img.getRGB(j,k)+16777216));
                        array[j+1][k+1]=(int)(0.25*(img.getRGB(j,k)+16777216));
                        }
                        //img.setRGB(j,k, img.getRGB(j,k));
	            }
	        }*/
	        /*File outputfile2 =  new   File ("test"+"."+type);
	            ImageIO .write(img,type,outputfile2);*/
                //Random randomno = new Random();
               BufferedImage img1 = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
                
	        /*for (int j = 0; j < w; j++)
	        {
                    for (int k = 0; k < h; k++)
	            {
	            
                     int op = img.getRGB(j,k),np,a;
                     if((img.getRGB(j,k)+16777216)>(-1*((img.getRGB(j,k)+1)))) 
	                np=-1;
                     else
                        np=-16777216;
                     img1.setRGB(j,k,np);
                     int qe=op-np;
                     if(j<w-1)
                     {
                     a=img.getRGB(j+1,k);
                     img.setRGB(j+1,k,(a+qe*7/16));
                     }
                     if(k<h-1)
                     {
                     a=img.getRGB(j,k+1);
                     img.setRGB(j,k+1,(a+qe*5/16));
                     }
                     if(j<w-1 && k<h-1)
                     {
                     a=img.getRGB(j+1,k+1);
                     img.setRGB(j+1,k+1,(a+qe*1/16));
                     }
                     if(j>0 && k<h-1)
                     {
                     a=img.getRGB(j-1,k+1);
                     img.setRGB(j-1,k+1,(a+qe*3/16));
                     }
                    }
                }*/
               for (int k = 0; k < h; k+=2)
	            {
               for (int j = 0; j < w; j+=2)
	        {

                     array[j/2][k/2]=(raster.getSample(j, k, 0)+raster.getSample(j+1, k, 0)+raster.getSample(j, k+1, 0)+raster.getSample(j+1, k+1, 0))/4;
                     if(j/2>0)
                     array[j/2][k/2]+=(7*array[j/2-1][k/2]/16);
                     if(k/2>0)
                     array[j/2][k/2]+=(5*array[j/2][k/2-1]/16);
                     if(j/2>0 && k/2>0)
                     array[j/2][k/2]+=(1*array[j/2][k/2-1]/16);
                     if(j/2<(w/2-1) && k/2>0)
                     array[j/2][k/2]+=(3*array[j/2+1][k/2-1]/16);
                     
                     int val2=-16777216;
                     int val1=-1;
                     //System.out.println(array[j/2][k/2]+","+val);
                     if(array[j/2][k/2]<32)
                     {
                     img1.setRGB(j,k,val2);
                     img1.setRGB(j+1,k,val2);
                     img1.setRGB(j,k+1,val2);
                     img1.setRGB(j+1,k+1,val2);
                     }
                     else if(array[j/2][k/2]<96)
                     {
                     array[j/2][k/2]-=64;
                     if(raster.getSample(j, k, 0)>=raster.getSample(j+1, k, 0) && raster.getSample(j, k, 0)>=raster.getSample(j, k+1, 0) && raster.getSample(j, k, 0)>=raster.getSample(j+1, k+1, 0))
                     {
                     img1.setRGB(j,k,val1);
                     img1.setRGB(j+1,k,val2);
                     img1.setRGB(j,k+1,val2);
                     img1.setRGB(j+1,k+1,val2);
                     }
                     else if(raster.getSample(j+1, k, 0)>=raster.getSample(j, k, 0) && raster.getSample(j+1, k, 0)>=raster.getSample(j, k+1, 0) && raster.getSample(j+1, k, 0)>=raster.getSample(j+1, k+1, 0))
                     {
                     img1.setRGB(j,k,val2);
                     img1.setRGB(j+1,k,val1);
                     img1.setRGB(j,k+1,val2);
                     img1.setRGB(j+1,k+1,val2);
                     }
                     else if(raster.getSample(j, k+1, 0)>=raster.getSample(j+1, k, 0) && raster.getSample(j, k+1, 0)>=raster.getSample(j, k, 0) && raster.getSample(j, k+1, 0)>=raster.getSample(j+1, k+1, 0))
                     {
                     img1.setRGB(j,k,val2);
                     img1.setRGB(j+1,k,val2);
                     img1.setRGB(j,k+1,val1);
                     img1.setRGB(j+1,k+1,val2);
                     }
                     else
                     {
                     img1.setRGB(j,k,val2);
                     img1.setRGB(j+1,k,val2);
                     img1.setRGB(j,k+1,val2);
                     img1.setRGB(j+1,k+1,val1);
                     }
                     }
                     else if(array[j/2][k/2]<160)
                     {
                     array[j/2][k/2]-=128;
                     if(raster.getSample(j, k+1, 0)>=raster.getSample(j+1, k, 0) && raster.getSample(j, k, 0)>=raster.getSample(j+1, k, 0) && raster.getSample(j, k+1, 0)>=raster.getSample(j+1, k+1, 0) && raster.getSample(j, k, 0)>=raster.getSample(j+1, k+1, 0))
                     {
                     img1.setRGB(j,k,val1);
                     img1.setRGB(j+1,k,val2);
                     img1.setRGB(j,k+1,val1);
                     img1.setRGB(j+1,k+1,val2);
                     }
                     else if(raster.getSample(j, k+1, 0)>=raster.getSample(j, k, 0) && raster.getSample(j+1, k, 0)>=raster.getSample(j, k, 0) && raster.getSample(j, k+1, 0)>=raster.getSample(j+1, k+1, 0) && raster.getSample(j+1, k, 0)>=raster.getSample(j+1, k+1, 0))
                     {
                     img1.setRGB(j,k,val2);
                     img1.setRGB(j+1,k,val1);
                     img1.setRGB(j,k+1,val1);
                     img1.setRGB(j+1,k+1,val2);
                     } 
                     else if(raster.getSample(j, k+1, 0)>=raster.getSample(j, k, 0) && raster.getSample(j+1, k+1, 0)>=raster.getSample(j, k, 0) && raster.getSample(j, k+1, 0)>=raster.getSample(j+1, k, 0) && raster.getSample(j+1, k+1, 0)>=raster.getSample(j+1, k, 0))
                     {
                     img1.setRGB(j,k,val2);
                     img1.setRGB(j+1,k,val2);
                     img1.setRGB(j,k+1,val1);
                     img1.setRGB(j+1,k+1,val1);
                     }
                     else  if(raster.getSample(j+1, k+1, 0)>=raster.getSample(j+1, k, 0) && raster.getSample(j, k, 0)>=raster.getSample(j+1, k, 0) && raster.getSample(j+1, k+1, 0)>=raster.getSample(j, k+1, 0) && raster.getSample(j, k, 0)>=raster.getSample(j, k+1, 0))
                     {
                     img1.setRGB(j,k,val1);
                     img1.setRGB(j+1,k,val2);
                     img1.setRGB(j,k+1,val2);
                     img1.setRGB(j+1,k+1,val1);
                     }
                     else  if(raster.getSample(j+1, k, 0)>=raster.getSample(j+1, k+1, 0) && raster.getSample(j, k, 0)>=raster.getSample(j+1, k+1, 0) && raster.getSample(j+1, k, 0)>=raster.getSample(j, k+1, 0) && raster.getSample(j, k, 0)>=raster.getSample(j, k+1, 0))
                     {
                     img1.setRGB(j,k,val1);
                     img1.setRGB(j+1,k,val1);
                     img1.setRGB(j,k+1,val2);
                     img1.setRGB(j+1,k+1,val2);
                     }
                     else
                     {
                     img1.setRGB(j,k,val2);
                     img1.setRGB(j+1,k,val1);
                     img1.setRGB(j,k+1,val2);
                     img1.setRGB(j+1,k+1,val1);
                     }
                     }
                     else if(array[j/2][k/2]<224)
                     {
                     array[j/2][k/2]-=192;
                     if(raster.getSample(j, k, 0)<=raster.getSample(j+1, k, 0) && raster.getSample(j, k, 0)<=raster.getSample(j, k+1, 0) && raster.getSample(j, k, 0)<=raster.getSample(j+1, k+1, 0))
                     {
                     img1.setRGB(j,k,val2);
                     img1.setRGB(j+1,k,val1);
                     img1.setRGB(j,k+1,val1);
                     img1.setRGB(j+1,k+1,val1);
                     }
                     else if(raster.getSample(j+1, k, 0)<=raster.getSample(j, k, 0) && raster.getSample(j+1, k, 0)<=raster.getSample(j, k+1, 0) && raster.getSample(j+1, k, 0)<=raster.getSample(j+1, k+1, 0))
                     {
                     img1.setRGB(j,k,val1);
                     img1.setRGB(j+1,k,val2);
                     img1.setRGB(j,k+1,val1);
                     img1.setRGB(j+1,k+1,val1);
                     }
                     else if(raster.getSample(j, k+1, 0)<=raster.getSample(j+1, k, 0) && raster.getSample(j, k+1, 0)<=raster.getSample(j, k, 0) && raster.getSample(j, k+1, 0)<=raster.getSample(j+1, k+1, 0))
                     {
                     img1.setRGB(j,k,val1);
                     img1.setRGB(j+1,k,val1);
                     img1.setRGB(j,k+1,val2);
                     img1.setRGB(j+1,k+1,val1);
                     }
                     else
                     {
                     img1.setRGB(j,k,val1);
                     img1.setRGB(j+1,k,val1);
                     img1.setRGB(j,k+1,val1);
                     img1.setRGB(j+1,k+1,val2);
                     }
                     }
                     else
                     {
                     array[j/2][k/2]-=256;
                     img1.setRGB(j,k,val1);
                     img1.setRGB(j+1,k,val1);
                     img1.setRGB(j,k+1,val1);
                     img1.setRGB(j+1,k+1,val1);
                     }
                    } 
                }
               File outputfile3 =  new   File (fname+"_htfs1"+"."+type);
	            ImageIO .write(img1,type,outputfile3);
               
	    }

	}

