/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Koneksi;
import Model.tb_hpp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ariramadhan
 */
public class HppDao extends tb_hpp{
    
    private Koneksi con;
    private Statement st;
    private String query;
    private ResultSet res;
    
    public void Save(String jenis, String bahanBaku, String bahanPenolong, String tkl, String totalHpp) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "insert into tb_hpp(jenis, bahan_baku, bahan_penolong, tkl, total_hpp)values('"+jenis+"','" + bahanBaku + "', '"+bahanPenolong+"', '"+tkl+"', '"+totalHpp+"')";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data berhasil di tambahkan");
        } catch (SQLException e) {
        }
    }
    
    public void Update(String jenis, String bahanBaku, String bahanPenolong, String tkl, String totalHpp, int id) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "update tb_hpp set jenis='"+jenis+"', bahan_baku='" + bahanBaku + "',bahan_penolong= '"+bahanPenolong+"', tkl='"+tkl+"', totalHpp = '"+totalHpp+"' where Id = '" + id + "'";
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
            query = "delete from tb_hpp where Id = '" + id + "'";
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
            query = "SELECT COUNT(Id) AS Jumlah FROM tb_hpp";
            res = st.executeQuery(query);
            if (res.next()) {
                jumlahBaris = res.getInt("Jumlah");
            }
            query = "select *from tb_hpp";
            res = st.executeQuery(query);
            data = new String[jumlahBaris][6];
            int r = 0;
            while (res.next()) {
                data[r][0] = res.getString("Id");
                data[r][1] = res.getString("jenis");
                data[r][2] = res.getString("bahan_baku");
                data[r][3] = res.getString("bahan_penolong");
                data[r][4] = res.getString("tkl");
                data[r][5] = res.getString("total_hpp");
                
                r++;
            }
            int jmlBaris = r;
            String[][] tmpArray = data;
            data = new String[jmlBaris][6];
            for (r = 0; r < jmlBaris; r++) {
                for(int c = 0; c < 6; c++) {
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
