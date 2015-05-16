package cz.fel.ds.database.model;

import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "Meal")
public class Meal implements Serializable {
    private int mealId;
    private SimpleStringProperty mealName = new SimpleStringProperty();

    public Meal(){
        mealId=-1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meal_id", unique = true, precision = 5, scale = 0)
    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    @Column(name = "food_name", length = 100)
    public String getMealName() {
        return mealName.get();
    }

    public void setMealName(String name) {
        this.mealName.set(name);
    }
}
