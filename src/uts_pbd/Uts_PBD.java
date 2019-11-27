/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_pbd;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import javax.swing.JOptionPane;

public class Uts_PBD {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String root     = "jdbc:mysql://localhost/uts_pemrograman_databases";
    static final String username    = "root";
    static final String password    = "";
    
   static java.sql.Connection con;
    static java.sql.Statement st;
    static ResultSet rs;
    public static String user;
    


    static void koneksi() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(root, username, password);
            st = (java.sql.Statement) con.createStatement();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    

    static void insertdata() {
        String nama = JOptionPane.showInputDialog("Nama Produk");
        String harga = JOptionPane.showInputDialog("Harga Produk");
        String sql = "insert into produk(nama_produk, harga_produk) values ('" + nama + "','" + harga + "')";
        try {
            st.executeUpdate(sql);
            System.out.println("update berhasil");
        } catch (Exception e) {
            System.out.println("update salah");
        }
    }
    

    static boolean login() {
        boolean cek;

        cek = false;
        user = JOptionPane.showInputDialog("username");
        String pass = JOptionPane.showInputDialog("password");
        String sql = "select * from login_user where username='" + user + "'and password = '" + pass + "'";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                cek = true;
            }
            return cek;
        } catch (Exception e) {
            cek = false;
            return cek;
        }
    }
    
    static void update() {
        try {
            String sqlup = "update login_user set last_login=now() where username='" + user + "'";
            st.executeUpdate(sqlup);
            
        } catch (Exception e) {
            
        }
    }
    
    static void tampildata() {
        String pil = JOptionPane.showInputDialog("pilih 1=A-Z dan 2=Z-A");
        String sql = null;
        switch(pil){
            case "1":
                sql = "select * from produk order by harga_produk asc";
                break;
            case "2":
                sql = "select * from produk order by harga_produk desc";
                break;
                
        }
        System.out.println("urut data");
        try {
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                System.out.print(rs.getString(1)+"    |");
                    System.out.print(rs.getString(2)+"    |");
                 System.out.println(rs.getString(3));
            }
        } catch (Exception e) {
            
        }
    }
    
    public static void main(String[] args) {
        koneksi();
        try {
            if (login()) {
                update();
                insertdata();
                tampildata();
            } else {
                System.out.println("gagal");
            }
        } catch (Exception e) {
            
        }
    }
    
}    
