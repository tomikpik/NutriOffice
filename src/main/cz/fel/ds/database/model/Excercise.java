package cz.fel.ds.database.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "Excercise")
public class Excercise implements Serializable {
    private int excerciseId;
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleDoubleProperty kjkgmin = new SimpleDoubleProperty();

    public Excercise() {
        excerciseId = -1;
    }

    public Excercise(String name, Double kjkgmin) {
        excerciseId = -1;
        this.setName(name);
        this.setKjkgmin(kjkgmin);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "excercise_id", unique = true, precision = 5, scale = 0)
    public int getExcerciseId() {
        return excerciseId;
    }

    public void setExcerciseId(int excerciseId) {
        this.excerciseId = excerciseId;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Column(name = "kjkgmin", length = 100)
    public double getKjkgmin() {
        return kjkgmin.get();
    }

    public void setKjkgmin(double kjkgmin) {
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
