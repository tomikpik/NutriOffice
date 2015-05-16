package cz.fel.ds.database.model;

import javafx.beans.property.SimpleFloatProperty;
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
    private SimpleFloatProperty kjkgmin = new SimpleFloatProperty();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exercise_id", unique = true, precision = 5, scale = 0)
    public int getExcerciseId()
    {
        return exerciseId;
    }

    public void setExcerciseId(int exerciseId)
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
    public float getKjkgmin()
    {
        return kjkgmin.get();
    }

    public void setKjkgmin(float kjkgmin)
    {
        this.kjkgmin.set(kjkgmin);
    }
}
