package database.model;

import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "MealSchedule")
public class MealSchedule implements Serializable {
    private SimpleIntegerProperty mealScheduleId = new SimpleIntegerProperty();
    private SimpleIntegerProperty order = new SimpleIntegerProperty();
    private MealType mealType;
    private Meal meal;
    private Diet diet;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "meal_schedule_id", unique = true, precision = 5, scale = 0)
    public int getMealScheduleId() {
        return mealScheduleId.get();
    }
    public void setMealScheduleId(int mealScheduleId) {
        this.mealScheduleId.set(mealScheduleId);
    }

    @Column(name = "meal_schedule_order", length = 100)
    public int getOrder() {
        return order.get();
    }
    public void setOrder(int order) {
        this.order.set(order);
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="meal_id")
    public Meal getMeal() {
        return meal;
    }
    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="meal_type_id")
    public MealType getMealType() {
        return mealType;
    }
    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="diet_id")
    public Diet getDiet() {
        return diet;
    }
    public void setDiet(Diet diet) {
        this.diet = diet;
    }
}
