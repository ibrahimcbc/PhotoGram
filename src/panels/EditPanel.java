package panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

import managers.PhotoManager;
import managers.ProgramManager;
import users.User;
/**
 * 
 * @author icebe
 *
 */
public class EditPanel extends JPanel{
	
	JLabel originalPhotoLabel= new JLabel();
	JLabel editedPhotoLabel= new JLabel();
	JLabel blurLabel= new JLabel();
	JLabel sharpenLabel= new JLabel();
	JLabel grayscaleLabel= new JLabel();
	JLabel edgeDetectionLabel= new JLabel();
	JLabel contrastLabel= new JLabel();
	JLabel brightnessLabel= new JLabel();
	JLabel arrowLabel=new JLabel();
	JLabel errorLabel=new JLabel();
	
	JSlider blurSlider=new JSlider(0,21,1);
	JSlider sharpenSlider=new JSlider(0,21,1);
	JSlider grayscaleSlider=new JSlider(1,10);
	JSlider edgeDetectionSlider=new JSlider(0,5);
	JSlider contrastSlider=new JSlider(0,255);
	JSlider brightnessSlider=new JSlider(-255,255);
	
	JButton applyBlur=new JButton();
	JButton applySharpen=new JButton();
	JButton applyGrayscale=new JButton();
	JButton applyEdgeDetection=new JButton();
	JButton applyContrast=new JButton();
	JButton applyBrightness=new JButton();
	JButton downloadButton=new JButton();
	
	BufferedImage bufferedImage;
	
