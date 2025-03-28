/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package our.project.map.graphicinterface;

import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import our.project.map.engine.Engine;
import our.project.map.utility.AlarmThread;
import our.project.map.utility.RadioThread;
import our.project.map.utility.Utility;

/**
 *
 * @author roby2
 */
public class GameUI extends javax.swing.JFrame {
     private final Engine engine = new Engine();
     
     private final Utility utility = new Utility();
          
    /**
     * Creates new form GameUI
     */
    public GameUI() { 
        initComponents();
        myInit();
        gameInterface.setVisible(false);
    }
    
    private final String INTRO = "Il tuo nome è Alan, hai 32 anni e sei uno scalatore nonché un ex-militare. Hai deciso di intraprendere "
            + "una grande avventura: la scalata del monte Everest\n\nDurante la notte, però, tu e il tuo gruppo di esplorazione siete stati "
            + "travolti da una valanga.\n\nHai perso i sensi e ti sei ritrovato in questo strano posto...\n\n";

    private final String DESCR = "Ti trovi all'interno di una grotta, sei un po' stordito ma sei riuscito a rialzarti. "
            + "Fa molto freddo qui dentro, per fortuna hai indosso parte dell'equipaggiamento da scalatore.\n\n";
    
    private void myInit(){
       
        console.setLineWrap(true);
        menuBackgraund.setIcon(new ImageIcon("./asset/img/everest_menu.jpg"));
        terminalImg.setIcon(new ImageIcon("./asset/img/terminal.jpg"));
        nuclearImg.setIcon(new ImageIcon("./asset/img/alert.png"));
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameMenu = new javax.swing.JPanel();
        menuButtonContainer = new javax.swing.JPanel();
        newBtn = new javax.swing.JButton();
        loadBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        menuBackgraund = new javax.swing.JLabel();
        gameInterface = new javax.swing.JPanel();
        comands = new javax.swing.JTextField();
        scrollConsole = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        terminalImg = new javax.swing.JLabel();
        nuclearImg = new javax.swing.JLabel();
        oBtn = new javax.swing.JButton();
        nBtn = new javax.swing.JButton();
        sBtn = new javax.swing.JButton();
        eBtn = new javax.swing.JButton();
        commandsControl = new javax.swing.JButton();
        abandonBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        inventoryBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(692, 700));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        gameMenu.setBackground(new java.awt.Color(0, 0, 0));
        gameMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuButtonContainer.setBackground(new java.awt.Color(0, 0, 0,80));

