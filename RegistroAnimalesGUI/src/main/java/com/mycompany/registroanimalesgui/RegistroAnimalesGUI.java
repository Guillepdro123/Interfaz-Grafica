

package com.mycompany.registroanimalesgui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


class FondoPanel extends JPanel {
    private Image imagen;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen == null) {
            imagen = new ImageIcon("finca.jpg").getImage();
        }
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
}

public class RegistroAnimalesGUI extends JFrame {

    private final JTextField txtID;
    private final JTextField txtRaza, txtEdad, txtPeso, txtSalud;
    private final JComboBox<String> cmbTipo;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final FondoPanel fondoPanel;
    private final String usuario = "Guillermo"; // Nombre del usuario para personalización

    public RegistroAnimalesGUI() {
        setTitle("Registro de Animales - Usuario: " + usuario);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel con imagen de fondo
        fondoPanel = new FondoPanel();
        fondoPanel.setLayout(null);
        setContentPane(fondoPanel);

        // Menú superior
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(70, 130, 180)); // Azul fuerte
        menuBar.setBorder(new LineBorder(Color.BLACK, 2));

        JMenu menuOpciones = new JMenu("Opciones");
        menuOpciones.setForeground(Color.WHITE);

        JMenuItem itemVacunacion = new JMenuItem("Vacunación");
        JMenuItem itemProduccionLeche = new JMenuItem("Producción de Leche");
        JMenuItem itemAyuda = new JMenuItem("Ayuda");
        JMenuItem itemSalir = new JMenuItem("Salir");

        
        itemSalir.addActionListener(e -> System.exit(0));
        itemAyuda.addActionListener(e -> mostrarAyuda());

        menuOpciones.add(itemVacunacion);
        menuOpciones.add(itemProduccionLeche);
        menuOpciones.add(itemAyuda);
        menuOpciones.addSeparator();
        menuOpciones.add(itemSalir);

        menuBar.add(menuOpciones);
        setJMenuBar(menuBar);

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        panelFormulario.setBounds(50, 70, 300, 200);
        panelFormulario.setOpaque(false);

        panelFormulario.add(new JLabel("ID:"));
        txtID = crearTextField();
        panelFormulario.add(txtID);

        panelFormulario.add(new JLabel("Tipo:"));
        cmbTipo = new JComboBox<>(new String[]{"Bovino", "Porcino", "Ovino", "Caprino"});
        panelFormulario.add(cmbTipo);

        panelFormulario.add(new JLabel("Raza:"));
        txtRaza = crearTextField();
        panelFormulario.add(txtRaza);

        panelFormulario.add(new JLabel("Edad (meses):"));
        txtEdad = crearTextField();
        panelFormulario.add(txtEdad);

        panelFormulario.add(new JLabel("Peso (kg):"));
        txtPeso = crearTextField();
        panelFormulario.add(txtPeso);

        panelFormulario.add(new JLabel("Estado de Salud:"));
        txtSalud = crearTextField();
        panelFormulario.add(txtSalud);

        fondoPanel.add(panelFormulario);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBounds(400, 100, 350, 100);
        panelBotones.setOpaque(false);

        JButton btnAgregar = crearBoton("Agregar", "agregar.png");
        JButton btnActualizar = crearBoton("Actualizar", "actualizar.png");
        JButton btnEliminar = crearBoton("Eliminar", "eliminar.png");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        fondoPanel.add(panelBotones);

        // Tabla con datos predefinidos
        String[] columnas = {"ID", "Tipo", "Raza", "Edad", "Peso", "Salud"};
        String[][] datos = {
            {"001", "Bovino", "Holstein", "24", "450", "Bueno"},
            {"002", "Porcino", "Yorkshire", "6", "90", "Excelente"},
            {"003", "Ovino", "Dorper", "12", "75", "Regular"},
            {"004", "Caprino", "Boer", "18", "65", "Bueno"}
        };
        tableModel = new DefaultTableModel(datos, columnas);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        estilizarTabla();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 300, 700, 150);
        fondoPanel.add(scrollPane);

        // Eventos de botones
        btnAgregar.addActionListener(e -> {
            mostrarMensaje("Animal agregado correctamente.");
        });

        btnActualizar.addActionListener(e -> {
            mostrarMensaje("Información del animal actualizada.");
        });

        btnEliminar.addActionListener(e -> {
            mostrarMensaje("Animal eliminado correctamente.");
        });

        // Atajos de teclado
        getRootPane().registerKeyboardAction(e -> btnAgregar.doClick(), KeyStroke.getKeyStroke("ctrl A"), JComponent.WHEN_IN_FOCUSED_WINDOW);
        getRootPane().registerKeyboardAction(e -> btnEliminar.doClick(), KeyStroke.getKeyStroke("ctrl E"), JComponent.WHEN_IN_FOCUSED_WINDOW);

        setVisible(true);
    }

    // Métodos auxiliares
    
    // Método para mostrar mensajes emergentes
    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void estilizarTabla() {
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(100, 149, 237));
        table.getTableHeader().setForeground(Color.WHITE);
    }

    private JButton crearBoton(String texto, String icono) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(new Color(255, 204, 102));
        boton.setFocusPainted(false);
        boton.setIcon(new ImageIcon(icono));
        return boton;
    }

    private JTextField crearTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(new Color(230, 230, 250));
        return textField;
    }

   

    private void mostrarAyuda() {
        JOptionPane.showMessageDialog(this, "Guía de uso:\n- Agregar: Ctrl + A\n- Eliminar: Ctrl + E", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
    }

    

    public static void main(String[] args) {
        RegistroAnimalesGUI registroAnimalesGUI = new RegistroAnimalesGUI();
    }
}