	/**
	 * For filtering images
	 * @param username: User username
	 * @param image: ImageIcon of the image that going to be edited
	 */
	public EditPanel(String username,ImageIcon image) {
		bufferedImage=PhotoManager.IconToBufferedImageCoverter(image);
		User user=ProgramManager.userEntered(username);
		String rank=user.getRank();
		this.setLayout(null);
		this.setBackground(Color.gray);
		this.setBounds(0,0,1000,700);
		
		originalPhotoLabel.setBounds(200,100,240, 240);
		originalPhotoLabel.setIcon(ProgramManager.setImageBounds(image, 240, 240));
		
		editedPhotoLabel.setBounds(560,100,240,240);
		
		arrowLabel.setBounds(470,180,60,60);
		arrowLabel.setBackground(Color.white);
		arrowLabel.setIcon(ProgramManager.setImageBounds(new ImageIcon("src\\icons\\icons8-right-arrow-60.png"), 60, 60));
	
		blurLabel.setBounds(125,380,150,30);
		blurLabel.setText("Blur:");
		blurSlider.setBounds(125,410,150 ,30 );
		blurSlider.setBackground(this.getBackground());
		applyBlur.setBounds(300,410,30,30);
		applyBlur.setBackground(this.getBackground());
		applyBlur.setIcon(ProgramManager.setImageBounds(new ImageIcon("src\\icons\\icons8-tick-30.png"), 30, 30));
		
		sharpenLabel.setBounds(125,480,150,30);
		sharpenLabel.setText("Sharp:");
		sharpenSlider.setBounds(125, 510, 150, 30);
		sharpenSlider.setBackground(this.getBackground());
		applySharpen.setBounds(300,510,30,30);
		applySharpen.setBackground(this.getBackground());
		applySharpen.setIcon(ProgramManager.setImageBounds(new ImageIcon("src\\icons\\icons8-tick-30.png"), 30, 30));
		
		brightnessLabel.setBounds(425,380,150,30);
		brightnessLabel.setText("Brightness:");
		brightnessSlider.setBounds(425,410,150,30);
		brightnessSlider.setBackground(this.getBackground());
		applyBrightness.setBounds(600,410,30,30);
		applyBrightness.setBackground(this.getBackground());
		applyBrightness.setIcon(ProgramManager.setImageBounds(new ImageIcon("src\\icons\\icons8-tick-30.png"), 30, 30));

		
		contrastLabel.setBounds(425,480,150,30);
		contrastLabel.setText("Contrast:");
		contrastSlider.setBounds(425,510,150,30);
		contrastSlider.setBackground(this.getBackground());
		applyContrast.setBounds(600,510,30,30);
		applyContrast.setBackground(this.getBackground());
		applyContrast.setIcon(ProgramManager.setImageBounds(new ImageIcon("src\\icons\\icons8-tick-30.png"), 30, 30));
		
		grayscaleLabel.setBounds(725,380,150,30);
		grayscaleLabel.setText("Grayscale:");
		grayscaleSlider.setBounds(725,410,150,30);
		grayscaleSlider.setBackground(this.getBackground());
		applyGrayscale.setBounds(900,410,30,30);
		applyGrayscale.setBackground(this.getBackground());
		applyGrayscale.setIcon(ProgramManager.setImageBounds(new ImageIcon("src\\icons\\icons8-tick-30.png"), 30, 30));
		
		edgeDetectionLabel.setBounds(725,480,150,30);
		edgeDetectionLabel.setText("Edge Detection:");
		edgeDetectionSlider.setBounds(725,510,150,30);
		edgeDetectionSlider.setBackground(this.getBackground());
		applyEdgeDetection.setBounds(900,510,30,30);
		applyEdgeDetection.setBackground(this.getBackground());
		applyEdgeDetection.setIcon(ProgramManager.setImageBounds(new ImageIcon("src\\icons\\icons8-tick-30.png"), 30, 30));
		
		downloadButton.setBounds(440,560,30,30);
		downloadButton.setBackground(this.getBackground());
		downloadButton.setIcon(ProgramManager.setImageBounds(new ImageIcon("src\\icons\\icons8-download-30.png"), 30, 30));
		
		errorLabel.setBounds(400,545,360,15);
		errorLabel.setBackground(this.getBackground());
		errorLabel.setForeground(Color.red);
		
		/**
		 * applies blur
		 */
		applyBlur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            	
        		bufferedImage=PhotoManager.filterBlur(bufferedImage,blurSlider.getValue());
        		editedPhotoLabel.setIcon(ProgramManager.setImageBounds(new ImageIcon(bufferedImage), 240, 240));
        		errorLabel.setText("");
        		updateUI();
            	
            }
            
        });
		/**
		 * applies sharpen
		 */
		applySharpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	bufferedImage=PhotoManager.sharpeningFilter(bufferedImage,sharpenSlider.getValue());
            	editedPhotoLabel.setIcon(ProgramManager.setImageBounds(new ImageIcon(bufferedImage), 240, 240));
            	errorLabel.setText("");
        		updateUI();
            	
            }
            
        });
		/**
		 * applies grayscale
		 */
		applyGrayscale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(user.getRank().equals("professional") || user.getRank().equals("admin") ) {
            	bufferedImage=PhotoManager.filterGrayscaling(bufferedImage, grayscaleSlider.getValue()*0.1);
            	editedPhotoLabel.setIcon(ProgramManager.setImageBounds(new ImageIcon(bufferedImage), 240, 240));
            	errorLabel.setText("");
        		updateUI();
            	}else {
            		errorLabel.setText("Your rank is not enough for this filter.");
            	}            	
            }           
        });
		/**
		 * applies edge detection
		 */
		applyEdgeDetection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(user.getRank().equals("professional") || user.getRank().equals("admin") ) {
            	bufferedImage=PhotoManager.FilterEdgeDetection(bufferedImage, edgeDetectionSlider.getValue());
        		editedPhotoLabel.setIcon(ProgramManager.setImageBounds(new ImageIcon(bufferedImage), 240, 240));       	
        		errorLabel.setText("");
        		updateUI();     	
            }else {
            	errorLabel.setText("Your rank is not enough for this filter.");
            }
            }
        });
		/**
		 * applies constrast
		 */
		applyContrast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(user.getRank().equals("professional") || user.getRank().equals("hobbyist") || user.getRank().equals("admin") ) {
            	bufferedImage=PhotoManager.filterContrast(bufferedImage, contrastSlider.getValue());
            	editedPhotoLabel.setIcon(ProgramManager.setImageBounds(new ImageIcon(bufferedImage), 240, 240));
            	errorLabel.setText("");
        		updateUI();
            }else {
            	errorLabel.setText("Your rank is not enough for this filter.");
            }
            }
        });
		/**
		 * sets the brightness of image
		 */
		applyBrightness.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(user.getRank().equals("professional") || user.getRank().equals("hobbyist") || user.getRank().equals("admin") ) {
            	bufferedImage=PhotoManager.filterBrightness(bufferedImage, brightnessSlider.getValue());
            	editedPhotoLabel.setIcon(ProgramManager.setImageBounds(new ImageIcon(bufferedImage), 240, 240));
            	errorLabel.setText("");
        		updateUI();
            	
            }else {
            	errorLabel.setText("Your rank is not enough for this filter.");
            }
            }
        });
		/**
		 * downloads the image on desired location
		 */
		downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle download button click
            	 if (bufferedImage != null) {
                     // Create a file chooser dialog for saving the image
                     JFileChooser fileChooser = new JFileChooser();
                     fileChooser.setDialogTitle("Save Image");
                     int userSelection = fileChooser.showSaveDialog(null);

                     if (userSelection == JFileChooser.APPROVE_OPTION) {
                         // Get the selected file path
                         File fileToSave = fileChooser.getSelectedFile();

                         try {
                             // Save the image to the selected file
                             ImageIO.write(bufferedImage, "png", fileToSave);
                             
                         } catch (IOException ex) {
       
                         }
                     }
                 }
        }});
		
		this.add(applyBlur);
		this.add(applyBrightness);
		this.add(applyContrast);
		this.add(applyEdgeDetection);
		this.add(applyGrayscale);
		this.add(applySharpen);
		this.add(downloadButton);
		this.add(blurLabel);
		this.add(blurSlider);
		this.add(brightnessLabel);
		this.add(brightnessSlider);
		this.add(arrowLabel);
		this.add(originalPhotoLabel);
		this.add(editedPhotoLabel);
		this.add(sharpenLabel);
		this.add(sharpenSlider);
		this.add(grayscaleLabel);
		this.add(grayscaleSlider);
		this.add(edgeDetectionLabel);
		this.add(edgeDetectionSlider);
		this.add(contrastLabel);
		this.add(contrastSlider);
		this.add(errorLabel);
	}
}
