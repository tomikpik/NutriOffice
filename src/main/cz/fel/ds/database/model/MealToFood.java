package cz.fel.ds.database.model;

import javafx.beans.property.SimpleDoubleProperty;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "MealToFood")
public class MealToFood implements Serializable 
{
    private SimpleDoubleProperty quantity = new SimpleDoubleProperty();
    private Meal meal;
    private Food food;

    @Column(name = "quantity", length = 100)
    public double getQuantity() 
    {
        return quantity.get();
    }

    public void setQuantity(double quantity)
    {
        this.quantity.set(quantity);
    }

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "food_id")
    public Food getFood()
    {
        return food;
    }

    public void setFood(Food food)
    {
        this.food = food;
    }

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "meal_id")
    public Meal getMeal()
    {
        return meal;
    }

    public void setMeal(Meal meal)
    {
        this.meal = meal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealToFood)) return false;

        MealToFood that = (MealToFood) o;

        if (!quantity.equals(that.quantity)) return false;
        if (!meal.equals(that.meal)) return false;
        return food.equals(that.food);

    }

    @Override
    public int hashCode() {
        int result = quantity.hashCode();
        result = 31 * result + meal.hashCode();
        result = 31 * result + food.hashCode();
        return result;
    }
}
