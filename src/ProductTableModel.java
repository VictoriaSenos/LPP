import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductTableModel extends AbstractTableModel {
    private final List<Product> products;
    private final String[] columns = {"Nome", "Preço", "Quantidade", "Detalhes"};

    // Construtor que inicializa a lista de produtos
    public ProductTableModel() {
        this.products = new ArrayList<>();
    }

    // Adiciona um novo produto á lista
    public void addProduct(Product product) {
        products.add(product);
        fireTableRowsInserted(products.size() - 1, products.size() - 1);
    }

    // Remove um produto da lista
    public void removeProduct(int indice) {
        products.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    // Salva os dados para um arquivo CSV
    public void saveCSV(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(String.join(",", columns)); // Escreve os nomes das colunas
            writer.newLine();
            // Escreve os dados dos produtos
            for (Product product : products) {
                String detalhes = "";
                if (product instanceof Laptop) {
                    Laptop laptop = (Laptop) product;
                    detalhes = "Laptop - Marca: " + laptop.getBrand() + ", Modelo: " + laptop.getModel();
                } else if (product instanceof GamingMouse) {
                    GamingMouse gamingMouse = (GamingMouse) product;
                    detalhes = "Gaming Mouse - Tipo: " + gamingMouse.getType() + ", DPI: " + gamingMouse.getDpi();
                } else if (product instanceof Mouse) {
                    Mouse mouse = (Mouse) product;
                    detalhes = "Mouse - Tipo: " + mouse.getType();
                }
                writer.write(product.getName() + "," + product.getPrice() + "," + product.getAmount() + "," + detalhes);
                writer.newLine();
            }
        }
    }

    // Carrega os dados de um arquivo CSV
    public void loadCSV(File file) throws IOException {
        products.clear();
        fireTableDataChanged();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // Pular cabeçalho
            while ((line = reader.readLine()) != null) {
                String[] field = line.split(",");
                String name = field[0];
                double price = Double.parseDouble(field[1]);
                int amount = Integer.parseInt(field[2]);
                String details = field[3];

                Product product;
                if (details.startsWith("Laptop")) {
                    String brand = details.split("Marca: ")[1].split(",")[0].trim();
                    String model = details.split("Modelo: ")[1].trim();
                    product = new Laptop(name, price, amount, brand, model);
                } else if (details.startsWith("Gaming Mouse")) {
                    String type = details.split("Tipo: ")[1].split(",")[0].trim();
                    int dpi = Integer.parseInt(details.split("DPI: ")[1].trim());
                    product = new GamingMouse(name, price, amount, type, dpi);
                } else if (details.startsWith("Mouse")) {
                    String type = details.split("Tipo: ")[1].trim();
                    product = new Mouse(name, price, amount, type);
                } else {
                    product = new Product(name, price, amount);
                }

                addProduct(product);
            }
        }
    }


    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = products.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return product.getName();
            case 1:
                return product.getPrice();
            case 2:
                return product.getAmount();
            case 3:
                if (product instanceof Laptop) {
                    Laptop laptop = (Laptop) product;
                    return "Laptop - Marca: " + laptop.getBrand() + ", Modelo: " + laptop.getModel();
                } else if (product instanceof GamingMouse) {
                    GamingMouse gamingMouse = (GamingMouse) product;
                    return "Gaming Mouse - Tipo: " + gamingMouse.getType() + ", DPI: " + gamingMouse.getDpi();
                } else if (product instanceof Mouse) {
                    Mouse mouse = (Mouse) product;
                    return "Mouse - Tipo: " + mouse.getType();
                } else {
                    return "Detalhes não disponíveis";
                }
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
