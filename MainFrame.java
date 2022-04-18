

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * This program implements a GUI to display the optimal travel route between a group of cities and the clusters. The cities can be marked
 * on screen with a mouse click. Also, we can create , move and connect from the action menu bar in the top navigation bar .we have decorator
 * pattern to decorate a city , strategy pattern to navigate to the algorithms . 
 *
 * @author Zhuoran Li
 * @version 1.0
 * @since 2021-10-02
 * 
 * @author chandana bandlamudi
 * @version 2.0
 */
public class MainFrame extends JFrame {
    private static final int DEFAULT_WINDOW_HEIGHT = 500;
    private static final int DEFAULT_WINDOW_WIDTH = 600;
    
    
    
    /**
     * we have the menu file, connections and the actions.
     * 
     * in the file we have new, save and load the files
     * in the connections we have tsp nearest neighbour algorithm , tsp pro , clusters and the userconnect
     * in the actions we have create , connect and the move 
     */
    public MainFrame() {
        super("Travelling Salesman Path Plotting Tool");
        setLayout(new BorderLayout());




        WorkSpace workSpace = new WorkSpace();
        WorkSpacePanel drawArea = new WorkSpacePanel(workSpace);
        Algorithms algorithms = new Algorithms();

        algorithms.addObserver(workSpace);
        algorithms.addObserver(drawArea);
        workSpace.addObserver(algorithms);

        add(drawArea, BorderLayout.CENTER);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        JMenu ConnectionsMenu = new JMenu("Connections");//new change
        JMenu ActionMenu = new JMenu("Action");//new change
        JLabel LogLabel = new JLabel("",JLabel.CENTER);//new change
        

        JMenuItem mItemNew = new JMenuItem("New");
        mItemNew.addActionListener(ev -> {
        	 drawArea.clearRoutes();
        	 drawArea.clearWorkspace();
        	  drawArea.block();
            LogLabel.setText("New option from File menu is clicked");//new change
        });
        
       JMenuItem TSP_NN = new JMenuItem("TSP - Nearest Neighbour");//new change
       TSP_NN.addActionListener(ev -> {
    	   drawArea.clearRoutes();
     	   drawArea.repaint();
      	  drawArea.block();
           algorithms.setAlgorithms(new TSP());
    	   LogLabel.setText("TSP - Nearest Neighbour is selected");//new change
       });
       JMenuItem TSP_Pro = new JMenuItem("TSP - Pro");//new change
        TSP_Pro.addActionListener(ev -> {
      	   drawArea.clearRoutes();
     	   drawArea.repaint();
      	  drawArea.block();
            algorithms.setAlgorithms(new TSPPro("thread"));
            LogLabel.setText("TSP - Pro is selected");
        });

       JMenuItem Clusters = new JMenuItem("Clusters");//new change
        Clusters.addActionListener(ev -> {
      	   drawArea.clearRoutes();
     	   drawArea.repaint();
     	  drawArea.block();
            algorithms.setAlgorithms(new Cluster());
            LogLabel.setText("Cluster is selected");
        });
       JMenuItem User_Connect = new JMenuItem("User Connect");//new change
        User_Connect.addActionListener(ev -> {
     	   drawArea.clearRoutes();
     	   drawArea.repaint();
            drawArea.userconnect();
        });
       
       JMenuItem Move = new JMenuItem("Move");//new change
       Move.addActionListener(ev ->{
    	   drawArea.clear();
            drawArea.moving();
       });
       JMenuItem Connect = new JMenuItem("Connect");//new change
       Connect.addActionListener(ev ->{
            drawArea.connect();

       });
       JMenuItem Create = new JMenuItem("Create");//new change
       Create.addActionListener(ev ->{
           drawArea.create();
      });

        JMenuItem mItemLoad = new JMenuItem("Load");
        mItemLoad.addActionListener(ev -> {
            File selectedFile = displayFileSelectionDialog();
            if (selectedFile != null) {
                try { workSpace.load(selectedFile); }
                catch (IOException e) {
                    String msg = String.format("Failed To Load Data From File\nException: %s", e);
                    JOptionPane.showMessageDialog(this, msg);
                }
                drawArea.repaint();
            }
            LogLabel.setText("Load Option from File menu is selected");//new change  
        });

        JMenuItem mItemSave = new JMenuItem("Save");
        mItemSave.addActionListener(ev -> {
            File selectedFile = displayFileSaveDialog();
            if (selectedFile != null) {
                try { workSpace.save(selectedFile); }
                catch (IOException e) {
                    String msg = String.format("Failed To Save Data To File\nException: %s", e);
                    JOptionPane.showMessageDialog(this, msg);
                }
            }
            LogLabel.setText("Save option from File menu is clicked");//new change
        });

        fileMenu.add(mItemNew);
        fileMenu.add(new JSeparator());
        fileMenu.add(mItemLoad);
        fileMenu.add(mItemSave);
        
        ConnectionsMenu.add(TSP_NN);//new change
        ConnectionsMenu.add(TSP_Pro);//new change
        ConnectionsMenu.add(Clusters);//new change
        ConnectionsMenu.add(User_Connect);//new change
        
        ActionMenu.add(Move);//new change
        ActionMenu.add(Connect);//new change
        ActionMenu.add(Create);//new change

        menuBar.add(fileMenu);
        menuBar.add(ConnectionsMenu);//new change
        menuBar.add(ActionMenu);//new change

        add(menuBar, BorderLayout.NORTH);
        add(LogLabel,BorderLayout.PAGE_END);//new change
        
    }

    /**
     * Entry point for the program. It creates an instance of the GUI, configures its physical attributes and displays
     * it on screen.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        mainFrame.setVisible(true);
    }

    private File displayFileSelectionDialog() {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            return jFileChooser.getSelectedFile();
        else return null;
    }

    private File displayFileSaveDialog() {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            return jFileChooser.getSelectedFile();
        else return null;
    }
    
}