public class InventoryView {

    public void displayInventory(Inventory inventory) {
        System.out.println("\n=== Inventory ===");
        System.out.println("Gold: " + inventory.getGold());
        System.out.println("Weight: " + String.format("%.1f", inventory.getCurrentWeight())
                + "/" + String.format("%.1f", inventory.getMaxWeight()));
        System.out.println("Items (" + inventory.getItemCount() + "):");
        if (inventory.getItems().isEmpty()) {
            System.out.println("  (empty)");
        } else {
            for (int i = 0; i < inventory.getItems().size(); i++) {
                Item item = inventory.getItems().get(i);
                System.out.println("  " + (i + 1) + ". " + item.getName() + " [" + item.getType() + "]");
            }
        }
        System.out.println("=================");
    }

    public void displayItemAdded(Item item) {
        System.out.println(item.getName() + " added to inventory.");
    }

    public void displayItemRemoved(Item item) {
        System.out.println(item.getName() + " removed from inventory.");
    }

    public void displayInventoryFull() {
        System.out.println("Inventory is full! Cannot carry more items.");
    }

    public void displayItemDetail(Item item) {
        System.out.println("\n--- Item Detail ---");
        System.out.println("Name: " + item.getName());
        System.out.println("Type: " + item.getType());
        System.out.println("Description: " + item.getDescription());
        System.out.println("Weight: " + String.format("%.1f", item.getWeight()));
        System.out.println("Value: " + item.getValue() + " gold");
    }

    public int getItemChoice(int maxItems) {
        System.out.print("Choose an item (1-" + maxItems + ") or 0 to cancel: ");
        return new java.util.Scanner(System.in).nextInt();
    }
}
