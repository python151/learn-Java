import java.util.*;
import java.io.Serializable;

public class Exercise implements Serializable{
    public String name;
    public ArrayList<Equipment> equipment = new ArrayList<Equipment>();
    public Exercise (String aName) {    
        name = aName;
    }
    public void addEquipment(Equipment aEquipment) {
        equipment.add(aEquipment);
    }
}