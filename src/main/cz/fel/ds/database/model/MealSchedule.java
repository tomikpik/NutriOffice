package cz.fel.ds.database.model;

import javafx.beans.property.SimpleIntegerProperty;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "MealSchedule")
public class MealSchedule implements Serializable
{
    private SimpleIntegerProperty mealScheduleId = new SimpleIntegerProperty();
    private SimpleIntegerProperty order = new SimpleIntegerProperty();
    private MealType mealType;
    private Meal meal;
    private Diet diet;

    public MealSchedule(){
        mealScheduleId.set(-1);
    }

    public MealSchedule(int order, MealType mealType, Meal meal, Diet diet)
    {
        this.order = new SimpleIntegerProperty(order);
        this.mealType = mealType;
        this.meal = meal;
        this.diet = diet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meal_schedule_id", unique = true, precision = 5, scale = 0)
    public int getMealScheduleId()
    {
        return mealScheduleId.get();
    }

    public void setMealScheduleId(int mealScheduleId)
    {
        this.mealScheduleId.set(mealScheduleId);
    }

    @Column(name = "meal_schedule_order", length = 100)
    public int getOrder()
    {
        return order.get();
    }

    public void setOrder(int order)
    {
        this.order.set(order);
    }
    @ManyToOne
    //@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "meal_id")
    public Meal getMeal()
    {
        return meal;
    }

    public void setMeal(Meal meal)
    {
        this.meal = meal;
    }
    @ManyToOne
    //@ManyToOne(cascade = CascadeType.)
    @JoinColumn(name = "meal_type_id")
    public MealType getMealType()
    {
        return mealType;
    }

    public void setMealType(MealType mealType)
    {
        this.mealType = mealType;
    }

    @ManyToOne
    //@ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "diet_id")
    public Diet getDiet()
    {
        return diet;
    }

    public void setDiet(Diet diet)
    {
        this.diet = diet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealSchedule)) return false;

        MealSchedule that = (MealSchedule) o;

        if (!mealScheduleId.equals(that.mealScheduleId)) return false;
        if (!order.equals(that.order)) return false;
        if (!mealType.equals(that.mealType)) return false;
        if (!meal.equals(that.meal)) return false;
        return diet.equals(that.diet);

    }

    @Override
    public int hashCode() {
        int result = mealScheduleId.hashCode();
        result = 31 * result + order.hashCode();
        result = 31 * result + mealType.hashCode();
        result = 31 * result + meal.hashCode();
        result = 31 * result + diet.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MealSchedule{" +
                "mealScheduleId=" + mealScheduleId +
                ", order=" + order +
                ", mealType=" + mealType +
                ", meal=" + meal +
                ", diet=" + diet +
                '}';
    }
}