        newBtn.setBackground(new java.awt.Color(0, 0, 0));
        newBtn.setForeground(new java.awt.Color(255, 255, 255));
        newBtn.setText("Nuova partita");
        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });

        loadBtn.setBackground(new java.awt.Color(0, 0, 0));
        loadBtn.setForeground(new java.awt.Color(255, 255, 255));
        loadBtn.setText("Carica partita");
        loadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBtnActionPerformed(evt);
            }
        });

        exitBtn.setBackground(new java.awt.Color(0, 0, 0));
        exitBtn.setForeground(new java.awt.Color(255, 255, 255));
        exitBtn.setText("Esci");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuButtonContainerLayout = new javax.swing.GroupLayout(menuButtonContainer);
        menuButtonContainer.setLayout(menuButtonContainerLayout);
        menuButtonContainerLayout.setHorizontalGroup(
            menuButtonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuButtonContainerLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(menuButtonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        menuButtonContainerLayout.setVerticalGroup(
            menuButtonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuButtonContainerLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(newBtn)
                .addGap(18, 18, 18)
                .addComponent(loadBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addComponent(exitBtn)
                .addGap(14, 14, 14))
        );

        gameMenu.add(menuButtonContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 270, 340));

        menuBackgraund.setBackground(new java.awt.Color(255, 255, 255));
        menuBackgraund.setForeground(new java.awt.Color(255, 255, 255));
        gameMenu.add(menuBackgraund, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 692, 709));

        getContentPane().add(gameMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        gameInterface.setBackground(new java.awt.Color(0, 0, 0));
        gameInterface.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gameInterface.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comands.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comandsActionPerformed(evt);
            }
        });
        gameInterface.add(comands, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 470, 50));

        scrollConsole.setBackground(new java.awt.Color(0, 0, 0, 100));

        console.setEditable(false);
        console.setBackground(new java.awt.Color(0, 0, 0));
        console.setColumns(20);
        console.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        console.setForeground(new java.awt.Color(102, 204, 0));
        console.setRows(5);
        console.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        scrollConsole.setViewportView(console);

        gameInterface.add(scrollConsole, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 640, 300));
        gameInterface.add(terminalImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 370));
        gameInterface.add(nuclearImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, -1, -1));

        oBtn.setText("O");
        oBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oBtnActionPerformed(evt);
            }
        });
        gameInterface.add(oBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 60, 110));

        nBtn.setText("N");
        nBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nBtnActionPerformed(evt);
            }
        });
        gameInterface.add(nBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 60, 50));

        sBtn.setText("S");
        sBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sBtnActionPerformed(evt);
            }
        });
        gameInterface.add(sBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 60, 50));

        eBtn.setText("E");
        eBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eBtnActionPerformed(evt);
            }
        });
        gameInterface.add(eBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 60, 110));

        commandsControl.setText("Invia");
        commandsControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commandsControlActionPerformed(evt);
            }
        });
        gameInterface.add(commandsControl, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 590, 170, 50));

        abandonBtn.setText("Abbandona");
        abandonBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abandonBtnActionPerformed(evt);
            }
        });
        gameInterface.add(abandonBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, 160, -1));

        saveBtn.setText("Salva");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        gameInterface.add(saveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 450, 160, -1));

        inventoryBtn.setText("Inventario");
        inventoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventoryBtnActionPerformed(evt);
            }
        });
        gameInterface.add(inventoryBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 490, 160, -1));

        getContentPane().add(gameInterface, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 700));

        setBounds(0, 0, 701, 709);
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
       System.exit(0);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void oBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oBtnActionPerformed
        nextAction("Ovest");
    }//GEN-LAST:event_oBtnActionPerformed

    private void nBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nBtnActionPerformed
        nextAction("Nord");
    }//GEN-LAST:event_nBtnActionPerformed

    private void sBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sBtnActionPerformed
        nextAction("Sud");
    }//GEN-LAST:event_sBtnActionPerformed

    private void eBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eBtnActionPerformed
        nextAction("Est");
    }//GEN-LAST:event_eBtnActionPerformed

    private void commandsControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commandsControlActionPerformed
        String currentCommand = comands.getText().toLowerCase();
        printVideoCommand(currentCommand);
        comands.setText("");
        
        String response = parseCommand(currentCommand);
        printVideo(response);
        
    }//GEN-LAST:event_commandsControlActionPerformed

    private void abandonBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abandonBtnActionPerformed

        gameInterface.setVisible(false);
        gameMenu.setVisible(true);
        console.setText(null);
        engine.clear();
        closeThreads();
    }//GEN-LAST:event_abandonBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
         printVideo(engine.saveGame());
    }//GEN-LAST:event_saveBtnActionPerformed

    private void comandsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comandsActionPerformed
        
    }//GEN-LAST:event_comandsActionPerformed

    private void inventoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryBtnActionPerformed
        nextAction("inventario");
    }//GEN-LAST:event_inventoryBtnActionPerformed

    private void newBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBtnActionPerformed
        gameMenu.setVisible(false);
        gameInterface.setVisible(true);
        console.setText(INTRO);
        engine.startNewGame();
        showInput();
        console.append(DESCR);
    }//GEN-LAST:event_newBtnActionPerformed

    private void loadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBtnActionPerformed
        if(utility.notEmptyFile()){
            gameMenu.setVisible(false);
            gameInterface.setVisible(true);
            engine.loadGame();
            showInput();
            nextAction("guarda");
        }else{
           JOptionPane.showMessageDialog(null,
            "I file di salvataggio sono vuoti, iniziare una nuova partita.",
            "ATTENZIONE",
            JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_loadBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abandonBtn;
    private javax.swing.JTextField comands;
    private javax.swing.JButton commandsControl;
    private javax.swing.JTextArea console;
    private javax.swing.JButton eBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JPanel gameInterface;
    private javax.swing.JPanel gameMenu;
    private javax.swing.JButton inventoryBtn;
    private javax.swing.JButton loadBtn;
    private javax.swing.JLabel menuBackgraund;
    private javax.swing.JPanel menuButtonContainer;
    private javax.swing.JButton nBtn;
    private javax.swing.JButton newBtn;
    private javax.swing.JLabel nuclearImg;
    private javax.swing.JButton oBtn;
    private javax.swing.JButton sBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JScrollPane scrollConsole;
    private javax.swing.JLabel terminalImg;
    // End of variables declaration//GEN-END:variables


    
    private void printVideoCommand(String currentCommand) {                                       
        console.append("--> ");
        console.append(currentCommand);
        console.append("\n");
        console.append("\n");
    }    
     
    private void printVideo(String currentCommand) {                                       
        console.append(currentCommand);
        console.append("\n");
        console.append("\n");
    }  
    
    private void nextAction(String currentCommand) {     
        printVideoCommand(currentCommand);
        comands.setText("");
        
        String response = parseCommand(currentCommand);
       
        printVideo(response);
        
    }
    
    private String parseCommand(String currentCommand) {                                       
        String response = engine.processingInput(currentCommand);
        
        String[] message = response.split("#");
        
        if(message.length > 1 ){
            hideInput();
        }
        return message[0];
    }   
    
    private void hideInput() {                                       
       comands.setEnabled(false);
       commandsControl.setEnabled(false);
       saveBtn.setEnabled(false);
       inventoryBtn.setEnabled(false);
       oBtn.setEnabled(false);
       sBtn.setEnabled(false);
       eBtn.setEnabled(false);
       nBtn.setEnabled(false);
    }   

    private void showInput() {                                       
       comands.setEnabled(true);
       commandsControl.setEnabled(true);
       saveBtn.setEnabled(true);
       inventoryBtn.setEnabled(true);
       oBtn.setEnabled(true);
       sBtn.setEnabled(true);
       eBtn.setEnabled(true);
       nBtn.setEnabled(true);
    }
    
    private void closeThreads() {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for (Thread t : threadSet){
            if (t.getClass().equals(RadioThread.class)){
                System.out.println("Thread radio interrotto");
                RadioThread rt = (RadioThread) t;
                rt.doStop();
            }
            
            if (t.getClass().equals(AlarmThread.class)){
                System.out.println("thread allarme interrotto");
                AlarmThread at = (AlarmThread) t;
                at.doStop();
            }
        }
    }

    /**
     * @param args the command line arguments
    */
    public static void main(String[] args) {
          new GameUI().setVisible(true);
    }

    

}
