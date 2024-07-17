public class Mouse extends Product {
    private String type;

    // Construtor sem parâmetros
    public Mouse() {
        super();
        this.type = "";
    }

    // Construtor com parâmetros
    public Mouse(String name, double price, int amount, String type) {
        super(name, price, amount);
        this.type = type;
    }

    // Construtor de cópia
    public Mouse(Mouse other) {
        super(other);
        this.type = other.type;
    }

    // Métodos getters e setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Método para representação textual do objeto
    @Override
    public String toString() {
        return "Mouse{" +
                "tipo='" + type + '\'' +
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

