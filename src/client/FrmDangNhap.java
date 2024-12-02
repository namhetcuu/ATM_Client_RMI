package client;
import Services.ClientCallbackImpl;
import Services.ITinhToan;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Rectangle;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class FrmDangNhap extends javax.swing.JFrame {

    private JTextField selectedTextField;

    SharedVariables sharedVariables = new SharedVariables();

    public static int demChuoiTrongArrayList(ArrayList<String> list, String chuoi) {
        int count = 0;
        for (String phanTu : list) {
            if (phanTu.equals(chuoi)) {
                count++;
            }
        }
        return count;
    }

    long taikhoan = 0;

    public void run() {
        try {
            selectedTextField = null;
            String a = tk.getText();
            if (!a.isEmpty()) {
                taikhoan = Long.parseLong(a);
            }
            String b = mk.getText();
           ITinhToan server = ServerUtility.getServerInstance();
            if (mk.isEnabled() && (a.isEmpty() || b.isEmpty())) {
                txtloi.setText("Please enter your full account and password");
                tk.requestFocusInWindow();
            } else if (server.GetStatus(taikhoan) == -1) {
                txtloi.setText(taikhoan + " has been locked!");
                mk.setEnabled(false);
                mk.setText("");
            } else {
                if (!b.isEmpty()) {
                    long pin = Long.parseLong(b);
                    int login = server.KTDangNhap(taikhoan, pin);
                    switch (login) {
                        case 0:
                            SharedVariables.stk = taikhoan;
                            SharedVariables.tendn = server.timKiemNguoiNhan(taikhoan);
                            //SharedVariables.soTien = server.GetSoTien(taikhoan);
                            FrmDangNhap.this.setVisible(false);
                            FrmClient f = new FrmClient();
                            //TaiKhoan taiKhoan = new TaiKhoan(SharedVariables.stk,SharedVariables.tendn,SharedVariables.soTien);
                            ClientCallbackImpl clientCallbackImpl = new ClientCallbackImpl(f);
                            server.callbackRegister(clientCallbackImpl,SharedVariables.tendn);
                            
                            f.setVisible(true);
                            break;
                        case 1: // đang được sử dụng
                            txtloi.setText(taikhoan + "account is in use");

                            break;
                        case -1: // đã bị khóa
                            txtloi.setText(taikhoan + " has been locked!");
                            server.SetStatus(taikhoan, -1);
                            mk.setEnabled(false);
                            mk.setText("");
                            break;
                        case -2:
                            sharedVariables.saiMaPin.add(String.valueOf(taikhoan));
                            if (demChuoiTrongArrayList(sharedVariables.saiMaPin, String.valueOf(taikhoan)) >= 3) {
                                txtloi.setText("Enter the wrong PIN code 3 times, the account is locked!");
                                mk.setText("");
                                mk.setEnabled(false);
                                server.SetStatus(taikhoan, -1);
                                break;
                            } else {
                                txtloi.setText("Wrong PIN code " + demChuoiTrongArrayList(sharedVariables.saiMaPin, String.valueOf(taikhoan)) + " times");
                                mk.setText("");
                                mk.requestFocusInWindow();
                                break;
                            }
                        case -3:
                            txtloi.setText("Database error");
                            break;
                        case -4:
                            txtloi.setText("Account dosen't exist");
                            break;
                    }
                } else {
                    if (server.timKiemNguoiNhan(taikhoan) == null) {
                        txtloi.setText("Account dosen't exist");
                        tk.requestFocusInWindow();
                    } else if (server.timKiemNguoiNhan(taikhoan) != null) {
                        txtloi.setText("");
                    }
                }
            }
        } catch (Exception tt) {
            tt.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtError = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setAlignmentY(0.0f);
        jLabel2 = new javax.swing.JLabel();
        mk = new javax.swing.JPasswordField();
        mk.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
        tk = new javax.swing.JTextField();
        tk.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
        tk.setBounds(new Rectangle(2, 0, 0, 0));
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel5.setBackground(new Color(0, 0, 0));
        txtloi = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        dn1 = new javax.swing.JButton();
        dn1.setBackground(new Color(192, 192, 192));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtError.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        txtError.setForeground(new java.awt.Color(255, 51, 51));

        jPanel1.setBackground(new Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 36)); // NOI18N
        jLabel1.setText("Account Nunber:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 2, 36)); // NOI18N
        jLabel2.setText("PIN Code:");

        mk.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        mk.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mkFocusGained(evt);
            }
        });
        mk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mkActionPerformed(evt);
            }
        });
        mk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mkKeyPressed(evt);
            }
        });

        tk.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tk.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tkFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tkFocusLost(evt);
            }
        });
        tk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tkKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(12,87,118));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("YAKI BANK");

        jPanel2.setBackground(new Color(192, 192, 192));
        jPanel2.setBorder(new LineBorder(new Color(193, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel5.setForeground(new Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Sign In here >");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, jPanel2Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
        			.addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.TRAILING)
        		.addComponent(jLabel5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        jPanel2.setLayout(jPanel2Layout);

        txtloi.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        txtloi.setForeground(new java.awt.Color(255, 0, 0));
        txtloi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtloi.setText("xin loi duoc chua");

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel4.setText("Notification from the System:");
        
        JSeparator separator = new JSeparator();
        
        textField = new JTextField();
        textField.setAlignmentY(Component.TOP_ALIGNMENT);
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField.setBackground(new Color(193, 0, 0));
        textField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Welcome to ATM Machine");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon(FrmDangNhap.class.getResource("/images/bank_home.png")));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jLabel4)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtloi, GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        								.addGroup(jPanel1Layout.createSequentialGroup()
        									.addComponent(jLabel2)
        									.addGap(40))
        								.addGroup(jPanel1Layout.createSequentialGroup()
        									.addComponent(jLabel1)
        									.addGap(18)))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        								.addComponent(mk, 554, 554, 554)
        								.addComponent(tk, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))))
        					.addContainerGap())
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(0, 885, Short.MAX_VALUE)
        					.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(12))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
        					.addGap(31)
        					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())))
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(156)
        			.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
        			.addGap(88)
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 642, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(290, Short.MAX_VALUE))
        		.addComponent(textField, GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(21)
        					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(40)
        					.addComponent(jLabel3)))
        			.addGap(20)
        			.addComponent(textField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(80)
        					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(53)
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
        			.addGap(75)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        				.addComponent(tk, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
        			.addGap(34)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jLabel2)
        				.addComponent(mk, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
        			.addGap(42)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtloi)
        				.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
        			.addGap(43)
        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(22))
        );
        jPanel1.setLayout(jPanel1Layout);

        dn1.setFont(new java.awt.Font("Times New Roman", 1, 20));
        dn1.setText("Login");
        dn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(134)
        			.addComponent(txtError)
        			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 1177, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(dn1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(txtError)
        					.addGap(33)
        					.addComponent(dn1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        					.addGap(34))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
        					.addContainerGap())))
        );
        getContentPane().setLayout(layout);


        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public FrmDangNhap() {
        initComponents();
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        txtloi.setText("");
        tk.requestFocusInWindow();

    }//GEN-LAST:event_formWindowOpened

    

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try {
            SharedVariables.tendn = "";
            ITinhToan server = (ITinhToan) ServerUtility.getServerInstance();
            server.DangXuat(SharedVariables.stk);
            SharedVariables.stk = 0;
            this.dispose();//hủy Frame: FrmDangNhap
            FrmDangNhap f = new FrmDangNhap();
            f.setVisible(true);//Mở form: drmClient
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        if (selectedTextField != null) {
            // Lấy văn bản hiện tại từ JTextField
            String currentText = selectedTextField.getText();

            // Xóa một ký tự cuối cùng từ chuỗi
            if (currentText.length() > 0) {
                currentText = currentText.substring(0, currentText.length() - 1);
            }

            // Cập nhật JTextField với chuỗi mới
            selectedTextField.setText(currentText);
        }

    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        if (selectedTextField == tk && mk.isEnabled()) {
            mk.requestFocusInWindow();
            mk.setText("");
        } else {
            tk.requestFocusInWindow();
            tk.setText("");
            txtloi.setText("");
            mk.setEnabled(true);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void dn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dn1ActionPerformed
        // TODO add your handling code here:
        try {
            if(mk.getText().length() < 6) {
                txtloi.setText("Mã PIN phải gồm 6 chữ số");
                mk.requestFocusInWindow();
                selectedTextField = mk;
            } else {
                run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_dn1ActionPerformed

    private void tkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkKeyPressed
        // TODO add your handling code here:
        String matkhau = String.valueOf(tk.getText());
        int lengthMatKhau = matkhau.length();
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            if (lengthMatKhau < 8) {
                tk.setEditable(true);
            } else {
                tk.setEditable(false);
            }
        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                tk.setEditable(true);
            } else {
                tk.setEditable(false);
            }
        }
    }//GEN-LAST:event_tkKeyPressed

    private void tkFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tkFocusLost
        // TODO add your handling code here:
        //        try {
        //            run();
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
    }//GEN-LAST:event_tkFocusLost

    private void tkFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tkFocusGained
        // TODO add your handling code here:

        selectedTextField = tk;
        mk.setText("");
    }//GEN-LAST:event_tkFocusGained

    private void mkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mkKeyPressed
        // TODO add your handling code here:
        //        if (evt.getKeyCode() == 10) {
        //            try {
        //                run();
        //            } catch (Exception tt) {
        //                tt.printStackTrace();
        //            }
        //        }
        String PIN = String.valueOf(mk.getPassword());
        int lengthPIN = PIN.length();
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            if (lengthPIN < 6) {
                mk.setEditable(true);
            } else {
                mk.setEditable(false);
            }
        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                mk.setEditable(true);
            } else {
                mk.setEditable(false);
            }
        }
    }//GEN-LAST:event_mkKeyPressed

    private void mkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mkActionPerformed

    private void mkFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mkFocusGained
        // TODO add your handling code here:
        //txtloi.setText("");
        selectedTextField = mk;
        mk.setText("");
    }//GEN-LAST:event_mkFocusGained

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
            java.util.logging.Logger.getLogger(FrmDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dn1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField mk;
    private javax.swing.JTextField tk;
    private javax.swing.JLabel txtError;
    private javax.swing.JLabel txtloi;
    private JTextField textField;
}