package cz.fel.ds.database.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "Exercise")
public class Exercise implements Serializable
{
    private int exerciseId;
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleDoubleProperty kjkgmin = new SimpleDoubleProperty();

    public Exercise() {
        excerciseId = -1;
    }

    public Exercise(String name, Double kjkgmin) {
        excerciseId = -1;
        this.setName(name);
        this.setKjkgmin(kjkgmin);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exercise_id", unique = true, precision = 5, scale = 0)
    public int getExerciseId()
    {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId)
    {
        this.exerciseId = exerciseId;
    }

    @Column(name = "name", length = 100)
    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    @Column(name = "kjkgmin", length = 100)
    public double getKjkgmin() 
    {
        return kjkgmin.get();
    }

    public void setKjkgmin(double kjkgmin) 
   {
        this.kjkgmin.set(kjkgmin);
    }

    @Override
    public String toString() {
        return "Excercise{" +
                "excerciseId=" + excerciseId +
                ", name=" + name +
                ", kjkgmin=" + kjkgmin +
                '}';
    }
}
