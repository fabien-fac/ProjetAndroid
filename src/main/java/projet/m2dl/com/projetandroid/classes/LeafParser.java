package projet.m2dl.com.projetandroid.classes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by fabien on 19/01/15.
 */
public class LeafParser {

    public Leaf parseXmlInputStream(InputStream xmlInputStream){
        try {
            //File fXmlFile = new File("/home/user/Documents/tree.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlInputStream);

            Leaf leaf = new Leaf("root", new ArrayList<Leaf>());
            parse(leaf, doc.getDocumentElement());

            for(Leaf l : leaf.getChildren()){
                displayLeaf(l, 0);
            }

            return leaf;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    private Leaf parse(Leaf leaf, final Element e) {
        final NodeList children = e.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            final Node node = children.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if(node.hasChildNodes()){
                    if(node.getNodeName().equals("leaf")){
                        leaf.getChildren().add(parse(new Leaf("", new ArrayList<Leaf>(), leaf), (Element) node));
                    }
                    else if(node.getNodeName().equals("children")){
                        leaf.setChildren(parse(new Leaf(leaf.getName(), new ArrayList<Leaf>(), leaf.getFather()), (Element) node).getChildren());
                    }
                    else if(node.getNodeName().equals("name")){
                        String name = node.getFirstChild().getNodeValue().trim();
                        if(!name.equals("")){
                            leaf.setName(name);
                        }
                    }
                }
            }
        }

        return leaf;
    }

    private void displayLeaf(Leaf leaf, int deep){
        for(int i=0; i<deep; i++){
            System.out.print("   ");
        }
        if(deep > 0){
            System.out.print("|-- ");
        }
        System.out.print(leaf.getName());
        Leaf father = leaf.getFather();
        System.out.print("(");
        for(int i=0; i<deep; i++){
            if(father!=null){
                System.out.print(" -> "+father.getName());
                father = father.getFather();
            }
        }
        System.out.println(")");
        for(Leaf l : leaf.getChildren()){
            displayLeaf(l, deep+1);
        }
    }
}
