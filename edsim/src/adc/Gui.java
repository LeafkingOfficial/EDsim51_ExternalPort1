package adc;

import java.awt.Color;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author www.edsim51.com
 */
class Gui extends JPanel {
    
    private ADC adc;

    private boolean p32set;

    public void setport(String str, Board board){

        for(int i = 0; i < 8; i++){
            board.clearPortPin(1, i);
        }
        String bs = Integer.toBinaryString(Integer.parseInt(str, 16));
        for(int i = bs.length()-1; i >= 0; i--){
            System.out.println(bs);
            if(bs.charAt(i) == '1'){ 
                board.setPortPin(1, bs.length()-i-1);
                System.out.println("Set P1." + (i));
            }
            else {
                board.clearPortPin(1, bs.length()-i-1);
                System.out.println("Clr P1." + (i));
            }
        }
    }
    
    Gui(boolean small, Board board) {
        adc = new ADC(board);
        JPanel sp = new JPanel();
     

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        JTextField port_1 = new JTextField("HEX");
        sp.add(port_1);


        //Action Listener of button
        ActionListener buttonlisten = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setport(port_1.getText(), board);
            }
        };

        gbc.gridx = 20;
        gbc.gridy = 0;
        JButton bt = new JButton("CHECK");
        bt.addActionListener(buttonlisten);
        sp.add(bt);


        this.add(sp);

   
    }
      
    void setSize(boolean small) {
        adc.getGraphics().setSize(small);
    }
    
    ADC getADC() {
        return adc;
    }
    
}