	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.*;
	import java.awt.image.Raster;
	import javax.imageio.ImageIO;
        import java.util.*;

	public class BBR {
	    
	    
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
                int blackco[][]=new int[w/2-1][h/2-1];
                int newblackco[][]=new int[w/2-1][h/2-1];
                for(int j=0;j<w/2-1;j++)
                {
                for(int k=0;k<h/2-1;k++)
                {
                blackco[j][k]=0;
                newblackco[j][k]=0;
                }
                }
                int ws=0, bs=0;
	        /*int values;
	        MersenneTwister rand = new MersenneTwister();
	        
	        int[][] array = new int[w][h];
	        int[][] value=new int[w][h];
	        int[][] S1=new int[w][h];
	        int[][] S2=new int[w][h];
	        int[][] S3=new int[w][h];*/
	        //Raster raster = ((BufferedImage)image).getData();
	        /*for (int j = 0; j < w; j++)
	        {
	            for (int k = 0; k < h; k++)
	            {
                        if((img.getRGB(j,k)+16777216)>(-1*((img.getRGB(j,k)+1)))) 
	                img.setRGB(j,k, -1);
                        else
                        img.setRGB(j,k, -16777216);
                        //img.setRGB(j,k, img.getRGB(j,k));
	            }
	        }*/
	        /*File outputfile2 =  new   File ("test"+"."+type);
	            ImageIO .write(img,type,outputfile2);*/
               BufferedImage img1 = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
                for (int j = 0; j < w-4; j+=2)
	        {
	            for (int k = 0; k < h-4; k+=2)
	            {
                        for(int i=0;i<4;i++)
                        {
                            for(int l=0;l<4;l++)
                            {
                                if(img.getRGB(j+i,k+l)==-16777216)
                                blackco[j/2][k/2]++;
                            }
                        }
                    }
                 }
	        for (int j = 0; j < w; j+=2)
	        {
	            for (int k = 0; k < h; k+=2)
	            {
                        int count=0;
                        if(img.getRGB(j,k)==-16777216) 
                        count++;
                        if(img.getRGB(j,k+1)==-16777216) 
                        count++;
                        if(img.getRGB(j+1,k)==-16777216) 
                        count++;
                        if(img.getRGB(j+1,k+1)==-16777216) 
                        count++;
                        if(count>2)
                        {
                            img1.setRGB(j,k, -16777216);
                            img1.setRGB(j+1,k, -16777216);
                            img1.setRGB(j,k+1, -16777216);
                            img1.setRGB(j+1,k+1, -16777216);
                        }
                        else
                        {
                            img1.setRGB(j,k, -1);
                            img1.setRGB(j+1,k, -1);
                            img1.setRGB(j,k+1, -1);
                            img1.setRGB(j+1,k+1, -1);
                        }
	            }
	        }
               
               for (int j = 0; j < w-4; j+=2)
	        {
	            for (int k = 0; k < h-4; k+=2)
	            {
                        for(int i=0;i<4;i++)
                        {
                            for(int l=0;l<4;l++)
                            {
                                if(img1.getRGB(j+i,k+l)==-16777216)
                                newblackco[j/2][k/2]++;
                            }
                        }
                    }
                 }
               for(int j=0;j<w/2-1;j++)
                {
                for(int k=0;k<h/2-1;k++)
                {
                System.out.print(blackco[j][k]+",");
                System.out.println(newblackco[j][k]);
                }
                }
                 System.out.println("now");
              for(int j=0;j<w-2;j+=2)
              {
                  for(int k=0;k<h-2;k+=2)
                  {
                      int count=0;
                        if(img.getRGB(j,k)==-16777216) 
                        count++;
                        if(img.getRGB(j,k+1)==-16777216) 
                        count++;
                        if(img.getRGB(j+1,k)==-16777216) 
                        count++;
                        if(img.getRGB(j+1,k+1)==-16777216) 
                        count++;
                    if(count!=2)
                    continue;
                     System.out.println(blackco[j/2][k/2]+","+newblackco[j/2][k/2]);
                    Random randomno = new Random();
                    if(blackco[j/2][k/2]>newblackco[j/2][k/2])
                    {
                        img1.setRGB(j,k, -1);
                            img1.setRGB(j+1,k, -1);
                            img1.setRGB(j,k+1, -1);
                            img1.setRGB(j+1,k+1, -1);
                    }
                    else
                    {
                      img1.setRGB(j,k, -16777216);
                            img1.setRGB(j+1,k, -16777216);
                            img1.setRGB(j,k+1, -16777216);
                            img1.setRGB(j+1,k+1, -16777216);
                    }
                 }
              }
            File outputfile3 =  new   File (fname+"_bbr"+"."+type);
	            ImageIO .write(img1,type,outputfile3);
	    }

	}

