/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ariramadhan
 */
public class Koneksi {
    public java.sql.Connection conn;

    public java.sql.Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil Koneksi ke MySQL");

            try {

                String url = "jdbc:mysql://localhost:3306/nea?"+ "autoReconnect=true&useSSL=false";

                String user = "root";

                String password = "";

                conn = DriverManager.getConnection(url, user, password);

                System.out.println("Koneksi sukses");

            } catch (SQLException sqle) {

                System.out.println("Koneksi gagal dilakukan");

            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Gagal Koneksi ke MySQL");
        }

        return conn;
    }
}
