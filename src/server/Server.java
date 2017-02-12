/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Audi
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date d = new Date();
        String jamskrg = sdf.format(d);

        ServerSocket ss;
        int port = 8888;
        try {
            ss = new ServerSocket(port);
            System.out.println("Server started | " + jamskrg);
            while (true) {
                Socket incoming = ss.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            handleSocket(incoming);
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }).start();
            }
        } catch (IOException ex) {
            System.out.println("Server already started");
        }
    }

    private static void handleSocket(Socket incoming) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        Date d = new Date();
        String haritanggalskrg = sdf.format(d);
        //Input -> Nerima
        //Output -> Ngasi

        //Input
        BufferedReader input = new BufferedReader(new InputStreamReader(incoming.getInputStream()));

        //Output
        PrintStream output = new PrintStream(incoming.getOutputStream());

        DatabaseServer ds = new DatabaseServer();

        String jenisdata = input.readLine();
        if (jenisdata.equalsIgnoreCase("login")) {
            String username = input.readLine();
            String passwordmd5 = DigestUtils.md5Hex(input.readLine());
            if (ds.Login(username, passwordmd5)) {
                output.println(true);
                output.println(ds.iduser);
                output.println(ds.namauser);
                output.println(ds.tipeuser);
                ds.insertLog(ds.iduser, "Sukses Login ke server", haritanggalskrg);
            } else {
                output.println(false);
            }

        }
        if (jenisdata.equalsIgnoreCase("insertkegiatan")) {
            String noinduk = input.readLine();
            String kegiatan = input.readLine();
            String tanggal = input.readLine();
            if (ds.insertKegiatan(noinduk, kegiatan, tanggal)) {
                output.println(true);
                ds.insertLog(ds.iduser, "Menambahkan kegiatan ke server", haritanggalskrg);
            }
        }
        if (jenisdata.equalsIgnoreCase("gantipassword")) {
            String iduser = input.readLine();
            String passwordlamamd5 = DigestUtils.md5Hex(input.readLine());
            if (ds.CekPassword(iduser, passwordlamamd5)) {
                output.println(true);
                String passwordbarumd5 = DigestUtils.md5Hex(input.readLine());
                if (ds.updatePassword(iduser, passwordbarumd5)) {
                    output.println(true);
                    ds.insertLog(ds.iduser, "Berhasil mengganti password", haritanggalskrg);
                }
            }
        }
        if (jenisdata.equalsIgnoreCase("menulog")) {
            ArrayList<Log> list = new ArrayList<>(ds.AmbilDataLogMenuAdmin());
            int sizeArray = list.size();
            output.println(sizeArray);
            for (int i = 0; i < sizeArray; i++) {
                output.println(list.get(i).getNama());
                output.println(list.get(i).getEvent());
                output.println(list.get(i).getWaktu());
            }
        }
    }
}
