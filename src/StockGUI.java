import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StockGUI extends JFrame {
    private ProductTableModel productTableModel;
    private JTable tableProducts;

    public StockGUI() {
        setTitle("Estoque de Produtos de Informática");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        productTableModel = new ProductTableModel();
        tableProducts = new JTable(productTableModel);

        // Botão para adicionar um produto
        JButton addButton = new JButton("Adicionar Produto");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        // Botão para remover um produto selecionado
        JButton removeButton = new JButton("Remover Produto");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableProducts.getSelectedRow();
                if (selectedRow >= 0) {
                    productTableModel.removeProduct(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um produto para remover.");
                }
            }
        });

        // Botão para salvar os dados em um arquivo CSV
        JButton saveButton = new JButton("Salvar para CSV");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCSV();
            }
        });

        // Botão para carregar os dados de um arquivo CSV
        JButton loadButton = new JButton("Carregar de CSV");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCSV();
            }
        });

        // Painel de butões
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        // Adiciona a tabela e o painel de botões ao container principal
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(new JScrollPane(tableProducts), BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Adicionar um produto no Stock
    private void addProduct() {
        String[] tipos = {"Laptop", "Mouse", "Gaming Mouse"};
        String type = (String) JOptionPane.showInputDialog(
                this,
                "Selecione o tipo de produto:",
                "Adicionar Produto",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipos,
                tipos[0]
        );

        if (type != null) {
            String name = JOptionPane.showInputDialog("Nome:");
            double price = Double.parseDouble(JOptionPane.showInputDialog("Preço:"));
            int amount = Integer.parseInt(JOptionPane.showInputDialog("Quantidade:"));

            Product product = null;
            switch (type) {
                case "Laptop":
                    String brand = JOptionPane.showInputDialog("Marca:");
                    String model = JOptionPane.showInputDialog("Modelo:");
                    product = new Laptop(name, price, amount, brand, model);
                    break;
                case "Mouse":
                    String typeMouse = JOptionPane.showInputDialog("Tipo:");
                    product = new Mouse(name, price, amount, typeMouse);
                    break;
                case "Gaming Mouse":
                    String typeGamingMouse = JOptionPane.showInputDialog("Tipo:");
                    int dpi = Integer.parseInt(JOptionPane.showInputDialog("DPI:"));
                    product = new GamingMouse(name, price, amount, typeGamingMouse, dpi);
                    break;
            }

            if (product != null) {
                productTableModel.addProduct(product);
            }
        }
    }

    // Salvar os dados num arquivo CSV
    private void saveCSV() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                productTableModel.saveCSV(file);
                JOptionPane.showMessageDialog(this, "Dados salvos com sucesso.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar os dados: " + e.getMessage());
            }
        }
    }

    // Carregar os dados de um CSV
    private void loadCSV() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                productTableModel.loadCSV(file);
                JOptionPane.showMessageDialog(this, "Dados carregados com sucesso.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar os dados: " + e.getMessage());
            }
        }
    }

    // Método principal para executar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StockGUI().setVisible(true);
            }
        });
    }
}
