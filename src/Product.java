public class Product implements ItemStock, Cloneable {
    protected String name;
    protected double price;
    protected int amount;

    // Construtor sem parâmetros
    public Product() {
        this.name = "";
        this.price = 0.0;
        this.amount = 0;
    }

    // Construtor com parâmetros
    public Product(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    // Construtor de cópia
    public Product(Product outro) {
        this.name = outro.name;
        this.price = outro.price;
        this.amount = outro.amount;
    }

    // Métodos getters e setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    // Método toString para representação textual do objeto
    @Override
    public String toString() {
        return "ProdutoInformatica{" +
                "nome='" + name + '\'' +
                ", preço=" + price +
                ", quantidade=" + amount +
                '}';
    }

    // Método equals para comparar os objetos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.price, price) != 0) return false;
        if (amount != product.amount) return false;
        return name != null ? name.equals(product.name) : product.name == null;
    }

    // Método para gerar o código hash
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + amount;
        return result;
    }

    // Método para criar uma cópia do objeto
    @Override
    protected Product clone() {
        return new Product(this);
    }

    // Método para imprimir os detalhes do objeto
    public void print() {
        System.out.println(this.toString());
    }
}

