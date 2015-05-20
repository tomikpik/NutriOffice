package cz.fel.ds.database.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "MealType")
public class MealType implements Serializable
{

    private SimpleIntegerProperty mealTypeId = new SimpleIntegerProperty();
    private SimpleIntegerProperty mealType = new SimpleIntegerProperty();
    private SimpleStringProperty mealTypeName = new SimpleStringProperty();

    public MealType(){
        setMealTypeId(-1);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meal_type_id", unique = true, precision = 5, scale = 0)
    public int getMealTypeId()
    {
        return mealTypeId.get();
    }

    public void setMealTypeId(int mealTypeId)
    {
        this.mealTypeId.set(mealTypeId);
    }

    @Column(name = "meal_type_name", length = 100)
    public String getMealTypeName()
    {
        return mealTypeName.get();
    }

    public void setMealTypeName(String mealTypeName)
    {
        this.mealTypeName.set(mealTypeName);
    }

    @Column(name = "meal_type", length = 100)
    public int getMealType()
    {
        return mealType.get();
    }

    public void setMealType(int mealType)
    {
        this.mealType.set(mealType);
    }

    @Override
    public String toString() {
        return "MealType{" +
                "mealTypeId=" + mealTypeId +
                ", mealType=" + mealType +
                ", mealTypeName=" + mealTypeName +
                '}';
    }
}
