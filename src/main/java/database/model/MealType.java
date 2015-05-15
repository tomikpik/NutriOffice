package database.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "MealType")
public class MealType {

    private SimpleIntegerProperty mealTypeId = new SimpleIntegerProperty();
    private SimpleIntegerProperty mealType = new SimpleIntegerProperty();
    private SimpleStringProperty mealTypeName = new SimpleStringProperty();

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "meal_type_id", unique = true, precision = 5, scale = 0)
    public int getMealTypeId() {
        return mealTypeId.get();
    }
    public void setMealTypeId(int mealTypeId) {
        this.mealTypeId.set(mealTypeId);
    }

    @Column(name = "meal_type_name", length = 100)
    public String getMealTypeName() {
        return mealTypeName.get();
    }
    public void setMealTypeName(String mealTypeName) {
        this.mealTypeName.set(mealTypeName);
    }

    @Column(name = "meal_type", length = 100)
    public int getMealType() {
        return mealType.get();
    }
    public void setMealType(int mealType) {
        this.mealType.set(mealType);
    }

}
