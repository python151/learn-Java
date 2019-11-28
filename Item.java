public class Item {
    private static int total = 0;
    public String name;
    public int stock, id;
    Item(String aName, int aStock) {
        total++;
        id = total;

        stock = aStock;
        name = aName;

        Archive.items.add(this);
    }

    public int updateStock(int inOrDecrement) {
        if (stock > 0) {
            return stock += inOrDecrement;
        }
        return 0;
    }

    public boolean delete() {
        try {
            Archive.items.remove(id-1);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}