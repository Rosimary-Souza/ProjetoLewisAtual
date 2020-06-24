/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rosim
 */

public class TelaLogin extends javax.swing.JFrame {
    private MusicaQuiz telaMusicaQuiz;   
    
    /**
     * Creates new form Lewis
     */
         
    public TelaLogin() {
        initComponents();
        telaMusicaQuiz = new MusicaQuiz();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jTextFieldSenha = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonLogin = new javax.swing.JButton();
        jLabelFundoLogin = new javax.swing.JLabel();

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Aprenda Inglês com Música ");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("login");
        setResizable(false);
        getContentPane().setLayout(null);

        jTextFieldUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUserActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldUser);
        jTextFieldUser.setBounds(90, 260, 210, 24);

        jTextFieldSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldSenha);
        jTextFieldSenha.setBounds(90, 290, 210, 22);

        jLabel4.setText("Usuário:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 260, 47, 20);

        jLabel5.setText("Senha:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 290, 39, 20);

        jButtonCadastrar.setBackground(new java.awt.Color(255, 51, 51));
        jButtonCadastrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButtonCadastrar.setText("Cadastrar");
        jButtonCadastrar.setToolTipText("");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCadastrar);
        jButtonCadastrar.setBounds(210, 340, 100, 33);

        jButtonLogin.setBackground(new java.awt.Color(255, 51, 51));
        jButtonLogin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonLogin);
        jButtonLogin.setBounds(90, 340, 80, 33);

        jLabelFundoLogin.setBackground(new java.awt.Color(102, 255, 255));
        jLabelFundoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Fundo2.png"))); // NOI18N
        getContentPane().add(jLabelFundoLogin);
        jLabelFundoLogin.setBounds(-40, 0, 370, 390);

        setSize(new java.awt.Dimension(346, 431));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSenhaActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        TelaCadastro obj = new TelaCadastro ();        // TODO add your handling code here:
        obj.setVisible(true);
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        String email, senha, n, s;
        int encontrou=0;
        
        email = jTextFieldUser.getText();
        senha = jTextFieldSenha.getText();
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/lewis","root","");
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("Select * from tabela_cadastro");
            
            while(res.next())
            {
                n = res.getString("nome");
                s = res.getString("senha");
                
                if(email.equals(n) && senha.equals(s))
                {
                    encontrou = 1;
                }
                else
                {
                    encontrou = 0;
                }
            }
            if(encontrou == 1)
            {
                telaMusicaQuiz.setVisible(true);
                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Login e/ou usuário errado(s)","Erro",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(ClassNotFoundException e)
        {
               JOptionPane.showMessageDialog(null,e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);           
        }
        catch(SQLException e)
        {
               JOptionPane.showMessageDialog(null,e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jTextFieldUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUserActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelFundoLogin;
    private javax.swing.JPasswordField jTextFieldSenha;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables
}
