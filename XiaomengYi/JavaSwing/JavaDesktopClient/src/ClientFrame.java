
import javax.swing.JFileChooser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
/**
 * @author Xiaomeng
 */
public class ClientFrame extends javax.swing.JFrame {
    String path = null;
    String url = null;
    /**
     * Creates new form ClientFrame
     */
    public ClientFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title = new javax.swing.JLabel();
        chooseButton = new javax.swing.JButton();
        uploadButton = new javax.swing.JButton();
        QuitButton = new javax.swing.JButton();
        DomainBox = new javax.swing.JComboBox();
        Pane1 = new javax.swing.JScrollPane();
        teamInfo = new javax.swing.JTextArea();
        Laber1 = new javax.swing.JLabel();
        StatustText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CMPE 207");
        setResizable(false);

        Title.setFont(new java.awt.Font("Monaco", 0, 18)); // NOI18N
        Title.setText("CMPE207 Desktop Client");

        chooseButton.setText("Choose File");
        chooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseButtonActionPerformed(evt);
            }
        });

        uploadButton.setText("Upload");
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });

        QuitButton.setText("Quit");
        QuitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitButtonActionPerformed(evt);
            }
        });

        DomainBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Wei Zhong", "Xiaomeng Yi", "Tuo Lei", "Emy Itegbe" }));
        DomainBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DomainBoxActionPerformed(evt);
            }
        });

        teamInfo.setColumns(20);
        teamInfo.setFont(new java.awt.Font("Monaco", 0, 13)); // NOI18N
        teamInfo.setRows(5);
        teamInfo.setText("CMPE 207 Project\nTeam:Budweiser\nWei Zhong\nXiaomeng Yi\nTuo Lei\nEmy Itegbe");
        Pane1.setViewportView(teamInfo);

        Laber1.setText("Server");

        StatustText.setBackground(new java.awt.Color(0, 0, 0));
        StatustText.setForeground(new java.awt.Color(0, 255, 102));
        StatustText.setText("Ready...");
        StatustText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatustTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Laber1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DomainBox, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(StatustText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(QuitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(uploadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chooseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Pane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Pane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Title)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chooseButton)
                            .addComponent(DomainBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Laber1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uploadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(QuitButton)
                            .addComponent(StatustText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chooseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseButtonActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Choose File...");
        fc.setApproveButtonText("Sure");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(this)) {
            path=fc.getSelectedFile().getPath();
        }
        StatustText.setText(path);
    }//GEN-LAST:event_chooseButtonActionPerformed

    private void QuitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_QuitButtonActionPerformed

    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int index = DomainBox.getSelectedIndex();
        switch(index){
            case 0: url = "zhongwei";
                    break;
            case 1: url = "http://littlehabit.net/upload.php";
                    break;
            case 2: url = "http://tuolei.org/upload.php";
                    break;
            case 3: url = "Emy";
                    break;
        }
        try {
            StatustText.setText("Uploading...");
            CloseableHttpClient httpClient = HttpClients.createDefault();
            File file = new File(path);
            HttpPost uploadFile = new HttpPost(url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("user", "Xiaomeng From Desktop");
            builder.addTextBody("key","Budweiser");
            builder.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);
            HttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();
            System.out.println(response.toString());
            BufferedReader br2 = new BufferedReader(
            new InputStreamReader((responseEntity.getContent())));
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            StatustText.setText("Finished");
        }
    }//GEN-LAST:event_uploadButtonActionPerformed

    private void StatustTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatustTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatustTextActionPerformed

    private void DomainBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DomainBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DomainBoxActionPerformed

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
                }
                System.out.println(info);
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox DomainBox;
    private javax.swing.JLabel Laber1;
    private javax.swing.JScrollPane Pane1;
    private javax.swing.JButton QuitButton;
    private javax.swing.JTextField StatustText;
    private javax.swing.JLabel Title;
    private javax.swing.JButton chooseButton;
    private javax.swing.JTextArea teamInfo;
    private javax.swing.JButton uploadButton;
    // End of variables declaration//GEN-END:variables
}
