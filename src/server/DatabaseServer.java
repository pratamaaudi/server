/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author GeolseuDei
 */
public class DatabaseServer {

    String iduser;
    String namauser;
    String tipeuser;

    public String namaHakakses;
    public String hakspesial;

    public void insertLog(String id, String event, String waktu) {
        Connection myCon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/programpendataan", "root", "");
            if (!myCon.isClosed()) {
                PreparedStatement sql = (PreparedStatement) myCon.prepareStatement(
                        "insert into log (id_anggota,event,waktu) values('" + id + "','" + event + "','" + waktu + "')"
                );

                int a = sql.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (myCon != null) {
                    myCon.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public boolean Login(String username, String password) {
        boolean status = false;
        Connection myCon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/programpendataan", "root", "");
            if (!myCon.isClosed()) {
                PreparedStatement sql = (PreparedStatement) myCon.prepareStatement(
                        "select * from login where username='" + username + "' and password='" + password + "'"
                );
                ResultSet hasil = sql.executeQuery();
                if (hasil.next()) {
                    iduser = hasil.getString("id");
                    namauser = hasil.getString("username");
                    tipeuser = hasil.getString("status");
                    status = true;
                }
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (myCon != null) {
                    myCon.close();
                }
            } catch (SQLException e) {
            }
        }
        return status;
    }

    public boolean insertKegiatan(String noinduk, String kegiatan, String tanggal) {
        boolean status = false;
        Connection myCon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/programpendataan", "root", "");
            if (!myCon.isClosed()) {
                PreparedStatement sql = (PreparedStatement) myCon.prepareStatement(
                        "insert into kegiatan (id_anggota,kegiatan,tanggal) values('" + noinduk + "','" + kegiatan + "','" + tanggal + "')"
                );

                int a = sql.executeUpdate();
                status = true;
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (myCon != null) {
                    myCon.close();
                }
            } catch (SQLException e) {
            }
        }
        return status;
    }

    public boolean CekPassword(String iduser, String passwordlamamd5) {
        boolean status = false;
        Connection myCon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/programpendataan", "root", "");
            if (!myCon.isClosed()) {
                PreparedStatement sql = (PreparedStatement) myCon.prepareStatement(
                        "select * from login where id='" + iduser + "' and password='" + passwordlamamd5 + "'"
                );
                ResultSet hasil = sql.executeQuery();
                if (hasil.next()) {
                    status = true;
                }
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (myCon != null) {
                    myCon.close();
                }
            } catch (SQLException e) {
            }
        }
        return status;
    }

    public boolean updatePassword(String iduser, String passwordbarumd5) {
        boolean status = false;
        Connection myCon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/programpendataan", "root", "");
            if (!myCon.isClosed()) {
                PreparedStatement sql = (PreparedStatement) myCon.prepareStatement(
                        "update login set password='" + passwordbarumd5 + "'where id=" + iduser
                );

                int a = sql.executeUpdate();
                status = true;
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (myCon != null) {
                    myCon.close();
                }
            } catch (SQLException e) {
            }
        }
        return status;
    }

    public ArrayList Ambil12DataLogMenuAdmin() {
        ArrayList list = new ArrayList<>();
        Connection myCon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/programpendataan", "root", "");
            if (!myCon.isClosed()) {
                PreparedStatement sql = (PreparedStatement) myCon.prepareStatement(
                        "SELECT anggota.nama,log.event,log.waktu FROM log inner join anggota on log.id_anggota=anggota.id order by log.waktu desc limit 12"
                );
                ResultSet hasil = sql.executeQuery();
                while (hasil.next()) {
                    Log l = new Log();
                    l.setNama(hasil.getString("nama"));
                    l.setEvent(hasil.getString("event"));
                    l.setWaktu(hasil.getString("waktu"));
                    list.add(l);
                }
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (myCon != null) {
                    myCon.close();
                }
            } catch (SQLException e) {
            }
        }
        return list;
    }

    public ArrayList AmbilDataLogDenganTanggal(String tanggal, String bulan, String tahun) {
        ArrayList list = new ArrayList<>();
        Connection myCon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/programpendataan", "root", "");
            if (!myCon.isClosed()) {
                PreparedStatement sql = (PreparedStatement) myCon.prepareStatement(
                        "SELECT anggota.nama,log.event,log.waktu FROM log inner join anggota on log.id_anggota=anggota.id WHERE waktu like '" + tanggal + "-" + bulan + "-" + tahun + "%' order by waktu desc"
                );
                ResultSet hasil = sql.executeQuery();
                while (hasil.next()) {
                    Log l = new Log();
                    l.setNama(hasil.getString("nama"));
                    l.setEvent(hasil.getString("event"));
                    l.setWaktu(hasil.getString("waktu"));
                    list.add(l);
                }
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (myCon != null) {
                    myCon.close();
                }
            } catch (SQLException e) {
            }
        }
        return list;
    }

    public boolean AmbilNamaDanStatusHakAksesDenganNoInduk(String noinduk) {
        boolean status = false;
        Connection myCon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/programpendataan", "root", "");
            if (!myCon.isClosed()) {
                PreparedStatement sql = (PreparedStatement) myCon.prepareStatement(
                        "SELECT anggota.nama, login.hakspesial FROM anggota inner join login on anggota.id=login.id where anggota.noinduk=" + noinduk
                );
                ResultSet hasil = sql.executeQuery();
                if (hasil.next()) {
                    namaHakakses = hasil.getString("nama");
                    hakspesial = hasil.getString("hakspesial");
                    status = true;
                }
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (myCon != null) {
                    myCon.close();
                }
            } catch (SQLException e) {
            }
        }
        return status;
    }

    public boolean updateHakAkses(String noinduk, String pilihan) {
        boolean status = false;
        String iduser = "";
        Connection myCon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/programpendataan", "root", "");
            if (!myCon.isClosed()) {
                PreparedStatement sql = (PreparedStatement) myCon.prepareStatement(
                        "SELECT login.id FROM login inner join anggota on login.id = anggota.id WHERE anggota.noinduk=" + noinduk
                );
                ResultSet hasil = sql.executeQuery();
                if (hasil.next()) {
                    iduser = hasil.getString("id");
                }
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (myCon != null) {
                    myCon.close();
                }
            } catch (SQLException e) {
            }
        }
        String beriatocabut = "";
        if (pilihan.equalsIgnoreCase("beri")) {
            beriatocabut = "1";
        } else if (pilihan.equalsIgnoreCase("cabut")) {
            beriatocabut = "0";
        }
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/programpendataan", "root", "");
            if (!myCon.isClosed()) {
                PreparedStatement sql = (PreparedStatement) myCon.prepareStatement(
                        "update login set hakspesial='" + beriatocabut + "'where id=" + iduser
                );

                int a = sql.executeUpdate();
                status = true;
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (myCon != null) {
                    myCon.close();
                }
            } catch (SQLException e) {
            }
        }
        return status;
    }
}
