/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Koneksi;
import Model.tbKlasifikasi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ariramadhan
 */
public class TarifDao extends tbKlasifikasi{
    private Koneksi con;
    private Statement st;
    private String query;
    private ResultSet res;
    
    public void Save(String jenis, String unit, String bebanUnit, String kwh, String inspeksi, 
            String produk, String luasArea, String bahanBaku, String biayaTKL, String biayaOverhead, 
            String totalHpp, String hppUnit) {
        
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "insert into tb_hasil(jenis, unit_produk, beban_unit, kwh, inspeksi, produk, luas_area, bahan_baku, biaya_tkl, biaya_overhead, total_hpp, hpp_unit)"
                    + "values('"+jenis+"','" + unit + "', '"+bebanUnit+"', '"+kwh+"', '"+inspeksi+"'"
                    + ",'"+produk+"', '"+luasArea+"','"+bahanBaku+"','"+biayaTKL+"', '"+biayaOverhead+"'"
                    + ",'"+totalHpp+"', '"+hppUnit+"')";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data berhasil di tambahkan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:"+e.toString());
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
            query = "delete from tb_hasil where Id = '" + id + "'";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus");
        } catch (SQLException e) {

        }
    }

    public ArrayList<tbKlasifikasi> ListTarif(){
       ArrayList<tbKlasifikasi> list = new ArrayList<>();
       tbKlasifikasi tbk = new tbKlasifikasi();
        
       con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "select *from tb_klasifikasi";
            res = st.executeQuery(query);
            while(res.next()){
                tbk = new tbKlasifikasi();
                
                tbk.setBiayaPenolong(res.getString("biaya_penolong"));
                tbk.setBiayaListrik(res.getString("biaya_listrik"));
                tbk.setBiayaMesin(res.getString("biaya_mesin"));
                tbk.setBiayaMtnMesin(res.getString("mtn_mesin"));
                tbk.setBiayaPemasaran(res.getString("pemasaran"));
                tbk.setBiayaBangunan(res.getString("biaya_bangunan"));
                tbk.setBiayaMtnBangunan(res.getString("mtn_bangunan"));
                
                list.add(tbk);
            }
            
            int count = list.size();
            
             return list;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data prediksi gagal di listkan");
        }
       
        return list;
    }
    
    public ArrayList<String> ListJenis(){
        ArrayList<String> dataJenis = new ArrayList<>();
        con= new Koneksi();
        try{
            st = con.connect().createStatement();
            res = st.executeQuery("SELECT *FROM tb_costdriver");
            while(res.next()){
                dataJenis.add(res.getString("jenis_lemari"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal di request !!");
        }
        
        return dataJenis;
    }
    
    public String[][] ShowData() {

        res = null;
        String[][] data = null;
        con = new Koneksi();
        con.connect();
        int jumlahBaris = 0;
        try {
            st = con.conn.createStatement();
            query = "SELECT COUNT(Id) AS Jumlah FROM tb_hasil";
            res = st.executeQuery(query);
            if (res.next()) {
                jumlahBaris = res.getInt("Jumlah");
            }
            query = "select *from tb_hasil";
            res = st.executeQuery(query);
            data = new String[jumlahBaris][4];
            int r = 0;
            while (res.next()) {
                data[r][0] = res.getString("Id");
                data[r][1] = res.getString("jenis");
                data[r][2] = res.getString("total_hpp");
                data[r][3] = res.getString("hpp_unit");
                
                r++;
            }
            int jmlBaris = r;
            String[][] tmpArray = data;
            data = new String[jmlBaris][4];
            for (r = 0; r < jmlBaris; r++) {
                for(int c = 0; c < 4; c++) {
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
