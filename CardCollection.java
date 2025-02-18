import java.util.*;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return value + " of " + symbol;
    }
}

public class CardCollection {
    private static final Collection<Card> cardCollection = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Find Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCard();
                case 2 -> findCardsBySymbol();
                case 3 -> displayAllCards();
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addCard() {
        System.out.print("Enter Card Symbol (Heart, Spade, Diamond, Club): ");
        String symbol = scanner.nextLine();
        System.out.print("Enter Card Value (Ace, 2, 3, ... King): ");
        String value = scanner.nextLine();
        cardCollection.add(new Card(symbol, value));
        System.out.println("Card added successfully!");
    }

    private static void findCardsBySymbol() {
        System.out.print("Enter Symbol to search: ");
        String symbol = scanner.nextLine();
        boolean found = false;
        for (Card card : cardCollection) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                System.out.println(card);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cards found for the symbol " + symbol);
        }
    }

    private static void displayAllCards() {
        if (cardCollection.isEmpty()) {
            System.out.println("No cards available.");
        } else {
            System.out.println("All Cards in Collection:");
            for (Card card : cardCollection) {
                System.out.println(card);
            }
        }
    }
}
