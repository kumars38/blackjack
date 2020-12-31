import java.io.*;
import java.util.*;

public class Deck {
    
    boolean gameDeck;
    boolean hasAce;
    ArrayList<Card> cards = new ArrayList<>();
            
    public Deck (boolean gameDeck) throws IOException
    {
        if (gameDeck)
        {
            String[] suits = {"C","D","H","S"};
            String[] types = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
            
            //adds in the 52 cards for the game deck.
            for (String s:suits)
                for (String t:types)
                    add(new Card(t,s));   
        }
        // if not game deck, starts as empty deck
    }
    
    public void add(Card c)
    {
        if (c.getVal() == 1)
            hasAce=true;
        cards.add(c);
    }
    
    public int size()
    {
        return cards.size();
    }
    
    public Card get(int n)
    {
        return cards.get(n);
    }
    
    public Card pop()
    {
        Card c = cards.get(size()-1);
        cards.remove(size()-1);
        return c;
    }
    
    public int getVal()
    {
        int val=0;
        for (int i=0; i<size(); i++)
        {
            val+=get(i).getVal();
        }
        return val;
    }
    
    public void shuffle()
    {
        Collections.shuffle(cards);
    }
}
