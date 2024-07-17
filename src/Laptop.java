public class Laptop extends Product {
    private String brand;
    private String model;

    // Construtor sem parâmetros
    public Laptop() {
        super();
        this.brand = "";
        this.model = "";
    }

    // Construtor com parâmetros
    public Laptop(String name, double price, int amount, String brand, String model) {
        super(name, price, amount);
        this.brand = brand;
        this.model = model;
    }

    // Construtor de cópia
    public Laptop(Laptop other) {
        super(other);
        this.brand = other.brand;
        this.model = other.model;
    }

    // Métodos getters e setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    // Método para representação textual do objeto
    @Override
    public String toString() {
        return "Laptop{" +
                "marca='" + brand + '\'' +
                ", modelo='" + model + '\'' +
                ", nome='" + name + '\'' +
                ", preço=" + price +
                ", quantidade=" + amount +
                '}';
    }

    // Método para imprimir os detalhes
    @Override
    public void print() {
        System.out.println(this.toString());
    }
}


