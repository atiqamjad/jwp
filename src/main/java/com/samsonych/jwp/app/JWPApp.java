/**
 *
 */
package com.samsonych.jwp.app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import java.awt.Color;

//VS4E -- DO NOT REMOVE THIS LINE!
public class JWPApp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jPanel0;
    private JTabbedPane jTabbedPane0;
    private JTabbedPane jTabbedPane1;
    private JTabbedPane jTabbedPane2;
    private JTabbedPane jTabbedPane3;
    private JTable jTable0;
    private JScrollPane jScrollPane0;
    private JTable jTable1;
    private JScrollPane jScrollPane1;
    private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
    public JWPApp() {
        setBackground(Color.LIGHT_GRAY);
        initComponents();
    }

    private void initComponents() {
    	getContentPane().setLayout(null);
    	getContentPane().add(getJPanel0());
    	setSize(766, 291);
    }

    private JScrollPane getJScrollPane1() {
    	if (jScrollPane1 == null) {
    		jScrollPane1 = new JScrollPane();
    		jScrollPane1.setViewportView(getJTable1());
    	}
    	return jScrollPane1;
    }

    private JTable getJTable1() {
    	if (jTable1 == null) {
    		jTable1 = new JTable();
    		jTable1.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
    			private static final long serialVersionUID = 1L;
    			Class<?>[] types = new Class<?>[] { Object.class, Object.class, };

    			public Class<?> getColumnClass(int columnIndex) {
    				return types[columnIndex];
    			}
    		});
    	}
    	return jTable1;
    }

    private JScrollPane getJScrollPane0() {
    	if (jScrollPane0 == null) {
    		jScrollPane0 = new JScrollPane();
    		jScrollPane0.setViewportView(getJTable0());
    	}
    	return jScrollPane0;
    }

    private JTable getJTable0() {
    	if (jTable0 == null) {
    		jTable0 = new JTable();
    		jTable0.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
    			private static final long serialVersionUID = 1L;
    			Class<?>[] types = new Class<?>[] { Object.class, Object.class, };

    			public Class<?> getColumnClass(int columnIndex) {
    				return types[columnIndex];
    			}
    		});
    	}
    	return jTable0;
    }

    private JTabbedPane getJTabbedPane3() {
    	if (jTabbedPane3 == null) {
    		jTabbedPane3 = new JTabbedPane();
    	}
    	return jTabbedPane3;
    }

    private JTabbedPane getJTabbedPane2() {
    	if (jTabbedPane2 == null) {
    		jTabbedPane2 = new JTabbedPane();
    	}
    	return jTabbedPane2;
    }

    private JTabbedPane getJTabbedPane1() {
    	if (jTabbedPane1 == null) {
    		jTabbedPane1 = new JTabbedPane();
    	}
    	return jTabbedPane1;
    }

    private JTabbedPane getJTabbedPane0() {
    	if (jTabbedPane0 == null) {
    		jTabbedPane0 = new JTabbedPane();
    	}
    	return jTabbedPane0;
    }

    private JPanel getJPanel0() {
    	if (jPanel0 == null) {
    		jPanel0 = new JPanel();
    		jPanel0.setLayout(new GroupLayout());
    		jPanel0.add(getJTabbedPane0(), new Constraints(new Leading(182, 568, 10, 10), new Leading(134, 65, 10, 10)));
    		jPanel0.add(getJTabbedPane1(), new Constraints(new Leading(188, 100, 10, 10), new Leading(58, 100, 10, 10)));
    		jPanel0.add(getJTabbedPane2(), new Constraints(new Leading(31, 100, 10, 10), new Leading(20, 100, 10, 10)));
    	}
    	return jPanel0;
    }

    private static void installLnF() {
        try {
            String lnfClassname = PREFERRED_LOOK_AND_FEEL;
            if (lnfClassname == null)
                lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(lnfClassname);
        } catch (Exception e) {
            System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL + " on this platform:"
                    + e.getMessage());
        }
    }

    /**
     * Main entry of the class.
     * Note: This class is only created so that you can easily preview the result at runtime.
     * It is not expected to be managed by the designer.
     * You can modify it as you like.
     */
    public static void main(String[] args) {
        installLnF();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JWPApp frame = new JWPApp();
                frame.setDefaultCloseOperation(JWPApp.EXIT_ON_CLOSE);
                frame.setTitle("JWPApp");
                frame.getContentPane().setPreferredSize(frame.getSize());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

}
