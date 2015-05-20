package cz.fel.ds.database.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "MealScheduleChange")
public class MealScheduleChange implements Serializable
{
    private Meal meal;
    private Patient patient;
    private MealSchedule mealSchedule;
    private Integer mealScheduleChangeId;

    public MealScheduleChange(){
        mealScheduleChangeId=-1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meal_schedule_change_id", unique = true, precision = 5, scale = 0)
    public Integer getMealScheduleChangeId()
    {
        return mealScheduleChangeId;
    }

    public void setMealScheduleChangeId(Integer mealScheduleChangeId)
    {
        this.mealScheduleChangeId = mealScheduleChangeId;
    }

    @OneToOne
    @JoinColumn(name = "meal_id")
    public Meal getMeal()
    {
        return meal;
    }

    public void setMeal(Meal meal)
    {
        this.meal = meal;
    }

    @OneToOne
    @JoinColumn(name = "patient_id")
    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    @OneToOne
    @JoinColumn(name = "meal_schedule_id")
    public MealSchedule getMealSchedule()
    {
        return mealSchedule;
    }

    public void setMealSchedule(MealSchedule mealSchedule)
    {
        this.mealSchedule = mealSchedule;
    }

    @Override
    public String toString() {
        return "MealScheduleChange{" +
                "meal=" + meal +
                ", patient=" + patient +
                ", mealSchedule=" + mealSchedule +
                ", mealScheduleChangeId=" + mealScheduleChangeId +
                '}';
    }
}
