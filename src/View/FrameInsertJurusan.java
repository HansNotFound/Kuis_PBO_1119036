/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Jurusan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import Controller.Controller;
import javax.swing.JOptionPane;

/**
 *
 * @author HansNotFound
 */
public class FrameInsertJurusan extends JFrame implements ActionListener{
    JFrame frame_jurusan;
    JLabel label_kode,label_nama,label_atas;
    JTextField TF_kode,TF_nama;
    JButton button_submit,button_back,button_reset;
    public FrameInsertJurusan(){
        frame_jurusan = new JFrame("Insert Jurusan");
        frame_jurusan.setSize(400, 300);
        frame_jurusan.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame_jurusan.setLocationRelativeTo(null);
        
        label_atas = new JLabel("Silahkan Insert Data Dibawah ");
        label_atas.setBounds(100, 10, 200, 30);
        
        label_kode = new JLabel("Kode ");
        label_kode.setBounds(45, 50, 100, 30);
        
        TF_kode = new JTextField();
        TF_kode.setBounds(150, 50, 200, 30);
        
        label_nama = new JLabel("Nama ");
        label_nama.setBounds(45, 100, 100, 30);
        
        TF_nama = new JTextField();
        TF_nama.setBounds(150, 100, 200, 30);
        
        button_submit = new JButton("Submit");
        button_submit.setBounds(45,150,300,30);
        button_submit.addActionListener(this);
        
        button_back = new JButton("Back");
        button_back.setBounds(50,200,100,30);
        button_back.addActionListener(this);
        
        button_reset = new JButton("Reset");
        button_reset.setBounds(250,200,100,30);
        button_reset.addActionListener(this);
           
        frame_jurusan.add(label_atas);
        frame_jurusan.add(button_reset);
        frame_jurusan.add(button_back);
        frame_jurusan.add(button_submit);
        frame_jurusan.add(TF_kode);
        frame_jurusan.add(label_nama);
        frame_jurusan.add(label_kode);
        frame_jurusan.add(TF_nama);
        frame_jurusan.setLayout(null);
        frame_jurusan.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        switch(command){
            case"Submit":
                String kode_jur = TF_kode.getText();
                String nama_jur = TF_nama.getText();
                ArrayList<Jurusan> listJur = Controller.getAllJurusan();
                int cek_kode = Controller.cekDuplikatKode(kode_jur);
                if(kode_jur.equals("") && nama_jur.equals("")){
                    JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                } else if(cek_kode > 0){
                    JOptionPane.showMessageDialog(null, "Kode Sudah Terpakai!!", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                } else {
                    if(Controller.insertJurusan(kode_jur, nama_jur)){
                        JOptionPane.showMessageDialog(null, "Insert Berhasil!!");
                        frame_jurusan.setVisible(false);
                        new MainScreen();
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Insert gagal!!");
                        break;
                    }
                }
            case"Back":
                frame_jurusan.setVisible(false);
                new MainScreen();
                break;
            case"Reset":
                frame_jurusan.setVisible(false);
                new FrameInsertJurusan();
                break;
            default:
                break;
        }
    }
    
}
