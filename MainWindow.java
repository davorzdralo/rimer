import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

/**
 *
 * @author davor
 */
public final class MainWindow extends javax.swing.JFrame
{
    private TextProcessor textProcessor = new TextProcessor("dictionary.txt");

    public MainWindow()
    {
        super("Rimer 1.0");
        initComponents();
        center();
        setInputEnabled(false);
        jTable1.setModel(new DictionaryTableModel());
        setVisible(true);
    }

    public void start()
    {
        textProcessor.loadDictionary(this);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fieldSearch = new javax.swing.JTextField();
        spinnerPrecision = new javax.swing.JSpinner();
        progressBar = new javax.swing.JProgressBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fieldSearchKeyPressed(evt);
            }
        });

        spinnerPrecision.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(2), Integer.valueOf(1), null, Integer.valueOf(1)));
        spinnerPrecision.setToolTipText("broj slova koja se poklapaju");

        jScrollPane2.setViewportView(jTable1);

        buttonSearch.setText("Traži");
        buttonSearch.setFocusable(false);
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fieldSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerPrecision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerPrecision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonSearchActionPerformed
    {//GEN-HEADEREND:event_buttonSearchActionPerformed
        setInputEnabled(false);
        String pattern = fieldSearch.getText().toLowerCase();
        int precision = Integer.parseInt(spinnerPrecision.getValue().toString());
        textProcessor.search(this, pattern, precision);
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void fieldSearchKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_fieldSearchKeyPressed
    {//GEN-HEADEREND:event_fieldSearchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            buttonSearchActionPerformed(null);
    }//GEN-LAST:event_fieldSearchKeyPressed

    public void setInputEnabled(boolean enabled)
    {
        buttonSearch.setEnabled(enabled);
        spinnerPrecision.setEnabled(enabled);
        fieldSearch.setEnabled(enabled);
        fieldSearch.requestFocusInWindow();
    }

    public DictionaryTableModel getDictionaryTableModel()
    {
        return (DictionaryTableModel) jTable1.getModel();
    }

    public JProgressBar getProgressBar()
    {
        return progressBar;
    }

    private void center()
    {
        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
    }

    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    String nimbusLAF = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
                    UIManager.setLookAndFeel(nimbusLAF);
                }
                catch (Exception e)
                {
                    // Meh
                }

                new MainWindow().start();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSearch;
    private javax.swing.JTextField fieldSearch;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JSpinner spinnerPrecision;
    // End of variables declaration//GEN-END:variables
}
