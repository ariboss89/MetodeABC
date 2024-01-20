/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ariramadhan
 */
public class tb_hpp {
    private int Id;
    private String bahan_baku;
    private String bahan_penolong;
    private String tkl;
    private String total_hpp;
    private String jenis;

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getBahan_baku() {
        return bahan_baku;
    }

    public void setBahan_baku(String bahan_baku) {
        this.bahan_baku = bahan_baku;
    }

    public String getBahan_penolong() {
        return bahan_penolong;
    }

    public void setBahan_penolong(String bahan_penolong) {
        this.bahan_penolong = bahan_penolong;
    }

    public String getTkl() {
        return tkl;
    }

    public void setTkl(String tkl) {
        this.tkl = tkl;
    }

    public String getTotal_hpp() {
        return total_hpp;
    }

    public void setTotal_hpp(String total_hpp) {
        this.total_hpp = total_hpp;
    }
    
    
}
