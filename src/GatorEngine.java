import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;


public class GatorEngine {
    //UI Components (things that are "more" related to the UI)
    static JFrame WINDOW;
    static JPanel DISPLAY_CONTAINER;
    static JLabel DISPLAY_LABEL;
    static BufferedImage DISPLAY;
    static int WIDTH=500, HEIGHT=500;

    //Engine Components (things that are "more" related to the engine structures)
    static Graphics2D RENDERER;
    static ArrayList<GameObject> OBJECTLIST = new ArrayList<>(); //list of GameObjects in the scene
    static ArrayList<GameObject> CREATELIST = new ArrayList<>(); //list of GameObjects to add to OBJECTLIST at the end of the frame
    static ArrayList<GameObject> DELETELIST = new ArrayList<>(); //list of GameObjects to remove from OBJECTLIST at the end fo the frame
    static float FRAMERATE = 60; //target frames per second;
    static float FRAMEDELAY = 1000/FRAMERATE; //target delay between frames
    static Timer FRAMETIMER; //Timer controlling the update loop
    static Thread FRAMETHREAD; //the Thread implementing the update loop
    static Thread ACTIVE_FRAMETHREAD; //a copy of FRAMETHREAD that actually runs.


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CreateEngineWindow();
            }
        });
    }

    static void CreateEngineWindow(){
        //Sets up the GUI
        WINDOW = new JFrame("Gator Engine");
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WINDOW.setVisible(true);

        DISPLAY = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
        RENDERER = (Graphics2D) DISPLAY.getGraphics();
        DISPLAY_CONTAINER = new JPanel();
        DISPLAY_CONTAINER.setFocusable(true);
        DISPLAY_LABEL = new JLabel(new ImageIcon(DISPLAY));
        DISPLAY_CONTAINER.add(DISPLAY_LABEL);
        WINDOW.add(DISPLAY_CONTAINER);
        WINDOW.pack();

        //TODO: make this 1)execute Update(), 2) clear any inputs that need to be removed between frames, and 3) repaint the GUI back on the EDT.
        FRAMETHREAD = new Thread(new Runnable() {
            @Override
            public void run() {
                // 1) call the Update() function that is in the GatorEngine class
                Update();
                // 2) Call the UpdateInputs() function from the Input class,
                Input.UpdateInputs();
                // 3) Update the OBJECTLIST with UpdateObjectList(),
                UpdateObjectList();
                // 4) Repaint the window back on the EDT.
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        WINDOW.repaint();
                    }
                });
                //
            }
        });

        //This copies the template thread made above
        ACTIVE_FRAMETHREAD = new Thread(FRAMETHREAD);

        //TODO: create a timer that will create/run ACTIVE_FRAMETHREAD, but only if it it hasn't started/has ended
        FRAMETIMER = new Timer((int)FRAMEDELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!GatorEngine.ACTIVE_FRAMETHREAD.isAlive()) {
                    GatorEngine.ACTIVE_FRAMETHREAD = new Thread(GatorEngine.FRAMETHREAD);
                    GatorEngine.ACTIVE_FRAMETHREAD.start();
                }

                GatorEngine.FRAMETIMER.restart();
            }
        });
        FRAMETIMER.start();

        Start();

        //===================INPUT=========================
        //Set up some action listeners for input on the PANEL
        //These should update the Input classes ArrayLists and other members
        //TODO: use the correct listener functions to modify INPUT
        DISPLAY_CONTAINER.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                Input.pressed.add(e.getKeyChar());
                Input.held.add(e.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Input.released.add(e.getKeyChar());
            }
        });
        DISPLAY_CONTAINER.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Input.MouseClicked = true;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Input.MousePressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Input.MousePressed = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        DISPLAY_CONTAINER.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Input.MouseX = e.getX() - GatorEngine.DISPLAY_LABEL.getLocation().x;
                Input.MouseY = e.getY() - GatorEngine.DISPLAY_LABEL.getLocation().y;
            }
        });
    }

    //TODO: add the GameObject to the OBJECTLIST
    static void Create(GameObject g){
        CREATELIST.add(g);
    }

    //TODO: remove the GameObject from the OBJECTLIST
    static void Delete(GameObject g){
        DELETELIST.add(g);
    }

    //TODO: 1) remove objects in DELETELIST from OBJECTLIST, 2) add objects in CREATELIST to OBJECTLIST, 3) remove all items from DELETELIST and CREATELIST
    static void UpdateObjectList(){
        // 1) remove objects in DELETELIST from OBJECTLIST
        OBJECTLIST.removeAll(DELETELIST);

        // 2) add objects in CREATELIST to OBJECTLIST
        OBJECTLIST.addAll(CREATELIST);

        // 3) remove all items from DELETELIST and CREATELIST
        DELETELIST.clear();
        CREATELIST.clear();
    }

    //This begins the "user-side" of the software; above should set up the engine loop, data, etc.
    //Here you can create GameObjects, assign scripts, set parameters, etc.
    //NOTE: This is where we should be able to insert out own code and scripts
    static void Start(){
        GatorInvaders.Start();
        //TODO: Start() all objects in OBJECTLIST
        //At the end, you will need to implement logic to loop over every GameObject in OBJECTLIST and
        //start them at the end of the function once everything has been set up.
        Iterator itr = OBJECTLIST.iterator();

        while(itr.hasNext()) {
            GameObject game = (GameObject)itr.next();
            game.Start();
        }
    }

    //TODO: Redraw the Background(), then Draw() and Update() all GameObjects in OBJECTLIST
    //First, you should call the Background() function to clear the old frame and draw the background.
    //Then, you should loop over every GameObject in OBJECTLIST, draw them by passing the
    //RENDERER to their DRAW() function, and then call their own Update() function.
    static void Update(){
        Background();
        Iterator itr = OBJECTLIST.iterator();

        while(itr.hasNext()) {
            GameObject game = (GameObject)itr.next();
            game.Draw(RENDERER);
            game.Update();
        }
    }

    //draws a background on the Renderer. right now it is solid, but we could load an image
    //done for you!
    static void Background(){
        RENDERER.setColor(Color.WHITE);
        RENDERER.fillRect(0,0,WIDTH,HEIGHT);
    }


}
