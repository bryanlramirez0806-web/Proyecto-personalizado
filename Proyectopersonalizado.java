package proyectopersonalizado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Proyectopersonalizado extends JFrame implements ActionListener {
    
    JMenuBar bm;
    JMenu mArchivo, mBD;
    JMenuItem mBD_c, mBD_R, mBD_E, mBD_A;
    
    JLabel lbi, lbNombre, ibId, ibdire, ibtele, ibcor, ibrfc;
    JTextField txtNombre, txtId, txtdire, txttele, txtcor, txtrfc;
    JButton con, agre, actu, eli;
    DefaultTableModel modeloTabla;
    JTable tabla;
    JScrollPane scrollTabla;
    
    PersonaDAO personaDAO;
    
    Proyectopersonalizado() {
        this.setBounds(150, 50, 800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        
        personaDAO = new PersonaDAO(); 
        
        bm = new JMenuBar();
        this.setJMenuBar(bm);
        
        mArchivo = new JMenu("Archivo");
        bm.add(mArchivo);
        
        mBD = new JMenu("Base de Datos");
        bm.add(mBD);
        
        this.mBD_R = new JMenuItem("Consultar");
        this.mBD_R.addActionListener(e -> {
            personaDAO.consultar(modeloTabla);
            ajustarTamanoTabla();
        });
        mBD.add(mBD_R);
        
        this.mBD_c = new JMenuItem("Crear");
        this.mBD_c.addActionListener(e -> {
            agregarRegistro();
        });
        mBD.add(mBD_c);
        
        this.mBD_A = new JMenuItem("Actualizar");
        this.mBD_A.addActionListener(e -> {
            actualizarRegistro();
        });
        mBD.add(mBD_A);

        this.mBD_E = new JMenuItem("Eliminar");
        this.mBD_E.addActionListener(e -> {
            eliminarRegistro();
        });
        mBD.add(mBD_E);
        
        lbi = new JLabel("Datos personales");
        lbi.setBounds(350, 10, 250, 90);
        this.add(lbi);
        
        lbNombre = new JLabel("Nombre"); 
        lbNombre.setBounds(150, 75, 150, 30);
        this.add(lbNombre);
        
        ibId = new JLabel("ID"); 
        ibId.setBounds(150, 105, 150, 30);
        this.add(ibId);
        
        ibdire = new JLabel("Direccion"); 
        ibdire.setBounds(150, 135, 150, 30);
        this.add(ibdire);
        
        ibtele = new JLabel("Telefono"); 
        ibtele.setBounds(150, 165, 150, 30);
        this.add(ibtele);
        
        ibcor = new JLabel("Correo"); 
        ibcor.setBounds(150, 195, 150, 30);
        this.add(ibcor);
        
        ibrfc = new JLabel("RFC"); 
        ibrfc.setBounds(150, 225, 150, 30);
        this.add(ibrfc);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(300, 75, 150, 30);
        this.add(txtNombre);
        
        txtId = new JTextField();
        txtId.setBounds(300, 105, 150, 30);
        this.add(txtId);
        
        txtdire = new JTextField();
        txtdire.setBounds(300, 135, 150, 30);
        this.add(txtdire);
        
        txttele = new JTextField();
        txttele.setBounds(300, 165, 150, 30);
        this.add(txttele);
        
        txtcor = new JTextField();
        txtcor.setBounds(300, 195, 150, 30);
        this.add(txtcor);
        
        txtrfc = new JTextField();
        txtrfc.setBounds(300, 225, 150, 30);
        this.add(txtrfc);
        
        con = new JButton("Consultar");
        con.setBounds(600, 75, 150, 30);
        this.add(con);
        this.con.addActionListener(this);
        
        actu = new JButton("Actualizar");
        actu.setBounds(600, 105, 150, 30);
        this.add(actu);
        this.actu.addActionListener(this);
        
        agre = new JButton("Agregar");
        agre.setBounds(450, 75, 150, 30);
        this.add(agre);
        this.agre.addActionListener(this);
        
        eli = new JButton("Eliminar");
        eli.setBounds(450, 105, 150, 30);
        this.add(eli);
        this.eli.addActionListener(this);
        
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("RFC");

        tabla = new JTable(modeloTabla);
        scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBounds(150, 255, 555, 20);
        this.add(scrollTabla);
        
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Proyectopersonalizado();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.con) {
            personaDAO.consultar(modeloTabla);
            ajustarTamanoTabla();
        }
        if (e.getSource() == this.agre) {
            agregarRegistro();
        }
        if (e.getSource() == this.actu) {
            actualizarRegistro();
        }
        if (e.getSource() == this.eli) {
            eliminarRegistro();
        }
    }
    
    /*
     Valida y ejecuta la acción de agregar.
     */
    public void agregarRegistro() {
        if (this.txtNombre.getText().trim().isEmpty() || this.txtdire.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llene al menos el nombre y la dirección.", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        personaDAO.agregar(
            this.txtNombre.getText(),
            this.txtdire.getText(),
            this.txttele.getText(),
            this.txtcor.getText(),
            this.txtrfc.getText()
        );
        JOptionPane.showMessageDialog(this, "Registro agregado exitosamente.");
        personaDAO.consultar(modeloTabla);
        ajustarTamanoTabla();
    }

    /*
     Valida y ejecuta la acción de actualizar.
     */
    public void actualizarRegistro() {
        try {
            String idTexto = this.txtId.getText().trim();
            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el ID del registro a actualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            personaDAO.actualizar(
                Integer.parseInt(idTexto),
                this.txtNombre.getText(),
                this.txtdire.getText(),
                this.txttele.getText(),
                this.txtcor.getText(),
                this.txtrfc.getText()
            );
            JOptionPane.showMessageDialog(this, "Registro actualizado correctamente.");
            personaDAO.consultar(modeloTabla);
            ajustarTamanoTabla();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
     Valida, confirma y ejecuta la acción de eliminar.
     */
    public void eliminarRegistro() {
        try {
            String idTexto = this.txtId.getText().trim();

            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del registro a eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int respuesta = JOptionPane.showConfirmDialog(
                this, 
                "¿Está seguro de que desea eliminar el registro con ID: " + idTexto + "?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                personaDAO.eliminar(Integer.parseInt(idTexto));
                JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.");
                this.txtId.setText("");
                personaDAO.consultar(modeloTabla);
                ajustarTamanoTabla();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
     Ajusta la altura de la tabla según los datos.
     */
    public void ajustarTamanoTabla() {
        int filas = modeloTabla.getRowCount();
        int altoFila = tabla.getRowHeight(); 
        int altoCabecera = tabla.getTableHeader().getPreferredSize().height;

        int altoCalculado = altoCabecera + (filas * altoFila) + 5;
        int altoMinimo = 60; 
        int altoMaximo = 300; 

        int altoFinal = Math.max(altoMinimo, Math.min(altoCalculado, altoMaximo));

        scrollTabla.setBounds(150, 255, 555, altoFinal);
        this.revalidate();
        this.repaint();
    }
}