package managers;

import java.awt.Graphics2D;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.ImageIcon;
import photo.Photo;

import providedClasses.ImageMatrix;
/**
 * includes most of photo-related methods
 * @author icebe
 *
 */
public class PhotoManager {
	private static HashMap<String, ArrayList<Photo>> userPhotos= new HashMap<>();

    public PhotoManager() {
        
    	photoReader();
        
    }
    /**
     * reads photos.txt and creates photos according to that
     */
    public static void photoReader() {
    	try (BufferedReader reader = new BufferedReader(new FileReader("src\\photos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 3) {
                    String username = parts[0];
                    ArrayList<Photo> photos = new ArrayList<>();
                    for (int i = 1; i < parts.length; i+=3) {
                    	String address= parts[i];
                    	String priv= parts[i+1];
                    	String description= parts[i+2];
                        photos.add(new Photo(address,priv,description));
                        
                    }
                    userPhotos.put(username, photos);
                    ProgramManager.userEntered(username).setPhotos(photos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * gets user photos map
     * @return
     */
    public static HashMap<String, ArrayList<Photo>> getUserPhotos() {
        return userPhotos;
    }

    
    	/**
    	 * Blurs the image.
    	 * @param myimage Image of type BufferedImage.
    	 * @param kernel Value of the kernel. If kernel is not between 0 and 21, function does nothing.
    	 * @return Blurred image of type BufferedImage
    	 */
    public static BufferedImage filterBlur(BufferedImage myimage, int kernel) {
    		if (kernel < 0 || kernel > 21) {
    			return myimage;
    		}
    		ImageMatrix image = new ImageMatrix(myimage);
    		ImageMatrix writeImage = new ImageMatrix(myimage.getWidth(), myimage.getHeight());
    		
    		for (int i = 0; i < image.getWidth(); i++) {
    			for (int j = 0; j < image.getHeight(); j++) {
    				try {
//    					int sum = 0;
    					int redsum = 0;
    					int greensum = 0;
    					int bluesum = 0;
    					
    					for (int i2 = i - kernel; i2 < i + kernel + 1 ; i2++) {
    						for (int j2 = j-kernel; j2 < j + kernel + 1; j2++) {
//    							sum += image.getRGB(i2, j2);
    							redsum += image.getRed(i2, j2);
    							greensum += image.getGreen(i2, j2);
    							bluesum += image.getBlue(i2, j2);
    						}
    					}
//    					sum /= ((2 * kernel) + 1) * ((2 * kernel) + 1);
    					redsum /= ((2 * kernel) + 1) * ((2 * kernel) + 1);
    					greensum /= ((2 * kernel) + 1) * ((2 * kernel) + 1);
    					bluesum /= ((2 * kernel) + 1) * ((2 * kernel) + 1);
    					writeImage.setRGB(i, j, ImageMatrix.convertRGB(redsum,greensum,bluesum));
    				}catch(IndexOutOfBoundsException e) {
    					writeImage.setRGB(i, j, image.getRGB(i, j));
    				}
    			}
    		}
    		
    		BufferedImage ret = writeImage.getBufferedImage();
    		return ret;
    	}
    	
    	/**
    	 * Sharpens the image.
    	 * @param myimage Image of type BufferedImage.
    	 * @param kernel Value of the kernel. If kernel is not between 0 and 21, function does nothing.
    	 * @return Sharpened image of type BufferedImage
    	 */
    public static BufferedImage sharpeningFilter(BufferedImage myimage, int kernel){
    		if (kernel < 0 || kernel > 21) {
    			return myimage;
    		}
    		ImageMatrix image = new ImageMatrix(myimage);
    		ImageMatrix writeImage = new ImageMatrix(myimage.getWidth(), myimage.getHeight());
    		
    		for (int i = 0; i < image.getWidth(); i++) {
    			for (int j = 0; j < image.getHeight(); j++) {
    				try {
//    					int sum = 0;
    					int redsum = 0;
    					int greensum = 0;
    					int bluesum = 0;
    					
    					for (int i2 = i - kernel; i2 < i + kernel + 1 ; i2++) {
    						for (int j2 = j - kernel; j2 < j + kernel + 1; j2++) {
//    							sum += image.getRGB(i2, j2);
    							redsum += image.getRed(i2, j2);
    							greensum += image.getGreen(i2, j2);
    							bluesum += image.getBlue(i2, j2);
    						}
    					}
//    					sum /= ((2 * kernel) + 1) * ((2 * kernel) + 1);
    					redsum /= ((2 * kernel) + 1) * ((2 * kernel) + 1);
    					greensum /= ((2 * kernel) + 1) * ((2 * kernel) + 1);
    					bluesum /= ((2 * kernel) + 1) * ((2 * kernel) + 1);
    					writeImage.setRGB(i, j, ImageMatrix.convertRGB(
    							kernelValidator(Math.abs(2 * image.getRed(i, j) - redsum)),     // To save time, instead of blurring and iterating
    							kernelValidator(Math.abs(2 * image.getGreen(i, j) - greensum)), // (which would iterate twice), sharpening is done
    							kernelValidator(Math.abs(2 * image.getBlue(i, j) - bluesum)))); // in one step.
    				}catch(IndexOutOfBoundsException e) {
    					writeImage.setRGB(i, j, image.getRGB(i, j));
    				}
    			}
    		}
    		
    		BufferedImage ret = writeImage.getBufferedImage();
    		return ret;
    	}
    	
    	/**
    	 * GrayScales the image.
    	 * @param myimage Image of type BufferedImage.
    	 * @param percentage How much grayscale is applied. If percentage is not between 0 and 1, this does nothing. 1 is the best grayscaled image.
    	 * @return Grayscaled image of type BufferedImage
    	 */
    public static BufferedImage filterGrayscaling(BufferedImage myimage, double percentage) {
    		if (percentage > 1 || percentage < 0) {
    			return myimage;
    		}
    		ImageMatrix image = new ImageMatrix(myimage);
    		ImageMatrix writeImage = new ImageMatrix(myimage.getWidth(), myimage.getHeight());
    		
    		for (int i = 0; i < image.getWidth(); i++) {
    			for (int j = 0; j < image.getHeight(); j++) {
    				double average = image.getRed(i, j) * 0.3 + image.getGreen(i, j) * 0.59 + image.getBlue(i, j) * 0.11;
    				writeImage.setRGB(i,j, ImageMatrix.convertRGB((int) Math.floor(average * percentage), (int) Math.floor(average * percentage), (int) Math.floor(average * percentage)));
    			}
    		}
    		
    		BufferedImage ret = writeImage.getBufferedImage();
    		return ret;
    	}
    	
    	/**
    	 * validates an integer is between 0 and 255 (useful for algorithm)
    	 * @param n The number that we want to check.
    	 * @return if n is more than 255, return 255. if n is less than 0, return 0. otherwise return n.
    	 */
    public static int kernelValidator(int n) {
    		return Math.max(Math.min(n, 255),0);
    	}
    	
    	/**
    	 * Changes the brightness of the image.
    	 * @param myimage BufferedImage.
    	 * @param change How much brightness is changed. If change is not between -255 and 255, function does nothing. change = 255 makes it very bright, -255 makes it very dark.
    	 * @return Image with changed brightness of type BufferedImage.
    	 */
    public static BufferedImage filterBrightness(BufferedImage myimage, int change) {
    		if (change < -255 || change > 255) {
    			return myimage;
    		}
    		ImageMatrix image = new ImageMatrix(myimage);
    		ImageMatrix writeImage = new ImageMatrix(myimage.getWidth(), myimage.getHeight());
    		
    		for (int i = 0; i < image.getWidth(); i++) {
    			for (int j = 0; j < image.getHeight(); j++) {
    				writeImage.setRGB(i,j, ImageMatrix.convertRGB(
    						kernelValidator(image.getRed(i, j) + change), 
    						kernelValidator(image.getGreen(i, j) + change), 
    						kernelValidator(image.getBlue(i, j) + change)));
    			}
    		}
    		
    		BufferedImage ret = writeImage.getBufferedImage();
    		return ret;
    	}
    	
    	/**
    	 * Changes the contrast of the image. 
    	 * @param myimage Image of type BufferedImage.
    	 * @param percentage How much contrast is changed. If percentage is not between 0 and 255, function does nothing. percentage = 255 increases contrast, 0 decrrases contrast. Percentage should be between 0 and 2 for best results.
    	 * @return Image with changed contrast of type BufferedImage.
    	 */
    public static BufferedImage filterContrast(BufferedImage myimage, double percentage) {
    	if (percentage < 0 || percentage > 255) {
    		return myimage;
    	}
    	ImageMatrix image = new ImageMatrix(myimage);
    	ImageMatrix writeImage = new ImageMatrix(myimage.getWidth(), myimage.getHeight());
    		
    	for (int i = 0; i < image.getWidth(); i++) {
    		for (int j = 0; j < image.getHeight(); j++) {
    			writeImage.setRGB(i,j, ImageMatrix.convertRGB(
    					kernelValidator((int) Math.floor(image.getRed(i, j) * (percentage))), 
    					kernelValidator((int) Math.floor(image.getGreen(i, j) * (percentage))), 
    					kernelValidator((int) Math.floor(image.getBlue(i, j) * (percentage)))));
    		}
    	}
    		
    	BufferedImage ret = writeImage.getBufferedImage();
    	return ret;
    }
    
    /**
     * convert imageIcon to BufferedImage 
     * @param imageIcon ImageIcon to be converted
     * @return BufferedImage
     */
    public static BufferedImage IconToBufferedImageCoverter(ImageIcon imageIcon) {

    		BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
    		Graphics2D g2d = bufferedImage.createGraphics();
    		g2d.drawImage(imageIcon.getImage(), 0, 0, null);
    		g2d.dispose();
    		return bufferedImage;
    }
    	
    	/**
    	 * Provides the edge detection of the image. 
    	 * @param myimage Image of type BufferedImage.
    	 * @param kernel Value of the kernel. If kernel is not between 0 and 5, function does nothing.
    	 * @return Edge detection of the image of type BufferedImage.
    	 */
    public static BufferedImage FilterEdgeDetection(BufferedImage myimage, int kernel){
    		if (kernel < 0 || kernel > 5) {
    			return myimage;
    		}
    		ImageMatrix image = new ImageMatrix(myimage);
    		ImageMatrix writeImage = new ImageMatrix(myimage.getWidth(), myimage.getHeight());
    		
    		for (int i = 0; i < image.getWidth(); i++) {
    			for (int j = 0; j < image.getHeight(); j++) {
    				try {
    					int redsum = 0;
    					int greensum = 0;
    					int bluesum = 0;
    					
    					for (int i2 = i - kernel; i2 < i + kernel + 1 ; i2++) {
    						redsum -= image.getRed(i2, j);
    						greensum -= image.getGreen(i2, j);
    						bluesum -= image.getBlue(i2, j);
    					}
    					
    					for (int j2 = j - kernel; j2 < j + kernel + 1 ; j2++) {
    						redsum -= image.getRed(i, j2);
    						greensum -= image.getGreen(i, j2);
    						bluesum -= image.getBlue(i, j2);
    					}
    					
    					redsum += (4 * kernel + 2) * image.getRed(i, j);
    					greensum += (4 * kernel + 2) * image.getGreen(i, j);
    					bluesum += (4 * kernel + 2) * image.getBlue(i, j);
    					writeImage.setRGB(i, j, ImageMatrix.convertRGB(kernelValidator(redsum), kernelValidator(greensum), kernelValidator(bluesum)));
    				}catch(IndexOutOfBoundsException e) {
    					writeImage.setRGB(i, j, image.getRGB(i, j));
    				}
    			}
    		}
    		
    		BufferedImage ret = writeImage.getBufferedImage();
    		return ret;
    	}
    		
    
    /**
     * add the photo into related hashmap and save it
     * @param username: User username
     * @param photoFile: photo file
     * @param priv: privacy of photo
     * @param description: description of photo
     */
    public void addUserPhoto(String username, File photoFile, String priv, String description) {
        if (userPhotos.containsKey(username)) {
            Photo photo=new Photo(photoFile.getAbsolutePath(),priv,description);
            
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader("src\\\\photos.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                	if(line.startsWith(username)) {
                		line+= ","+photoFile.getAbsolutePath()+","+priv+","+description;
                	}
                	content.append(line).append(System.lineSeparator());
                	
                	//((ArrayList<Photo> photoList = new ArrayList<>(Arrays.asList(line.split(",")));
                	ProgramManager.userEntered(username).addPhoto(photo);
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\photos.txt"))) {
                writer.write(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    
    
    
}
