/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Koneksi;
import Model.tb_costdriver;
import Model.tb_hpp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ariramadhan
 */
public class CostDriverDao extends tb_costdriver {
    private Koneksi con;
    private Statement st;
    private String query;
    private ResultSet res;
    
    public void Save(String jenis, int unit, String kwh, String inspeksi, String luas) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "insert into tb_costdriver(jenis_lemari, jumlah_unit, total_kwh, total_inspeksi, luas_area)values('"+jenis+"','" + unit + "', '"+kwh+"', '"+inspeksi+"', '"+luas+"')";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data berhasil di tambahkan");
        } catch (SQLException e) {
        }
    }
    
    public void Update(String jenis, int unit, String kwh, String inspeksi, String luas, int id) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "update tb_costdriver set jenis_lemari='"+jenis+"', jumlah_unit='" + unit + "',total_kwh= '"+kwh+"', total_inspeksi='"+inspeksi+"', luas_area = '"+luas+"' where Id = '" + id + "'";
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
            query = "delete from tb_costdriver where Id = '" + id + "'";
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
            query = "SELECT COUNT(Id) AS Jumlah FROM tb_costdriver";
            res = st.executeQuery(query);
            if (res.next()) {
                jumlahBaris = res.getInt("Jumlah");
            }
            query = "select *from tb_costdriver";
            res = st.executeQuery(query);
            data = new String[jumlahBaris][6];
            int r = 0;
            while (res.next()) {
                data[r][0] = res.getString("Id");
                data[r][1] = res.getString("jenis_lemari");
                data[r][2] = res.getString("jumlah_unit");
                data[r][3] = res.getString("total_kwh");
                data[r][4] = res.getString("total_inspeksi");
                data[r][5] = res.getString("luas_area");
                
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
