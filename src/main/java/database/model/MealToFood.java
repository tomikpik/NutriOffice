package database.model;

import javafx.beans.property.SimpleFloatProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "MealToFood")
public class MealToFood implements Serializable {
    private SimpleFloatProperty quantity = new SimpleFloatProperty();
    private Meal meal;
    private Food food;

    @Column(name = "quantity", length = 100)
    public float getQuantity() {
        return quantity.get();
    }
    public void setQuantity(float quantity) {
        this.quantity.set(quantity);
    }

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="food_id")
    public Food getFood() {
        return food;
    }
    public void setFood(Food food) {
        this.food = food;
    }

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="meal_id")
    public Meal getMeal() {
        return meal;
    }
    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
