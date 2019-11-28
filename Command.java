public class Command {
    private String command, itemName, Stock, inorDecrement;
    Command(String aCommand) {
        command = aCommand;
    }

    public String execute() {
        String[] commandInParts = command.split(" ");
        switch (commandInParts[0]) {
            case "new":
                if (commandInParts.length == 3) {
                    itemName = commandInParts[1];
                    String Stock = commandInParts[2];
                    new Item(itemName, Integer.parseInt(Stock));
                    return "Success";
                }
                return "Invalid Params";

            case "stock":
                if (commandInParts.length == 2) {
                    itemName = commandInParts[1];
                    for (Item item : Archive.items) {
                        if (item.name.equals(itemName)) {
                            return String.valueOf(item.stock);
                        }
                    }
                    return "Item Not Found";
                }
                else if (commandInParts.length == 1) {
                    String returnStatment = "Id Name Stock\n";
                    for (Item item : Archive.items) {
                        returnStatment += item.id +" | "+ item.name +" ......... "+ item.stock  + " \n";
                    }
                    return returnStatment;
                }
                else {
                    return "Params Invalid";
                }

            case "update":
                if (commandInParts.length == 3) {
                    itemName = commandInParts[1];
                    inorDecrement = commandInParts[2];
                    for (Item item : Archive.items) {
                        if (item.name.equals(itemName)) {
                            item.updateStock(Integer.parseInt(inorDecrement));
                            return "Success, stock now "+String.valueOf(item.stock);
                        }
                    } 
                    return "Item Not Found";
                }
                return "Invalid Params";
            
            case "del":
                if (commandInParts.length == 2) {
                    itemName = commandInParts[1];
                    for (Item item : Archive.items) {
                        if (item.name.equals(itemName)) {
                            item.delete();
                            return "Success, Deleted";
                        }
                    }
                    return "Item Not Found";
                }
                return "Invalid Params";

            case "exit":
                System.exit(1);
            

            default:
                break;
        }
        return String.format("Command \"%s\" not recognised", commandInParts[0]) ;
    }
}