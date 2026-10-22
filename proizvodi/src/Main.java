import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Proizvod kava = new Proizvod("  Kava mljevena  ", 4.20, 30);
        Proizvod caj = new Proizvod("Čaj od kamilice", 3.50);
        Proizvod mlijeko = new Proizvod("Mlijeko 1L", 1.19, 50);

        System.out.println("--- Početno stanje ---");
        printAll(kava, caj, mlijeko);
        System.out.println("Broj stvorenih proizvoda: " + Proizvod.getProductCount());

        System.out.println();
        System.out.println("--- Promjene stanja ---");
        caj.add(12);
        kava.sell(5);
        mlijeko.sell(20);
        mlijeko.setPrice(1.29);
        kava.setName("   Kava u zrnu ");
        printAll(kava, caj, mlijeko);

        System.out.println();
        System.out.println("--- Ukupna vrijednost ---");
        System.out.println(caj.getName() + ": " + formatAmount(caj.totalValue()) + " EUR");
        System.out.println(caj.getName() + " uz 10 % popusta: " + formatAmount(caj.totalValue(10)) + " EUR");
        System.out.println(kava.getName() + ": " + formatAmount(kava.totalValue()) + " EUR");
        System.out.println(kava.getName() + " uz 25 % popusta: " + formatAmount(kava.totalValue(25)) + " EUR");

        System.out.println();
        System.out.println("--- Pokušaji neispravnih vrijednosti ---");
        try {
            caj.sell(1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }
        try {
            kava.setPrice(-2.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }
        try {
            mlijeko.setName("   ");
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }
        try {
            mlijeko.add(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }
        try {
            new Proizvod(null, 2.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }
        try {
            new Proizvod("Sok od jabuke", -1.0, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }

        System.out.println();
        System.out.println("--- Stanje nakon odbijenih pokušaja (nepromijenjeno) ---");
        printAll(kava, caj, mlijeko);
        System.out.println("Broj stvorenih proizvoda: " + Proizvod.getProductCount());
    }

    private static void printAll(Proizvod... proizvodi) {
        for (Proizvod p : proizvodi) {
            System.out.println(p.describe());
        }
    }

    private static String formatAmount(double vrijednost) {
        return String.format(Locale.ROOT, "%.2f", vrijednost);
    }
}
