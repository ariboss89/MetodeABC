/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ariramadhan
 */
public class KlasifikasiDao {
    private Koneksi con;
    private Statement st;
    private String query;
    private ResultSet res;
    
    public void Save(String biayaPenolong, String biayaListrik, String biayaMesin, String mtnMesin, String pemasaran, String mtnBangunan, String biayaBangunan) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "insert into tb_klasifikasi(biaya_penolong, biaya_listrik, biaya_mesin, mtn_mesin, pemasaran, mtn_bangunan, biaya_bangunan)"
                    + "values('"+biayaPenolong+"','" + biayaListrik + "', '"+biayaMesin+"', '"+mtnMesin+"', '"+pemasaran+"', '"+mtnBangunan+"'"
                    + ",'"+biayaBangunan+"')";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data berhasil di tambahkan");
        } catch (SQLException e) {
        }
    }
    
    public void Update(String biayaPenolong, String biayaListrik, String biayaMesin, String mtnMesin, String pemasaran, String mtnBangunan, String biayaBangunan, int id) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "update tb_klasifikasi set biaya_penolong='"+biayaPenolong+"', biaya_mesin='" + biayaMesin + "',mtn_mesin= '"+mtnMesin+"', "
                    + "pemasaran='"+pemasaran+"', mtn_bangunan = '"+mtnBangunan+"', biaya_bangunan='"+biayaBangunan+"' where Id = '" + id + "'";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data berhasil di update");
        } catch (SQLException e) {

        }
    }
    
    public void Delete(int id) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "delete from tb_klasifikasi where Id = '" + id + "'";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus");
        } catch (SQLException e) {

        }
    }

    public String[][] ShowData() {

        res = null;
        String[][] data = null;
        con = new Koneksi();
        con.connect();
        int jumlahBaris = 0;
        try {
            st = con.conn.createStatement();
            query = "SELECT COUNT(Id) AS Jumlah FROM tb_klasifikasi";
            res = st.executeQuery(query);
            if (res.next()) {
                jumlahBaris = res.getInt("Jumlah");
            }
            query = "select *from tb_klasifikasi";
            res = st.executeQuery(query);
            data = new String[jumlahBaris][8];
            int r = 0;
            while (res.next()) {
                data[r][0] = res.getString("Id");
                data[r][1] = res.getString("biaya_penolong");
                data[r][2] = res.getString("biaya_listrik");
                data[r][3] = res.getString("biaya_mesin");
                data[r][4] = res.getString("mtn_mesin");
                data[r][5] = res.getString("pemasaran");
                data[r][6] = res.getString("mtn_bangunan");
                data[r][7] = res.getString("biaya_bangunan");
                
                r++;
            }
            int jmlBaris = r;
            String[][] tmpArray = data;
            data = new String[jmlBaris][8];
            for (r = 0; r < jmlBaris; r++) {
                for(int c = 0; c < 8; c++) {
                    data[r][c] = tmpArray[r][c];
                }
            }
            st.close();
            con.conn.close();
        } catch (SQLException e) {
            System.err.println("SQLException : " + e.getMessage());
        }
        return data;
    }
}
