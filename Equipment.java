import java.io.Serializable;

public class Equipment implements Serializable{
    public String name;
    public boolean required;
    public Equipment(String aName, boolean aRequired) {
        name = aName;
        required = aRequired;
    }
}