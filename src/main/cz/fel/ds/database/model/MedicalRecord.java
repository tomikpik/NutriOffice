package cz.fel.ds.database.model;

import javafx.beans.property.SimpleDoubleProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tom on 15. 5. 2015.
 */

@Entity
@Table(name = "MedicalRecords")
public class MedicalRecord implements Serializable 
{
    private SimpleDoubleProperty height = new SimpleDoubleProperty();
    private SimpleDoubleProperty weight = new SimpleDoubleProperty();
    private SimpleDoubleProperty fat = new SimpleDoubleProperty();
    private SimpleDoubleProperty waist = new SimpleDoubleProperty();
    private SimpleDoubleProperty hip = new SimpleDoubleProperty();
    private SimpleDoubleProperty chest = new SimpleDoubleProperty();
    private Date date;
    private Patient patient;


    @Column(name = "height", length = 100)

    public double getHeight() {
        return height.get();
    }

    public void setHeight(double height) {

        this.height.set(height);
    }

    @Column(name = "weight", length = 100)

    public double getWeight() {
        return weight.get();
    }

    public void setWeight(double weight) {

        this.weight.set(weight);
    }

    @Column(name = "fat", length = 100)

    public double getFat() {
        return fat.get();
    }

    public void setFat(double fat) {

        this.fat.set(fat);
    }

    @Column(name = "waist", length = 100)

    public double getWaist() {
        return waist.get();
    }

    public void setWaist(double waist) {

        this.waist.set(waist);
    }

    @Column(name = "hip", length = 100)

    public double getHip() {
        return hip.get();
    }

    public void setHip(double hip) {

        this.hip.set(hip);
    }

    @Column(name = "chest", length = 100)

    public double getChest() {
        return chest.get();
    }

    public void setChest(double chest) {

        this.chest.set(chest);
    }

    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "medical_record_date", length = 50)
    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Id
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "patient_id")
    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }
}
