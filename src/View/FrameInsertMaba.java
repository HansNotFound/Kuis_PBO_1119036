/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import Controller.Controller;
import Model.Jurusan;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author HansNotFound
 */
public class FrameInsertMaba extends JFrame implements ActionListener{
    JFrame frame_maba;
    JLabel label_nim,label_nama,label_angkatan, label_jurusan,label_atas;
    JTextField TF_nim,TF_nama,TF_angkatan;
    JButton button_submit,button_back,button_reset;
    JComboBox CBJurusan;
    String[] jurusan;
    public FrameInsertMaba(){
        frame_maba = new JFrame("Insert Maba");
        frame_maba.setSize(400, 500);
        frame_maba.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame_maba.setLocationRelativeTo(null);
        
        label_atas = new JLabel("Silahkan Insert Data Dibawah ");
        label_atas.setBounds(100, 10, 200, 30);
        
        label_nim = new JLabel("NIM ");
        label_nim.setBounds(45, 50, 100, 30);
        
        TF_nim = new JTextField();
        TF_nim.setBounds(150, 50, 200, 30);
        
        label_nama = new JLabel("Nama ");
        label_nama.setBounds(45, 100, 100, 30);
        
        TF_nama = new JTextField();
        TF_nama.setBounds(150, 100, 200, 30);
        
        label_angkatan = new JLabel("Angkatan ");
        label_angkatan.setBounds(45, 150, 100, 30);
        
        TF_angkatan = new JTextField();
        TF_angkatan.setBounds(150, 150, 200, 30);
        
        ArrayList<Jurusan> listJur = Controller.getAllJurusan();
        int panjang = listJur.size();
        jurusan = new String[panjang];
        for(int i = 0; i < listJur.size() ; i++){
            jurusan[i] = listJur.get(i).getKode();
        }
        
        label_jurusan = new JLabel("Jurusan ");
        label_jurusan.setBounds(45, 200, 100, 30);
        
        CBJurusan = new JComboBox(jurusan);
        CBJurusan.setBounds(150, 200, 200, 30);
        
        button_submit = new JButton("Submit");
        button_submit.setBounds(45,250,300,30);
        button_submit.addActionListener(this);
        
        button_back = new JButton("Back");
        button_back.setBounds(50,300,100,30);
        button_back.addActionListener(this);
        
        button_reset = new JButton("Reset");
        button_reset.setBounds(250,300,100,30);
        button_reset.addActionListener(this);
          
        frame_maba.add(CBJurusan);
        frame_maba.add(label_atas);
        frame_maba.add(button_reset);
        frame_maba.add(button_back);
        frame_maba.add(button_submit);
        frame_maba.add(TF_nim);
        frame_maba.add(label_nama);
        frame_maba.add(label_nim);
        frame_maba.add(TF_nama);
        frame_maba.add(label_angkatan);
        frame_maba.add(TF_angkatan);
        frame_maba.add(label_jurusan);
        frame_maba.setLayout(null);
        frame_maba.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        switch(command){
            case"Submit":
                String nama = TF_nama.getText();
                String nim = TF_nim.getText();
                int angkatan = Integer.parseInt(TF_angkatan.getText());
                int cek_nim = Controller.cekDuplikatNIM(nim);
                if(nama.equals("") && nim.equals("")){
                    JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                } else if(cek_nim > 0){
                    JOptionPane.showMessageDialog(null, "NIM sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                } else {
                    String jurusan = CBJurusan.getItemAt(CBJurusan.getSelectedIndex()).toString();
                    if(Controller.insertMABA(nim, nama, angkatan, jurusan)){
                        JOptionPane.showMessageDialog(null, "Insert Berhasil!!");
                        frame_maba.setVisible(false);
                        new MainScreen();
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Insert gagal!!");
                        break;
                    }
                }
            case"Back":
                frame_maba.setVisible(false);
                new MainScreen();
                break;
            case"Reset":
                frame_maba.setVisible(false);
                new FrameInsertMaba();
                break;
            default:
                break;
        }
    }
}
