package kissjgabor20190319;

/**
 *
 * @author KjG
 */
public class CarRefueling {

    private String year, id;
    private double range, quantity, price, unitPrice, fuelAt100;

    private double countUnitPrice() {
        double uPrice = this.price / this.quantity;
        return Math.round(uPrice * 100) / 100.0;
    }

    private double countFuelAt100() {
        double fAt100 = 100 * this.quantity / this.range;
        return Math.round(fAt100 * 100) / 100.0;
    }

    public CarRefueling(String sor) {
        sor = sor.replace(',', '.');
        String[] t = sor.split("\\s*\t\\s*");
        this.year = t[0];
        this.range = Double.parseDouble(t[1]);
        this.quantity = Double.parseDouble(t[2]);
        this.price = Double.parseDouble(t[3]);
        this.id = t[4];
        this.unitPrice = countUnitPrice();
        this.fuelAt100 = countFuelAt100();
    }

    public String getYear() {
        return year;
    }

    public String getId() {
        return id;
    }

    public double getRange() {
        return range;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getFuelAt100() {
        return fuelAt100;
    }

}
