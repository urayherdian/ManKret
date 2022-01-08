/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManKret;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.menu.framelogin;

public class Kasir extends javax.swing.JFrame {

    /**
     * Creates new form Kasir
     */
    
    //-----------------------------------------PRINT-----------------------------------------//
    
    public class BillPrintable implements Printable {
    public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) throws PrinterException {    
      int r= itemName.size();
      ImageIcon icon=new ImageIcon(""); 
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    
            double width = pageFormat.getImageableWidth();                               
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 



          //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
        
        try{
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
           // int headerRectHeighta=40;
            
                
            g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
            g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);y+=yShift+30;
            g2d.drawString("-------------------------------------",12,y);y+=yShift;
            g2d.drawString("              Mankret                ",12,y);y+=yShift;
            g2d.drawString("            Kelompok 6               ",12,y);y+=yShift;
            g2d.drawString("             IT-43-03                ",12,y);y+=yShift;
            g2d.drawString("                                     ",12,y);y+=yShift;
            g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;

            g2d.drawString(" Item Name                   Price   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
     
            for(int s=0; s<r; s++)
            {
            g2d.drawString(" "+itemName.get(s)+"                            ",10,y);y+=yShift;
            g2d.drawString("      "+quantity.get(s)+" * Rp."+itemPrice.get(s),10,y); g2d.drawString(" Rp."+subtotal.get(s),160,y);y+=yShift;

            }
          
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Total amount:               Rp."+total1.get(0)+"   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Cash        :               Rp."+bayar1.get(0)+"   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Change      :               Rp."+kembalian1.get(0)+"   ",10,y);y+=yShift;
  
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("          THANK YOU COME AGAIN        ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("          SOFTWARE BY:ManKret       ",10,y);y+=yShift;   
           

    }
    catch(Exception e){
    e.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
   }
    public PageFormat getPageFormat(PrinterJob pj)
    {
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double bodyHeight = bHeight;  
    double headerHeight = 5.0;                  
    double footerHeight = 5.0;        
    double width = cm_to_pp(8); 
    double height = cm_to_pp(headerHeight+bodyHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(0,10,width,height - cm_to_pp(1));  
            
    pf.setOrientation(PageFormat.PORTRAIT);  
    pf.setPaper(paper);    

    return pf;
    }
    
    protected static double cm_to_pp(double cm)
    {
            return toPPI(cm * 0.393600787);
    }
 
protected static double toPPI(double inch)
    {
            return inch * 72d;
    }
    //-----------------------------------------PRINT-----------------------------------------//
    
    String Tanggal;
    private DefaultTableModel model;
    private DefaultTableModel model2;
    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<String> quantity = new ArrayList<>();
    ArrayList<String> itemPrice = new ArrayList<>();
    ArrayList<String> subtotal = new ArrayList<>();
    ArrayList<String> total1 = new ArrayList<>();
    ArrayList<String> bayar1 = new ArrayList<>();
    ArrayList<String> kembalian1 = new ArrayList<>();
    Double bHeight=0.0;
    int total, bayar, kembalian;
    
    
    
    private void back(){
        txNamaBarang.setBackground(new java.awt.Color(0,0,0,1));
        txIDBarang.setBackground(new java.awt.Color(0,0,0,1));
        txBayar.setBackground(new java.awt.Color(0,0,0,1));
        txIDCustomer.setBackground(new java.awt.Color(0,0,0,1));
        txNoTransaksi.setBackground(new java.awt.Color(0,0,0,1));
        txNamaCustomer.setBackground(new java.awt.Color(0,0,0,1));
        txNoTransaksi.setBackground(new java.awt.Color(0,0,0,1));
        txHarga.setBackground(new java.awt.Color(0,0,0,1));
        txJumlah.setBackground(new java.awt.Color(0,0,0,1));
        txTampil.setBackground(new java.awt.Color(0,0,0,1));
        txIDBarang1.setBackground(new java.awt.Color(0,0,0,1));
        txNamaBarang1.setBackground(new java.awt.Color(0,0,0,1));
        txHargaBarang1.setBackground(new java.awt.Color(0,0,0,1));
        txStok1.setBackground(new java.awt.Color(0,0,0,1));
        txPencarian1.setBackground(new java.awt.Color(0,0,0,1));
    }
    
    private void ClickKasir(){
        //---------Kasir---------//
        back();
        Kasir1.setVisible(false);
        Kasir.setVisible(true);
        DataBarang.setVisible(false);
        DataBarang1.setVisible(true);
        KasirBack.setVisible(true);
        txTanggal.setVisible(true);
        txNoTransaksi.setVisible(true);
        txIDCustomer.setVisible(true);
        txNamaCustomer.setVisible(true);
        txIDBarang.setVisible(true);
        txNamaBarang.setVisible(true);
        txHarga.setVisible(true);
        txJumlah.setVisible(true);
        txBayar.setVisible(true);
        txKembalian.setVisible(true);
        txTampil.setVisible(true);
        txTampil.setVisible(true);
        jTable1.setVisible(true);
        jScrollPane1.setVisible(true);
        btnTambah.setVisible(true);
        btnHapus.setVisible(true);
        btnSimpan.setVisible(true);
        //---------Kasir---------//
        
        //---------DataBarang---------//
        DataBarangBack1.setVisible(false);
        jScrollPaneDataBarang.setVisible(false);
        jTableDataBarang.setVisible(false);
        txIDBarang1.setVisible(false);
        txNamaBarang1.setVisible(false);
        cbJenis1.setVisible(false);
        txHargaBarang1.setVisible(false);
        txStok1.setVisible(false);
        txPencarian1.setVisible(false);
        btnSimpan1.setVisible(false);
        btnEdit1.setVisible(false);
        btnHapus1.setVisible(false);
        btnBatal1.setVisible(false);
        //---------DataBarang---------//
    }
    
    private void ClickDataBarang(){
        //---------Kasir---------//
        back();
        Kasir1.setVisible(true);
        Kasir.setVisible(false);
        DataBarang.setVisible(true);
        DataBarang1.setVisible(false);
        DataBarangBack1.setVisible(true);
        KasirBack.setVisible(false);
        txTanggal.setVisible(false);
        txNoTransaksi.setVisible(false);
        txIDCustomer.setVisible(false);
        txNamaCustomer.setVisible(false);
        txIDBarang.setVisible(false);
        txNamaBarang.setVisible(false);
        txHarga.setVisible(false);
        txJumlah.setVisible(false);
        txBayar.setVisible(false);
        txKembalian.setVisible(false);
        txTampil.setVisible(false);
        txTampil.setVisible(false);
        jTable1.setVisible(false);
        jScrollPane1.setVisible(false);
        btnTambah.setVisible(false);
        btnHapus.setVisible(false);
        btnSimpan.setVisible(false);
        //---------Kasir---------//
        
        //---------DataBarang---------//
        DataBarangBack1.setVisible(true);
        jScrollPaneDataBarang.setVisible(true);
        jTableDataBarang.setVisible(true);
        txIDBarang1.setVisible(true);
        txNamaBarang1.setVisible(true);
        cbJenis1.setVisible(true);
        txHargaBarang1.setVisible(true);
        txStok1.setVisible(true);
        txPencarian1.setVisible(true);
        btnSimpan1.setVisible(true);
        btnEdit1.setVisible(true);
        btnHapus1.setVisible(true);
        btnBatal1.setVisible(true);
        //---------DataBarang---------//
    }
    
    //-----------------------------------------------kasir----------------------------------------------------//
    public void totalBiaya(){
        int jumlahBaris = jTable1.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(jTable1.getValueAt(i, 3).toString());
            hargaBarang = Integer.parseInt(jTable1.getValueAt(i, 4).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        txTotalBayar.setText(String.valueOf(totalBiaya));
        txTampil.setText("Rp "+ totalBiaya +",00");
    }
    
    private void autonumber(){
        try {
            Connection c = KasirJava.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM penjualan ORDER BY NoFaktur DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String NoFaktur = r.getString("NoFaktur").substring(2);
                String TR = "" +(Integer.parseInt(NoFaktur)+1);
                String Nol = "";
                
                if(TR.length()==1)
                {Nol = "000";}
                else if(TR.length()==2)
                {Nol = "00";}
                else if(TR.length()==3)
                {Nol = "0";}
                else if(TR.length()==4)
                {Nol = "";}
                txNoTransaksi.setText("TR" + Nol + TR);
            } else {
                txNoTransaksi.setText("TR0001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("autonumber error");
        }
    }
    
    public void loadData(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{
            txNoTransaksi.getText(),
            txIDBarang.getText(),
            txNamaBarang.getText(),
            txJumlah.getText(),
            txHarga.getText(),
            txTotalBayar.getText()
        });
    }
    
    public void kosong(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        while(model.getRowCount() > 0){
            model.removeRow(0);
        }
    }
    
    public void utama(){
        txNoTransaksi.setText("");
        txIDBarang.setText("");
        txNamaBarang.setText("");
        txHarga.setText("");
        txJumlah.setText("");
        autonumber();
    }
    
    public void clear(){
        txIDCustomer.setText("");
        txNamaCustomer.setText("");
        txTotalBayar.setText("0");
        txBayar.setText("0");
        txKembalian.setText("0");
        txTampil.setText("0");
    
    }
    
    public void clear2(){
        txIDBarang.setText("");
        txNamaBarang.setText("");
        txHarga.setText("");
        txJumlah.setText("");
    }
    
    public void tambahTransaksi(){
        int jumlah, harga, total;
        
        jumlah = Integer.valueOf(txJumlah.getText());
        harga = Integer.valueOf(txHarga.getText());
        total = jumlah * harga;
        
        txTotalBayar.setText(String.valueOf(total));
        
        loadData();
        totalBiaya();
        clear2();
        txIDBarang.requestFocus();
    }
    //------------------------------------------------------------------------kasir---------------------------------------//
    
    //--------------------------------------------DataBarang-------------------------------------------------//
        private void autonumber2(){
        try {
            Connection c = KasirJava.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM barang ORDER BY ID_Barang DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String NoFaktur = r.getString("ID_Barang").substring(2);
                String BR = "" +(Integer.parseInt(NoFaktur)+1);
                String Nol = "";
                
                if (BR.length()==1) 
                    {Nol = "00";}
                else if(BR.length()==2)
                    {Nol = "0";}
                else if(BR.length()==3)
                    {Nol = "";}
                
                txIDBarang1.setText("BR" + Nol + BR);  
            }else{
                txIDBarang1.setText("BR001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("autonumber error");
        }
    }
    
    public void loadData2(){
        model2.getDataVector().removeAllElements();
        model2.fireTableDataChanged();
        
        try{
            Connection c = KasirJava.getKoneksi();
            Statement s = c.createStatement();
            
            String sql = "SELECT * FROM barang";
            ResultSet r = s.executeQuery(sql);
            
            while(r.next()){
                Object[] o = new Object[5];
                o [0] = r.getString("ID_Barang");
                o [1] = r.getString("Nama_Barang");
                o [2] = r.getString("Jenis_Barang");
                o [3] = r.getString("HargaBarang");
                o [4] = r.getString("Stok");
                model2.addRow(o);
            }
            r.close();
            s.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void cariData2(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Barang");
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Jenis");
        tabel.addColumn("Harga Barang");
        tabel.addColumn("Stok");
        
        try{
            Connection c = KasirJava.getKoneksi();
            String sql = "SELECT * FROM barang WHERE ID_Barang LIKE '%" + txPencarian1.getText() + "%'" + "OR Nama_Barang LIKE '%" + txPencarian1.getText() + "%'";
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                });
            }
            jTableDataBarang.setModel(tabel);
            loadData2();
        }catch(Exception e){
            System.out.println("CARI DATA ERROR");
        }finally{
        
        }
    }
    
    public void clear3(){
        txNamaBarang.setText("");
        txHargaBarang1.setText("");
        txStok1.setText("");
    }
    //------------------------------------------------------------------------------DataBarang-------------------------------------------------//
    public Kasir() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        back();
        ClickKasir();
        
        //--------Kasir--------//
        model = new DefaultTableModel();
        jTable1.setModel(model);
        
        model.addColumn("No Transaksi");
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Harga");
        model.addColumn("Total");
        
        utama();
        
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        
        txTanggal.setText(s.format(date));
        txTotalBayar.setText("0");
        txBayar.setText("0");
        txKembalian.setText("0");
        txIDCustomer.requestFocus();
        
        
        //--------DataBarang--------//
        model2 = new DefaultTableModel();
        
        jTableDataBarang.setModel(model2);
        
        model2.addColumn("ID Barang");
        model2.addColumn("Nama Barang");
        model2.addColumn("Jenis");
        model2.addColumn("Harga Barang");
        model2.addColumn("Stok");
        
        loadData2();
        autonumber2();
        btnHapus1.setEnabled(false);
        btnBatal1.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exit = new javax.swing.JLabel();
        btnLogout = new javax.swing.JLabel();
        btnBatal1 = new javax.swing.JLabel();
        btnHapus1 = new javax.swing.JLabel();
        btnEdit1 = new javax.swing.JLabel();
        btnSimpan1 = new javax.swing.JLabel();
        jScrollPaneDataBarang = new javax.swing.JScrollPane();
        jTableDataBarang = new javax.swing.JTable();
        txIDBarang1 = new javax.swing.JTextField();
        txNamaBarang1 = new javax.swing.JTextField();
        cbJenis1 = new javax.swing.JComboBox<>();
        txPencarian1 = new javax.swing.JTextField();
        txHargaBarang1 = new javax.swing.JTextField();
        txStok1 = new javax.swing.JTextField();
        DataBarangBack1 = new javax.swing.JLabel();
        DataBarang1 = new javax.swing.JLabel();
        Kasir1 = new javax.swing.JLabel();
        DataBarang = new javax.swing.JLabel();
        Kasir = new javax.swing.JLabel();
        menu = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JLabel();
        btnHapus = new javax.swing.JLabel();
        btnTambah = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txTanggal = new javax.swing.JTextField();
        txNoTransaksi = new javax.swing.JTextField();
        txIDCustomer = new javax.swing.JTextField();
        txNamaCustomer = new javax.swing.JTextField();
        txIDBarang = new javax.swing.JTextField();
        txNamaBarang = new javax.swing.JTextField();
        txHarga = new javax.swing.JTextField();
        txJumlah = new javax.swing.JTextField();
        txBayar = new javax.swing.JTextField();
        txKembalian = new javax.swing.JTextField();
        txTampil = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        KasirBack = new javax.swing.JLabel();
        txTotalBayar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.0F);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnX.png"))); // NOI18N
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, -1, -1));

        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogoutMouseClicked(evt);
            }
        });
        getContentPane().add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, 120, 50));

        btnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnBatal.png"))); // NOI18N
        btnBatal1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBatal1MouseClicked(evt);
            }
        });
        getContentPane().add(btnBatal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 290, -1, -1));

        btnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnHapus_1.png"))); // NOI18N
        btnHapus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapus1MouseClicked(evt);
            }
        });
        getContentPane().add(btnHapus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 290, -1, -1));

        btnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnEdit.png"))); // NOI18N
        btnEdit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEdit1MouseClicked(evt);
            }
        });
        getContentPane().add(btnEdit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 290, -1, -1));

        btnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnSimpan2.png"))); // NOI18N
        btnSimpan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpan1MouseClicked(evt);
            }
        });
        getContentPane().add(btnSimpan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, -1, -1));

        jTableDataBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableDataBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDataBarangMouseClicked(evt);
            }
        });
        jScrollPaneDataBarang.setViewportView(jTableDataBarang);

        getContentPane().add(jScrollPaneDataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, 1020, 320));

        txIDBarang1.setFont(new java.awt.Font("Bahnschrift", 0, 17)); // NOI18N
        txIDBarang1.setBorder(null);
        txIDBarang1.setDoubleBuffered(true);
        txIDBarang1.setOpaque(false);
        txIDBarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txIDBarang1ActionPerformed(evt);
            }
        });
        getContentPane().add(txIDBarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 230, 20));

        txNamaBarang1.setFont(new java.awt.Font("Bahnschrift", 0, 17)); // NOI18N
        txNamaBarang1.setBorder(null);
        txNamaBarang1.setDoubleBuffered(true);
        txNamaBarang1.setOpaque(false);
        txNamaBarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNamaBarang1ActionPerformed(evt);
            }
        });
        getContentPane().add(txNamaBarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 230, 30));

        cbJenis1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Makanan", "Minuman", "Perlengkapan" }));
        getContentPane().add(cbJenis1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 240, -1));

        txPencarian1.setFont(new java.awt.Font("Bahnschrift", 0, 17)); // NOI18N
        txPencarian1.setBorder(null);
        txPencarian1.setDoubleBuffered(true);
        txPencarian1.setOpaque(false);
        txPencarian1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txPencarian1ActionPerformed(evt);
            }
        });
        txPencarian1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txPencarian1KeyTyped(evt);
            }
        });
        getContentPane().add(txPencarian1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, 230, 30));

        txHargaBarang1.setFont(new java.awt.Font("Bahnschrift", 0, 17)); // NOI18N
        txHargaBarang1.setBorder(null);
        txHargaBarang1.setDoubleBuffered(true);
        txHargaBarang1.setOpaque(false);
        txHargaBarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txHargaBarang1ActionPerformed(evt);
            }
        });
        getContentPane().add(txHargaBarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 190, 230, 20));

        txStok1.setFont(new java.awt.Font("Bahnschrift", 0, 17)); // NOI18N
        txStok1.setBorder(null);
        txStok1.setDoubleBuffered(true);
        txStok1.setOpaque(false);
        txStok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txStok1ActionPerformed(evt);
            }
        });
        getContentPane().add(txStok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 230, 240, 30));

        DataBarangBack1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Data barang.png"))); // NOI18N
        getContentPane().add(DataBarangBack1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, -1, 720));

        DataBarang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnDataBarang2.png"))); // NOI18N
        DataBarang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataBarang1MouseClicked(evt);
            }
        });
        getContentPane().add(DataBarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 240, -1));

        Kasir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnKasir2.png"))); // NOI18N
        Kasir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Kasir1MouseClicked(evt);
            }
        });
        getContentPane().add(Kasir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 240, -1));

        DataBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnDataBarang.png"))); // NOI18N
        getContentPane().add(DataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 240, -1));

        Kasir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnKasir.png"))); // NOI18N
        Kasir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KasirMouseClicked(evt);
            }
        });
        getContentPane().add(Kasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 240, -1));

        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Menu.png"))); // NOI18N
        getContentPane().add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 720));

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnSimpan.png"))); // NOI18N
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 630, -1, -1));

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnHapus.png"))); // NOI18N
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusMouseClicked(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 640, -1, -1));

        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnTambah.png"))); // NOI18N
        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahMouseClicked(evt);
            }
        });
        getContentPane().add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 330, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 1010, 230));

        txTanggal.setEnabled(false);
        getContentPane().add(txTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 250, -1));

        txNoTransaksi.setEnabled(false);
        getContentPane().add(txNoTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 182, 250, 30));

        txIDCustomer.setFont(new java.awt.Font("Bahnschrift", 0, 17)); // NOI18N
        txIDCustomer.setBorder(null);
        getContentPane().add(txIDCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 240, 30));

        txNamaCustomer.setFont(new java.awt.Font("Bahnschrift", 0, 17)); // NOI18N
        txNamaCustomer.setBorder(null);
        getContentPane().add(txNamaCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 240, 40));

        txIDBarang.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        txIDBarang.setBorder(null);
        getContentPane().add(txIDBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, 160, 30));

        txNamaBarang.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        txNamaBarang.setBorder(null);
        getContentPane().add(txNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 340, 180, 30));

        txHarga.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        txHarga.setBorder(null);
        getContentPane().add(txHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 340, 180, 30));

        txJumlah.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        txJumlah.setBorder(null);
        txJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txJumlahActionPerformed(evt);
            }
        });
        getContentPane().add(txJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 340, 100, 30));

        txBayar.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        txBayar.setBorder(null);
        txBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txBayarActionPerformed(evt);
            }
        });
        getContentPane().add(txBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 656, 230, 20));

        txKembalian.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        txKembalian.setEnabled(false);
        getContentPane().add(txKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 652, 240, 30));

        txTampil.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        txTampil.setText("Rp. 0");
        getContentPane().add(txTampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, 490, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tombol Cari.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, -1, -1));

        KasirBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Kasir.png"))); // NOI18N
        getContentPane().add(KasirBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, -1, 720));

        txTotalBayar.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        txTotalBayar.setEnabled(false);
        getContentPane().add(txTotalBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 232, 380, 60));

        setSize(new java.awt.Dimension(1274, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        for(double i = 0.0; i <= 1.0; i = i + 0.1){
            String val = i + "";
            float f = Float.valueOf(val);
            this.setOpacity(f);
            try{
                Thread.sleep(50);
            }catch(Exception e){
            
            }
        }
    }//GEN-LAST:event_formWindowOpened

    private void KasirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KasirMouseClicked
        // TODO add your handling code here:
        Kasir1.setVisible(false);
        Kasir.setVisible(true);
        DataBarang.setVisible(false);
        DataBarang1.setVisible(true);
    }//GEN-LAST:event_KasirMouseClicked

    private void DataBarang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataBarang1MouseClicked
        // TODO add your handling code here:
        ClickDataBarang();
    }//GEN-LAST:event_DataBarang1MouseClicked

    private void Kasir1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kasir1MouseClicked
        // TODO add your handling code here:
        ClickKasir();
    }//GEN-LAST:event_Kasir1MouseClicked

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_exitMouseClicked

    private void txJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txJumlahActionPerformed
        // TODO add your handling code here:
        tambahTransaksi();
    }//GEN-LAST:event_txJumlahActionPerformed

    private void txBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txBayarActionPerformed
        // TODO add your handling code here:

        this.total = Integer.valueOf(txTotalBayar.getText());
        this.bayar = Integer.valueOf(txBayar.getText());

        if(this.total > this.bayar){
            JOptionPane.showMessageDialog(null, "Uang Tidak Cukup");
        }else{
            this.kembalian = this.bayar - this.total;
            txKembalian.setText(String.valueOf(kembalian));
        }
    }//GEN-LAST:event_txBayarActionPerformed

    private void btnTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseClicked
        // TODO add your handling code here:
        tambahTransaksi();
    }//GEN-LAST:event_btnTambahMouseClicked

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int row = jTable1.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        txBayar.setText("0");
        txKembalian.setText("0");
    }//GEN-LAST:event_btnHapusMouseClicked

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        String noTransaksi = txNoTransaksi.getText();
        String tanggal = txTanggal.getText();
        String idCustomer = txIDCustomer.getText();
        String total = txTotalBayar.getText();
        
        try {
            Connection c = KasirJava.getKoneksi();
            String sql = "INSERT INTO `penjualan`(`NoFaktur`, `Tanggal`, `ID_Customer`, `TotalBeli`) VALUES (?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, noTransaksi);
            p.setString(2, tanggal);
            p.setString(3, idCustomer);
            p.setString(4, total);
            p.executeUpdate();
            p.close();
        } catch (Exception e) {
            System.out.println("simpan penjualan error");
        }
        
        try{
            Connection c = KasirJava.getKoneksi();
            int baris = jTable1.getRowCount();
            
            for(int i = 0; i < baris; i++){
                String sql = "INSERT INTO `penjualanrinci`(`NoFaktur`, `ID_Barang`, `Nama_Barang`, `Jumlah`, `Harga`, `Total`) VALUES ('"
                        + jTable1.getValueAt(i, 0) + "', '" + jTable1.getValueAt(i, 1) +"', '"+ jTable1.getValueAt(i, 2)
                        +"', '"+ jTable1.getValueAt(i, 3) +"', '"+ jTable1.getValueAt(i, 4) +"', '"+ jTable1.getValueAt(i, 5)
                        +"')";
                PreparedStatement p = c.prepareStatement(sql);
                p.executeUpdate();
                p.close();
                
                
                //-----------------------------------------PRINT-----------------------------------------//
                
                itemName.add(String.valueOf(jTable1.getValueAt(i, 2)));
                quantity.add(String.valueOf(jTable1.getValueAt(i, 3)));
                itemPrice.add(String.valueOf(jTable1.getValueAt(i, 4)));
                subtotal.add(String.valueOf(jTable1.getValueAt(i, 5)));
                
                //-----------------------------------------PRINT-----------------------------------------//
            }
            total1.add(String.valueOf(this.total));
            bayar1.add(String.valueOf(this.bayar));
            kembalian1.add(String.valueOf(this.kembalian));
        } catch (Exception e) {
            System.out.println("simpan penjualanrinci error");
        }
        clear();
        utama();
        autonumber();
        kosong();
        txTampil.setText("Rp. 0");
        
        //-----------------------------------------PRINT-----------------------------------------//
        
        bHeight = Double.valueOf(itemName.size());
        //JOptionPane.showMessageDialog(rootPane, bHeight);
        
        PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
             pj.print();
          
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
        //-----------------------------------------PRINT-----------------------------------------//
        this.bayar = 0;
        this.total = 0;
        this.kembalian = 0;
        itemName.clear();
        quantity.clear();
        itemPrice.clear();
        subtotal.clear();
        bayar1.clear();
        total1.clear();
        kembalian1.clear();
    }//GEN-LAST:event_btnSimpanMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        ListBarang a = new ListBarang();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txIDBarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txIDBarang1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txIDBarang1ActionPerformed

    private void txNamaBarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNamaBarang1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNamaBarang1ActionPerformed

    private void txPencarian1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txPencarian1ActionPerformed
        // TODO add your handling code here:
        cariData2();
    }//GEN-LAST:event_txPencarian1ActionPerformed

    private void txPencarian1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPencarian1KeyTyped
        // TODO add your handling code here:
        cariData2();
    }//GEN-LAST:event_txPencarian1KeyTyped

    private void txHargaBarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txHargaBarang1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txHargaBarang1ActionPerformed

    private void txStok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txStok1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txStok1ActionPerformed

    private void jTableDataBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDataBarangMouseClicked
        // TODO add your handling code here:
        btnSimpan1.setEnabled(false);
        btnEdit1.setEnabled(true);
        btnHapus1.setEnabled(true);
        btnBatal1.setEnabled(true);

        int i = jTableDataBarang.getSelectedRow();
        if (i == -1){
            return;
        }

        String id = (String) model2.getValueAt(i, 0);
        String nama = (String) model2.getValueAt(i, 1);
        String jenis = (String) model2.getValueAt(i, 2);
        String hargaBarang = (String) model2.getValueAt(i, 3);
        String stok = (String) model2.getValueAt(i, 4);

        txIDBarang1.setText(id);
        txNamaBarang1.setText(nama);
        cbJenis1.setSelectedItem(jenis);
        txHargaBarang1.setText(hargaBarang);
        txStok1.setText(stok);
    }//GEN-LAST:event_jTableDataBarangMouseClicked

    private void btnSimpan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpan1MouseClicked
        // TODO add your handling code here:
        String id = txIDBarang1.getText();
        String nama = txNamaBarang1.getText();
        String jenis = (String)cbJenis1.getSelectedItem();
        String hargaBarang = txHargaBarang1.getText();
        String stok = txStok1.getText();
        
        try{
            Connection c = KasirJava.getKoneksi();
            String sql = "INSERT INTO barang VALUES (?, ?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id);
            p.setString(2, nama);
            p.setString(3, jenis);
            p.setString(4, hargaBarang);
            p.setString(5, stok);
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            loadData2();
        }catch(Exception e){
            System.out.println("Terjadi Kesalahan Dalam Menyimpan");
        }finally{
            autonumber2();
            clear3();
        }
    }//GEN-LAST:event_btnSimpan1MouseClicked

    private void btnEdit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit1MouseClicked
        // TODO add your handling code here:
        int i = jTableDataBarang.getSelectedRow();
        if (i == -1){
            return;
        }
        String id = (String) model2.getValueAt(i, 0);
        String nama = txNamaBarang.getText();
        String jenis = (String) cbJenis1.getSelectedItem();
        String hargaBarang = txHargaBarang1.getText();
        String stok = txStok1.getText();
        
        try{
            Connection c = KasirJava.getKoneksi();
            String sql = "UPDATE `barang` SET `Nama_Barang` = ?, `Jenis_Barang` = ?, `HargaBarang` = ?, `Stok` = ? WHERE `barang`.`ID_Barang` = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, nama);
            p.setString(2, jenis);
            p.setString(3, hargaBarang);
            p.setString(4, stok);
            p.setString(5, id);
            
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "DATA SUDAH DI UBAH");
            btnSimpan1.setEnabled(true);
            btnEdit1.setEnabled(false);
            btnHapus1.setEnabled(false);
            btnBatal1.setEnabled(false);
            clear();
        }catch(Exception e){
            System.out.println("UPDATE ERROR");
        }finally{
            loadData2();
            autonumber2();
        }
    }//GEN-LAST:event_btnEdit1MouseClicked

    private void btnHapus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapus1MouseClicked
        // TODO add your handling code here:
        int i = jTableDataBarang.getSelectedRow();
        if (i == -1){
            return;
        }
        String id = (String) model2.getValueAt(i, 0);
        int pernyataan = JOptionPane.showConfirmDialog(null, "Apakah Data Mau Di Hapus ?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if(pernyataan == JOptionPane.OK_OPTION){
            try{
                Connection c = KasirJava.getKoneksi();
                String sql = "DELETE FROM barang WHERE ID_Barang = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, id);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Data Sudah Di Hapus");
            }catch(Exception e){
                System.out.println("Terjadi Kesalahan");
            }finally{
                btnSimpan1.setEnabled(true);
                btnEdit1.setEnabled(false);
                btnHapus1.setEnabled(false);
                btnBatal1.setEnabled(false);
                loadData2();
                autonumber2();
                clear3();
            }
        }
        if(pernyataan == JOptionPane.CANCEL_OPTION){
        }
    }//GEN-LAST:event_btnHapus1MouseClicked

    private void btnBatal1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatal1MouseClicked
        // TODO add your handling code here:
        clear3();
        loadData2();
        btnSimpan1.setEnabled(true);
        btnEdit1.setEnabled(false);
        btnHapus1.setEnabled(false);
        btnBatal1.setEnabled(false);
        autonumber2();
    }//GEN-LAST:event_btnBatal1MouseClicked

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        // TODO add your handling code here:
        dispose();
        new framelogin().setVisible(true);
    }//GEN-LAST:event_btnLogoutMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DataBarang;
    private javax.swing.JLabel DataBarang1;
    private javax.swing.JLabel DataBarangBack1;
    private javax.swing.JLabel Kasir;
    private javax.swing.JLabel Kasir1;
    private javax.swing.JLabel KasirBack;
    private javax.swing.JLabel btnBatal1;
    private javax.swing.JLabel btnEdit1;
    private javax.swing.JLabel btnHapus;
    private javax.swing.JLabel btnHapus1;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnSimpan;
    private javax.swing.JLabel btnSimpan1;
    private javax.swing.JLabel btnTambah;
    private javax.swing.JComboBox<String> cbJenis1;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneDataBarang;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableDataBarang;
    private javax.swing.JLabel menu;
    private javax.swing.JTextField txBayar;
    public static javax.swing.JTextField txHarga;
    private javax.swing.JTextField txHargaBarang1;
    public static javax.swing.JTextField txIDBarang;
    private javax.swing.JTextField txIDBarang1;
    private javax.swing.JTextField txIDCustomer;
    private javax.swing.JTextField txJumlah;
    private javax.swing.JTextField txKembalian;
    public static javax.swing.JTextField txNamaBarang;
    private javax.swing.JTextField txNamaBarang1;
    private javax.swing.JTextField txNamaCustomer;
    private javax.swing.JTextField txNoTransaksi;
    private javax.swing.JTextField txPencarian1;
    private javax.swing.JTextField txStok1;
    private javax.swing.JTextField txTampil;
    private javax.swing.JTextField txTanggal;
    private javax.swing.JTextField txTotalBayar;
    // End of variables declaration//GEN-END:variables
}
