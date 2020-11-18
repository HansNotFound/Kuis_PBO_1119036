/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import Controller.Controller;
import Model.Jurusan;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author HansNotFound
 */
public class FrameAllJurusan extends JFrame implements ActionListener{
    JFrame frameJur;
    String[][] data;
    String[] kolom = {"Kode","Nama"};
    JTable tabelJur;
    public FrameAllJurusan(){
        frameJur = new JFrame("Jurusan");
        frameJur.setSize(500, 500);
        frameJur.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameJur.setLocationRelativeTo(null);
        
        ArrayList<Jurusan> listJur = Controller.getAllJurusan();
        int panjang = listJur.size();
        data = new String[panjang][2];
        for(int i = 0; i < listJur.size() ; i++){
            data[i][0] = listJur.get(i).getKode();
            data[i][1] = listJur.get(i).getNama();
        }
        
        tabelJur = new JTable(data,kolom);
        tabelJur.setBounds(20, 20, 200, 500);
        JScrollPane sp = new JScrollPane(tabelJur);
        
        frameJur.add(sp);
        frameJur.setLayout(null);
        frameJur.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
