import java.util.Locale;

public class Proizvod {

    private static int brojac = 0;

    private final String sifra;
    private String naziv;
    private double cijena;
    private int kolicina;

    public Proizvod(String naziv, double cijena, int kolicina) {
        String ocisceniNaziv = validateName(naziv);
        validatePrice(cijena);
        if (kolicina < 0) {
            throw new IllegalArgumentException("Početna količina ne smije biti negativna: " + kolicina);
        }

        brojac++;
        this.sifra = String.format("P%03d", brojac);
        this.naziv = ocisceniNaziv;
        this.cijena = cijena;
        this.kolicina = kolicina;
    }

    public Proizvod(String naziv, double cijena) {
        this(naziv, cijena, 0);
    }

    public static int getProductCount() {
        return brojac;
    }

    public String getCode() {
        return sifra;
    }

    public String getName() {
        return naziv;
    }

    public void setName(String naziv) {
        this.naziv = validateName(naziv);
    }

    public double getPrice() {
        return cijena;
    }

    public void setPrice(double cijena) {
        validatePrice(cijena);
        this.cijena = cijena;
    }

    public int getQuantity() {
        return kolicina;
    }

    public void add(int kolicina) {
        if (kolicina <= 0) {
            throw new IllegalArgumentException("Količina za dodavanje mora biti pozitivna: " + kolicina);
        }
        this.kolicina += kolicina;
    }

    public void sell(int kolicina) {
        if (kolicina <= 0) {
            throw new IllegalArgumentException("Količina za prodaju mora biti pozitivna: " + kolicina);
        }
        if (kolicina > this.kolicina) {
            throw new IllegalArgumentException("Nema dovoljno na skladištu: traženo " + kolicina
                    + ", dostupno " + this.kolicina);
        }
        this.kolicina -= kolicina;
    }

    public double totalValue() {
        return cijena * kolicina;
    }

    public double totalValue(double popust) {
        if (popust < 0 || popust > 100) {
            throw new IllegalArgumentException("Popust mora biti između 0 i 100 posto: " + popust);
        }
        return totalValue() * (1 - popust / 100);
    }

    public String describe() {
        return String.format(Locale.ROOT, "%s | %s | %.2f EUR | %d kom", sifra, naziv, cijena, kolicina);
    }

    private static String validateName(String naziv) {
        if (naziv == null) {
            throw new IllegalArgumentException("Naziv ne smije biti null.");
        }
        String ocisceni = naziv.strip();
        if (ocisceni.isEmpty()) {
            throw new IllegalArgumentException("Naziv ne smije biti prazan niti samo od razmaka.");
        }
        return ocisceni;
    }

    private static void validatePrice(double cijena) {
        if (Double.isNaN(cijena) || Double.isInfinite(cijena)) {
            throw new IllegalArgumentException("Cijena mora biti konačan broj: " + cijena);
        }
        if (cijena < 0) {
            throw new IllegalArgumentException("Cijena ne smije biti negativna: " + cijena);
        }
    }
}
