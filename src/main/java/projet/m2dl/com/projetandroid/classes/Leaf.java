package projet.m2dl.com.projetandroid.classes;

/**
 * Created by fabien on 19/01/15.
 */
import java.util.ArrayList;

public class Leaf {

    private String name;
    private ArrayList<Leaf> children;
    private Leaf father;

    public Leaf(String name, ArrayList<Leaf> children) {
        this.name = name;
        this.children = children;
    }

    public Leaf(String name, ArrayList<Leaf> children, Leaf father) {
        this.name = name;
        this.children = children;
        this.father = father;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the children
     */
    public ArrayList<Leaf> getChildren() {
        return children;
    }
    /**
     * @param children the children to set
     */
    public void setChildren(ArrayList<Leaf> children) {
        this.children = children;
    }

    /**
     * @return the father
     */
    public Leaf getFather() {
        return father;
    }

    /**
     * @param father the father to set
     */
    public void setFather(Leaf father) {
        this.father = father;
    }
}