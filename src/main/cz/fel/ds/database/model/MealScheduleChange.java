package cz.fel.ds.database.model;

import javax.persistence.*;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "MealScheduleChange")
public class MealScheduleChange {
    private Meal meal;
    private Patient patient;
    private MealSchedule mealSchedule;
    private Integer mealScheduleChangeId;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "meal_schedule_change_id", unique = true, precision = 5, scale = 0)
    public Integer getMealScheduleChangeId() {
        return mealScheduleChangeId;
    }
    public void setMealScheduleChangeId(Integer mealScheduleChangeId) {
        this.mealScheduleChangeId = mealScheduleChangeId;
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
    @JoinColumn(name="patient_id")
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="meal_schedule_id")
    public MealSchedule getMealSchedule() {
        return mealSchedule;
    }
    public void setMealSchedule(MealSchedule mealSchedule) {
        this.mealSchedule = mealSchedule;
    }


}
