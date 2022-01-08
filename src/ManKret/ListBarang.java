/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManKret;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;

public class ListBarang extends javax.swing.JFrame {

    private DefaultTableModel model;
    
    public void loadData(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Connection c = KasirJava.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM barang";
            
            ResultSet r = s.executeQuery(sql);
            
            while(r.next()){
                Object[] o = new Object [5];
                o [0] = r.getString("ID_Barang");
                o [1] = r.getString("Nama_Barang");
                o [2] = r.getString("Jenis_Barang");
                o [3] = r.getString("HargaBarang");
                o [4] = r.getString("Stok");
                model.addRow(o);
            }
            r.close();
            s.close();
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Load Data Gagal");
        }
    }
    
    public void pencarian(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Barang");
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Jenis");
        tabel.addColumn("Harga Barang");
        tabel.addColumn("Stok");
        
        try{
            Connection c = KasirJava.getKoneksi();
            String sql = "SELECT * FROM barang WHERE ID_Barang LIKE '%" + txPencarian.getText() + "%'" + "OR Nama_Barang LIKE '%" + txPencarian.getText() + "%'";
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
            jTable.setModel(tabel);
            loadData();
        }catch(Exception e){
            System.out.println("CARI DATA ERROR");
        }finally{
        
        }
    }
    
    /**
     * Creates new form ListBarang
     */
    public ListBarang() {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        model = new DefaultTableModel();
        jTable.setModel(model);
        
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Jenis Barang");
        model.addColumn("Harga Barang");
        model.addColumn("Stok");
        
        loadData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txPencarian = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        btnPilih = new javax.swing.JToggleButton();
        btnBatal = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("List Data Barang");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Pencarian");

        txPencarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txPencarianActionPerformed(evt);
            }
        });
        txPencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txPencarianKeyTyped(evt);
            }
        });

        jTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable);

        btnPilih.setText("Pilih");
        btnPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPilih, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPilih)
                    .addComponent(btnBatal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihActionPerformed
        // TODO add your handling code here:
        int i = jTable.getSelectedRow();
        
        String id = jTable.getValueAt(i, 0).toString();
        String nama = jTable.getValueAt(i, 1).toString();
        String harga = jTable.getValueAt(i, 3).toString();
        
        Kasir.txIDBarang.setText(id);
        Kasir.txNamaBarang.setText(nama);
        Kasir.txHarga.setText(harga);
        
        dispose();
    }//GEN-LAST:event_btnPilihActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void txPencarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txPencarianActionPerformed
        // TODO add your handling code here:
        pencarian();
    }//GEN-LAST:event_txPencarianActionPerformed

    private void txPencarianKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPencarianKeyTyped
        // TODO add your handling code here:
        pencarian();
    }//GEN-LAST:event_txPencarianKeyTyped

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
            java.util.logging.Logger.getLogger(ListBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnBatal;
    private javax.swing.JToggleButton btnPilih;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField txPencarian;
    // End of variables declaration//GEN-END:variables
}