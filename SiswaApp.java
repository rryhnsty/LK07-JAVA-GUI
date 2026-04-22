import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SiswaApp extends JFrame {
    JTextField tfNIS, tfNama, tfAlamat;
    JTable table;
    DefaultTableModel model;

    String fileName = "siswa.csv";

    public SiswaApp() {
        setTitle("Data Siswa");
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // warna
        Color warnaAtas = new Color(220, 235, 250);
        Color warnaInput = new Color(240, 248, 255);
        Color warnaBawah = new Color(230, 230, 250);

        // panel input
        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data Siswa"));
        panelInput.setBackground(warnaInput);

        panelInput.add(new JLabel("NIS"));
        tfNIS = new JTextField(8);
        tfNIS.setBackground(new Color(255, 255, 204));
        panelInput.add(tfNIS);

        panelInput.add(new JLabel("Nama"));
        tfNama = new JTextField(10);
        tfNama.setBackground(new Color(255, 255, 204));
        panelInput.add(tfNama);

        panelInput.add(new JLabel("Alamat"));
        tfAlamat = new JTextField(10);
        tfAlamat.setBackground(new Color(255, 255, 204));
        panelInput.add(tfAlamat);

        // tombol
        JPanel panelButton = new JPanel();
        panelButton.setBackground(warnaBawah);

        JButton btnAdd = new JButton("TAMBAH");
        JButton btnUpdate = new JButton("UPDATE");
        JButton btnDelete = new JButton("DELETE");
        JButton btnClear = new JButton("CLEAR");

        // warna button
        btnAdd.setBackground(new Color(15, 204, 15));
        btnUpdate.setBackground(new Color(255, 255, 0));
        btnClear.setBackground(new Color(200, 200, 200));
        btnDelete.setBackground(Color.RED);

        btnAdd.setForeground(Color.BLACK);
        btnUpdate.setForeground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);
        btnClear.setForeground(Color.BLACK);

        panelButton.add(btnAdd);
        panelButton.add(btnUpdate);
        panelButton.add(btnDelete);
        panelButton.add(btnClear);

        // tabel
        model = new DefaultTableModel(new String[]{"NIS", "Nama", "Alamat"}, 0);
        table = new JTable(model);
        table.setBackground(Color.WHITE);
        table.setSelectionBackground(new Color(173, 216, 230));
        table.setSelectionForeground(Color.BLACK);
        table.getTableHeader().setBackground(new Color(100, 149, 237));
        table.getTableHeader().setForeground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(table);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(warnaAtas);

        JLabel judul = new JLabel("Aplikasi Data Siswa", JLabel.CENTER);
        judul.setFont(new Font("Arial", Font.BOLD, 16));
        judul.setForeground(new Color(0, 51, 102));
        judul.setOpaque(true);
        judul.setBackground(warnaAtas);

        topPanel.add(judul, BorderLayout.NORTH);
        topPanel.add(panelInput, BorderLayout.CENTER);

        // layout utama
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        loadData();

        // event tombol
        btnAdd.addActionListener(e -> tambahData());
        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> deleteData());
        btnClear.addActionListener(e -> clearForm());

        // klik tabel
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                tfNIS.setText(model.getValueAt(row, 0).toString());
                tfNama.setText(model.getValueAt(row, 1).toString());
                tfAlamat.setText(model.getValueAt(row, 2).toString());
            }
        });
    }

    // load data dari file
    void loadData() {
        model.setRowCount(0);

        try {
            File file = new File(fileName);
            if (!file.exists()) file.createNewFile();

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
            br.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data dari file");
        }
    }

    // cek NIS
    boolean isNISExist(String nis) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equals(nis)) {
                return true;
            }
        }
        return false;
    }

    // tambah data (sudah ada validasi)
    void tambahData() {
        String nis = tfNIS.getText().trim();
        String nama = tfNama.getText().trim();
        String alamat = tfAlamat.getText().trim();

        // VALIDASI INPUT
        if (nis.isEmpty() || nama.isEmpty() || alamat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }

        if (isNISExist(nis)) {
            JOptionPane.showMessageDialog(this, "NIS sudah ada!");
            return;
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
            bw.write(nis + "," + nama + "," + alamat);
            bw.newLine();
            bw.close();

            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
            loadData();
            clearForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data");
        }
    }

    // update data
    void updateData() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        model.setValueAt(tfNIS.getText(), row, 0);
        model.setValueAt(tfNama.getText(), row, 1);
        model.setValueAt(tfAlamat.getText(), row, 2);

        saveAll();
        JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
        clearForm();
    }

    // delete data (dengan konfirmasi)
    void deleteData() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Yakin ingin menghapus data ini?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            model.removeRow(row);
            saveAll();
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            clearForm();
        }
    }

    // simpan ulang file
    void saveAll() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

            for (int i = 0; i < model.getRowCount(); i++) {
                bw.write(
                    model.getValueAt(i, 0) + "," +
                    model.getValueAt(i, 1) + "," +
                    model.getValueAt(i, 2)
                );
                bw.newLine();
            }
            bw.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal update file");
        }
    }

    // bersihkan form
    void clearForm() {
        tfNIS.setText("");
        tfNama.setText("");
        tfAlamat.setText("");
    }

    public static void main(String[] args) {
        new SiswaApp().setVisible(true);
    }
}