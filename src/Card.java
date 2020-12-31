import java.awt.Image;
import java.io.*;
import javax.swing.ImageIcon;

public class Card {
    int val;
    String type;
    String suit;
    Image img;

    public Card(String type, String suit) throws IOException
    {
        this.type=type;
        this.suit=suit;
        img = getImg();
    }
    
    public Image getImg() throws IOException
    {
        String path = type+suit+".png";
        Image image = new ImageIcon(this.getClass().getResource("img/"+path)).getImage();
        return image;
    }
    
    //returns value of card
    public int getVal()
    {
        if (type.equals("A"))
            val = 1;
        else if (type.equals("J") || type.equals("Q") || type.equals("K"))
            val = 10;
        else
            val = Integer.parseInt(type);
        return val;
    }
}
