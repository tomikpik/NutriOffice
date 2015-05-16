package cz.fel.ds.database.model;

import javafx.beans.property.SimpleFloatProperty;

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
    private SimpleFloatProperty height = new SimpleFloatProperty();
    private SimpleFloatProperty weight = new SimpleFloatProperty();
    private SimpleFloatProperty fat = new SimpleFloatProperty();
    private SimpleFloatProperty waist = new SimpleFloatProperty();
    private SimpleFloatProperty hip = new SimpleFloatProperty();
    private SimpleFloatProperty chest = new SimpleFloatProperty();
    private Date date;
    private Patient patient;


    @Column(name = "height", length = 100)
    public float getHeight()
    {
        return height.get();
    }

    public void setHeight(float height)
    {
        this.height.set(height);
    }

    @Column(name = "weight", length = 100)
    public float getWeight()
    {
        return weight.get();
    }

    public void setWeight(float weight)
    {
        this.weight.set(weight);
    }

    @Column(name = "fat", length = 100)
    public float getFat()
    {
        return fat.get();
    }

    public void setFat(float fat)
    {
        this.fat.set(fat);
    }

    @Column(name = "waist", length = 100)
    public float getWaist()
    {
        return waist.get();
    }

    public void setWaist(float waist)
    {
        this.waist.set(waist);
    }

    @Column(name = "hip", length = 100)
    public float getHip()
    {
        return hip.get();
    }

    public void setHip(float hip)
    {
        this.hip.set(hip);
    }

    @Column(name = "chest", length = 100)
    public float getChest()
    {
        return chest.get();
    }

    public void setChest(float chest)
    {
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
