import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class GameObject {
    public AffineTransform transform ; //the location/scale/rotation of our object
    public Shape shape; //the collider/rendered shape of this object
    public Material material; //data about the fill color, border color, and border thickness
    public ArrayList<ScriptableBehavior> scripts = new ArrayList<>(); //all scripts attached to the object
    public boolean active = true; //whether this gets Updated() and Draw()n

    //TODO: create the default GameObject use a default AffineTransform, default Material, and a 10x10 pix rectangle Shape at 0,0
    public GameObject(){
        this.transform = new AffineTransform();
        this.material = new Material();
        this.shape = new Rectangle2D.Float(0.0f, 0.0f, 10.0f, 10.0f);
    }

    //TODO: create the default GameObject, but with its AffineTransform translated to the coordinate x,y
    public GameObject(int x, int y) {
        this.transform = new AffineTransform();
        this.transform.translate(x, y);
        this.material = new Material();
        this.shape = new Rectangle2D.Float(0.0f, 0.0f, 10.0f, 10.0f);
    }

    //TODO: 1) save the pen's old transform, 2) transform it based on this object's transform, 3) draw either the styled shape, or the image scaled to the bounds of the shape.
    public void Draw(Graphics2D pen) {
        if (!active) {
            return;
        } else {
            AffineTransform old = pen.getTransform();
            pen.transform(this.transform);
            if (this.material.isShape) {
                pen.setPaint(material.fill);
                pen.fill(shape);
                pen.setPaint(material.border);
                pen.setStroke(new BasicStroke(material.borderWidth));
                pen.draw(shape);
            } else {
                pen.scale(2, 2);
                pen.drawImage(this.material.img, 0, 0, null);
            }

            pen.setTransform(old);
        }
    }

    //TODO: start all scripts on the object
    public void Start(){
        Iterator itr = this.scripts.iterator();

        while(itr.hasNext()) {
            ScriptableBehavior script = (ScriptableBehavior)itr.next();
            script.Start();
        }
    }

    //TODO: update all scripts on the object
    public void Update(){
        Iterator itr = this.scripts.iterator();

        while(itr.hasNext()) {
            ScriptableBehavior script = (ScriptableBehavior)itr.next();
            script.Update();
        }
    }

    //TODO: move the GameObject's transform
    public void Translate(float dX, float dY){
        this.transform.translate(dX, dY);
    }

    //TODO: scale the GameObject's transform around the CENTER of its shape
    public void Scale(float sX, float sY){
            // Get the bounds of the shape
            Rectangle2D bounds = this.shape.getBounds2D();

            // Calculate the center of the shape
            double centerX = bounds.getCenterX();
            double centerY = bounds.getCenterY();

            // Translate to the center, scale, and then translate back
            this.transform.translate(centerX, centerY);
            this.transform.scale(sX, sY);
            this.transform.translate(-centerX, -centerY);
    }

    //TODO: should return true if the two objects are touching (i.e., the intersection of their areas is not empty)
    public boolean CollidesWith(GameObject other){
        Area firstArea = new Area(this.shape);
        Area secondArea = new Area(other.shape);

        firstArea.transform(this.transform);
        secondArea.transform(other.transform);

        firstArea.intersect(secondArea);

        // Return true if both collide
        return !firstArea.isEmpty();
    }

    //TODO: should return true of the shape on screen contains the point
    public boolean Contains(Point2D point){
        Area firstArea = new Area(this.shape);
        firstArea.transform(this.transform);
        return firstArea.contains(point);
    }

}
