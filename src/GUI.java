import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GUI extends javax.swing.JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Deck gameDeck, pDeck, dDeck;
    
    //card dimensions on GUI
    int cWidth = 90, cHeight = 120;
    
    //other init variables
    int bal = 100;
    int bet = 0;
    int pVal = 0, dVal = 0;
    boolean dealerDone,dealerStand;

    /**
     * Creates new form GUI
     */
    public GUI() throws IOException {
        initComponents();   // initialize GUI components
        init();				// initialize decks, cards, bet, button states
    }

    public void init() throws IOException {
        //set default cards to null, until they are handed out
    	gameDeckDisplay.setIcon(getImgIcon(new ImageIcon(this.getClass().getResource("img/red_back.png")).getImage(), cWidth, cHeight));
        card1.setIcon(null);
        card2.setIcon(null);
        card3.setIcon(null);
        card4.setIcon(null);
        card5.setIcon(null);
        card6.setIcon(null);
        card7.setIcon(null);
        card8.setIcon(null);
        card9.setIcon(null);
        card10.setIcon(null);
        updateText.setText("");

        pDeck = new Deck(false);
        dDeck = new Deck(false);
        bet=0;
        pVal=0;
        dVal=0;
        dealerDone=false;
        dealerStand=false;

        //initializes a new game deck and shuffles it
        gameDeck = new Deck(true);
        gameDeck.shuffle();
        balText.setText("Balance: $" + bal);
        switchButtons();
    }

    public ImageIcon getImgIcon(Image img, int w, int h) {
        Image dimg = img.getScaledInstance(w, h,
                Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);
    }

    public void configImgPlayer() throws IOException {
    	//sets card icon for the most recent player card
        int n = pDeck.size();
        Card c = pDeck.get(n - 1);
        ImageIcon i = getImgIcon(c.getImg(), cWidth, cHeight);
        if (n == 1) {
            card1.setIcon(i);
        } else if (n == 2) {
            card2.setIcon(i);
        } else if (n == 3) {
            card3.setIcon(i);
        } else if (n == 4) {
            card4.setIcon(i);
        } else if (n == 5) {
            card5.setIcon(i);
        }
    }

    public void configImgDealer(boolean firstDeal) throws IOException {
    	// if first deal, deal one card face up and one card face down
        if (firstDeal) {
            Card c = dDeck.get(0);
            ImageIcon i = getImgIcon(c.getImg(), cWidth, cHeight);
            card6.setIcon(i);
        	card7.setIcon(getImgIcon(new ImageIcon(this.getClass().getResource("img/red_back.png")).getImage(), cWidth, cHeight));
        } //otherwise set the appropriate card icon
        else {
            int size = dDeck.size();
            Card c = dDeck.get(size - 1);
            ImageIcon i = getImgIcon(c.getImg(), cWidth, cHeight);
            switch (size) {
                case 3:
                    card8.setIcon(i);
                    break;
                case 4:
                    card9.setIcon(i);
                    break;
                case 5:
                    card10.setIcon(i);
                    break;
                default:
                    break;
            }
        }
    }
    
    //returns true if player has run out of money.
    public boolean placeBet() throws IOException {
        //default bet
        int betAmount = bal > 25 ? 25 : bal;
        String betAmnt = betField.getText();    	
    	
        if (bal == 0) {
            updateText.setText("            You have no money, cannot place bet.");
            lab1.setText("");
            lab2.setText("");
            play.setEnabled(false);
            hit.setEnabled(false);
            stand.setEnabled(false);
            return true;
        }

        //if not blank bet, place entered bet
        if (!betAmnt.equals("")) {
            betAmount = Integer.parseInt(betAmnt);
            updateText.setText("              Bet of $"+betAmount+" successfully placed!");
        } else {
            updateText.setText("        No bet specified, default bet of $"+(bal > 25 ? 25 : bal)
                    +" placed.");
        }

        if (betAmount > bal) {
            updateText.setText("Your bet is higher than your balance. Max bet of $"+bal+" placed.");
        }
        bet = Math.min(betAmount, bal);
        bal -= bet;
        balText.setText("Balance: $" + bal);
        return false;
    }

    public void switchButtons() {
        play.setEnabled(!play.isEnabled());
        hit.setEnabled(!hit.isEnabled());
        stand.setEnabled(!stand.isEnabled());
    }

    public void firstDeal() throws IOException {
        //retrieve top two cards from game deck, deal to player hand
        pDeck.add(gameDeck.pop());
        pDeck.add(gameDeck.pop());

        //next two cards dealt to dealer hand
        dDeck.add(gameDeck.pop());
        dDeck.add(gameDeck.pop());

        //configure players first card
        ImageIcon i = getImgIcon(pDeck.get(0).getImg(), cWidth, cHeight);
        card1.setIcon(i);

        //sets images for player's second card, dealer's first two cards
        configImgPlayer();
        configImgDealer(true);
    }

    //NetBeans generated code - GUI components
    private void initComponents() {
    	//init layered panes where cards sit
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        
        //init all card display labels
        gameDeckDisplay = new javax.swing.JLabel();
        
        //cards 1-5 for player, 6-10 for dealer
        card1 = new javax.swing.JLabel();
        card2 = new javax.swing.JLabel();
        card3 = new javax.swing.JLabel();
        card4 = new javax.swing.JLabel();
        card5 = new javax.swing.JLabel();
        card6 = new javax.swing.JLabel();
        card7 = new javax.swing.JLabel();
        card8 = new javax.swing.JLabel();
        card9 = new javax.swing.JLabel();
        card10 = new javax.swing.JLabel();
        
        //other labels
        title = new javax.swing.JLabel();
        title.setFont(new java.awt.Font("Aharoni", 1, 24));
        title.setText("BLACKJACK");
        
        //hand label
        lab1 = new javax.swing.JLabel();
        lab1.setText("Your Hand");
        
        //dealer label
        lab2 = new javax.swing.JLabel();
        lab2.setText("Dealer's Hand");
        
        betLabel = new javax.swing.JLabel();
        betLabel.setText("Bet:");
        
        //default balance label
        balText = new javax.swing.JLabel();
        balText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        balText.setText("Balance: $100");
        
        //text field to enter bets
        betField = new javax.swing.JTextField();
        
        //label to update bet status/win/loss/draw
        updateText = new javax.swing.JLabel();
        
        //buttons for player actions
        play = new javax.swing.JButton();
        hit = new javax.swing.JButton();
        stand = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        //configure layered panes
        jLayeredPane1.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(1, java.awt.Color.white, java.awt.Color.white, java.awt.Color.darkGray, java.awt.Color.darkGray));
        jLayeredPane1.setToolTipText("");

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jLayeredPane1.setLayer(card1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(card2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(card3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(card4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(card5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(1, java.awt.Color.white, java.awt.Color.white, java.awt.Color.darkGray, java.awt.Color.darkGray));
        jLayeredPane2.setToolTipText("");

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(card10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jLayeredPane2.setLayer(card10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(card9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(card8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(card7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(card6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        //button events
        play.setText("Play");
        play.setEnabled(false);
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });

        hit.setText("Hit");
        hit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitActionPerformed(evt);
            }
        });

        stand.setText("Stand");
        stand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                standActionPerformed(evt);
            }
        });

        //configure layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lab2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(title)
                                        .addGap(116, 116, 116))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(balText)
                                                .addGap(150, 150, 150))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(hit)
                                                .addGap(65, 65, 65)
                                                .addComponent(stand)
                                                .addGap(99, 99, 99)))))
                                .addComponent(gameDeckDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLayeredPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lab1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(betLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(betField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(play, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateText, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lab2)
                            .addComponent(title))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(balText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(betLabel)
                            .addComponent(betField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(play, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hit)
                            .addComponent(stand)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(gameDeckDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(lab1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void standActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_standActionPerformed
        //player stands, now deal for dealer
    	switchButtons();
        
        try 
        {
        	//flip over second dealer card
            ImageIcon i = getImgIcon(dDeck.get(1).getImg(), cWidth, cHeight);
            card7.setIcon(i);
            
            //check if dealer gets blackjack on first 2 cards
            dVal = getHandValue(dDeck);
            dealerDone = checkDealerValue();
            
            //dealer now stands after 2 cards (dVal >= 17)
            if (!dealerDone && dVal>=17)
            {
                if (dVal > pVal)
                {
                    updateText.setText("      Dealer's hand has higher value. You lose.");
                    dealerDone=true;
                }
                else if (dVal==pVal)
                {
                    updateText.setText("     You tied the dealer. Money is returned.");
                    bal+=bet;
                    balText.setText("Balance: $"+bal);
                    dealerDone=true;
                }
            } 
        } catch (IOException e) {}
        
        //loops through while dealer is still playing (dVal < 17)
        while (!dealerDone) 
        {
            //deal a card to dealer
            dDeck.add(gameDeck.pop());
            try
            {
                configImgDealer(false);
                dVal=getHandValue(dDeck);
                dealerDone = checkDealerValue();
            } catch (IOException e) {}
            
            //dealers cards have 17 <= dVal < 21, therefore they stand
            if (!dealerDone && dealerStand)
            {
                if (dVal > pVal) 
                {
                    updateText.setText("      Dealer's hand has higher value. You lose.");
                }
                else if (dVal < pVal)
                {
                	updateText.setText("             You beat the dealer. You win.");
                    bal+=2*bet;
                    balText.setText("Balance: $"+bal);
                }
                else
                {
                    updateText.setText("         You tied the dealer. Money is returned.");
                    bal+=bet;
                    balText.setText("Balance: $"+bal);
                }
                dealerDone=true;
            }
        }
    }//GEN-LAST:event_standActionPerformed

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        //configure
        boolean done=false;
        try 
        {
            init();
            done = placeBet();
        } catch (IOException e) {}
        
        //deal cards
        if (!done)
        {
            try {
                firstDeal();
                //checks for blackjack on first 2 cards (ace+face)
                pVal = getHandValue(pDeck);
                checkPlayerValue();
                int d1val = dDeck.get(0).getVal();
                //if dealer's first card is an ace, show value as 11
                if (d1val == 1)
                    d1val=11;
                lab2.setText("Dealer's Hand: "+d1val);
            } catch (IOException e) {}    
        }

    }//GEN-LAST:event_playActionPerformed

    private void hitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitActionPerformed
        //deals card to player hand
        pDeck.add(gameDeck.pop());
        try {
            configImgPlayer();
            pVal = getHandValue(pDeck);
            checkPlayerValue();

        } catch (IOException e) {}

    }//GEN-LAST:event_hitActionPerformed

    public int getHandValue(Deck d) {
        int val = d.getVal();
        if (d.hasAce) {
            //treat ace as having 11 point value if possible
            if (val <= 11) {
                val += 10;
            }
            //otherwise ace remains as 1
        }
        return val;
    }

    public void checkPlayerValue() throws IOException {
        //went over and lost or got 21
        if (pVal >= 21) 
        {
            //reveal dealer's deck
            //second card
            ImageIcon i = getImgIcon(dDeck.get(1).getImg(), cWidth, cHeight);
            card7.setIcon(i);
            dVal = getHandValue(dDeck);
            
            //dealer plays as it would, hitting until reaching 17
            int count=2;
            while (dVal < 17)
            {
                //deal a card to dealer
                dDeck.add(gameDeck.pop());
                i = getImgIcon(dDeck.get(count).getImg(),cWidth,cHeight);
                if (count==2)
                    card8.setIcon(i);
                else if (count==3)
                    card9.setIcon(i);
                else if (count==4)
                    card10.setIcon(i);
                dVal = getHandValue(dDeck);
                count++;

            }
            lab2.setText("Dealer's Hand: "+dVal);
            
            //player and dealer tie at 21
            if (pVal == 21 && dVal == 21)
            {
                updateText.setText("         You tied the dealer. Money is returned.");
                bal += bet;
                balText.setText("Balance: $"+bal);
            }
            //otherwise, no tie, 21 is a win
            else if (pVal == 21)
            {
                updateText.setText("                  You got 21! You win.");
                bal += 2 * bet;
                balText.setText("Balance: $"+bal);
            }
            //otherwise, player busted
            else
            {
                updateText.setText("                 You went over 21. You lose.");
            }
            switchButtons();
        }
        lab1.setText("Your Hand: "+pVal);
    }
    
    public boolean checkDealerValue() throws IOException {
        lab2.setText("Dealer's Hand: "+dVal);
        if (dVal > 21)
        {
            updateText.setText("                 Dealer busted. You win.");
            bal+=bet*2;
            balText.setText("Balance: $"+bal);
            return true;
        }
        else if (dVal == 21)
        {
            updateText.setText("                  Dealer got 21. You lose.");
            return true;
        }
        else if (dVal >= 17)
        {
            dealerStand=true;
        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
    	//Set UI look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GUI().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel balText;
    private javax.swing.JTextField betField;
    private javax.swing.JLabel card1;
    private javax.swing.JLabel card2;
    private javax.swing.JLabel card3;
    private javax.swing.JLabel card4;
    private javax.swing.JLabel card5;
    private javax.swing.JLabel card6;
    private javax.swing.JLabel card7;
    private javax.swing.JLabel card8;
    private javax.swing.JLabel card9;
    private javax.swing.JLabel card10;
    private javax.swing.JLabel gameDeckDisplay;
    private javax.swing.JButton hit;
    private javax.swing.JLabel betLabel;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLabel lab1;
    private javax.swing.JLabel lab2;
    private javax.swing.JButton play;
    private javax.swing.JButton stand;
    private javax.swing.JLabel title;
    private javax.swing.JLabel updateText;
    // End of variables declaration//GEN-END:variables
}
