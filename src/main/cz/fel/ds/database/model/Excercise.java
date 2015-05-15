package cz.fel.ds.database.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "Excercise")
public class Excercise implements Serializable{
    private int excerciseId;
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleFloatProperty kjkgmin = new SimpleFloatProperty();

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "excercise_id", unique = true, precision = 5, scale = 0)
    public int getExcerciseId() {
        return excerciseId;
    }
    public void setExcerciseId(int excerciseId) {
        this.excerciseId=excerciseId;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }

    @Column(name = "kjkgmin", length = 100)
    public float getKjkgmin() {
        return kjkgmin.get();
    }
    public void setKjkgmin(float kjkgmin) {
        this.kjkgmin.set(kjkgmin);
    }
}
