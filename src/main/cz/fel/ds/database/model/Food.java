package cz.fel.ds.database.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */

@Entity
@Table(name = "Food")
public class Food implements Serializable {
    private int foodId;
    private SimpleStringProperty foodName = new SimpleStringProperty();
    private SimpleDoubleProperty energyValue = new SimpleDoubleProperty();

    public Food() {
        foodId = -1;
    }

    public Food(String name, Double energyValue) {
        foodId = -1;
        this.setFoodName(name);
        this.setEnergyValue(energyValue);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "food_id", unique = true, precision = 5, scale = 0)
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int dietId) {
        this.foodId = dietId;
    }

    @Column(name = "energy_value", length = 100)
    public double getEnergyValue() {
        return energyValue.get();
    }

    public void setEnergyValue(double energyValue) {
        this.energyValue.set(energyValue);
    }

    @Column(name = "food_name", length = 100)
    public String getFoodName() {
        return foodName.get();
    }

    public void setFoodName(String name) {
        this.foodName.set(name);
    }


    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName=" + foodName +
                ", energyValue=" + energyValue +
                '}';
    }
}
