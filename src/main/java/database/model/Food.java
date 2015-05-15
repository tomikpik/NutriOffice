package database.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */

@Entity
@Table(name = "Food")
public class Food implements Serializable{
    private int foodId;
    private SimpleStringProperty foodName = new SimpleStringProperty();
    private SimpleFloatProperty energyValue = new SimpleFloatProperty();

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "food_id", unique = true, precision = 5, scale = 0)
    public int getFoodId() {
        return foodId;
    }
    public void setFoodId(int dietId) {
        this.foodId=dietId;
    }

    @Column(name = "energy_value", length = 100)
    public float getEnergyValue() {
        return energyValue.get();
    }
    public void setEnergyValue(float energyValue) {
        this.energyValue.set(energyValue);
    }

    @Column(name = "food_name", length = 100)
    public String getFoodName() {
        return foodName.get();
    }
    public void setFoodName(String name) {
        this.foodName.set(name);
    }

}
