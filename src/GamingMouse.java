public class GamingMouse extends Mouse {
    private int dpi;

    // Construtor sem parâmetros
    public GamingMouse() {
        super();
        this.dpi = 0;
    }

    // Construtor com parâmetros
    public GamingMouse(String name, double price, int amount, String type, int dpi) {
        super(name, price, amount, type);
        this.dpi = dpi;
    }

    // Construtor de cópia
    public GamingMouse(GamingMouse other) {
        super(other);
        this.dpi = other.dpi;
    }

    // Métodos getters e setters
    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    // Método para representação textual do objeto
    @Override
    public String toString() {
        return "GamingMouse{" +
                "dpi=" + dpi +
                ", tipo='" + getType() + '\'' +
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
