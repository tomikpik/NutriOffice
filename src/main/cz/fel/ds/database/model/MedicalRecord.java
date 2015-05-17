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
    private Date date = new Date();
    private Patient patient;

    public MedicalRecord(){}

    public MedicalRecord(Patient p,Date d){
        this.patient=p;
        this.date=d;
    }

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
        this.date.setTime(date.getTime());
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalRecord)) return false;

        MedicalRecord that = (MedicalRecord) o;

        if (!height.equals(that.height)) return false;
        if (!weight.equals(that.weight)) return false;
        if (!fat.equals(that.fat)) return false;
        if (!waist.equals(that.waist)) return false;
        if (!hip.equals(that.hip)) return false;
        if (!chest.equals(that.chest)) return false;
        if (!date.equals(that.date)) return false;
        return patient.equals(that.patient);

    }

    @Override
    public int hashCode() {
        int result = height.hashCode();
        result = 31 * result + weight.hashCode();
        result = 31 * result + fat.hashCode();
        result = 31 * result + waist.hashCode();
        result = 31 * result + hip.hashCode();
        result = 31 * result + chest.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + patient.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "height=" + height +
                ", weight=" + weight +
                ", fat=" + fat +
                ", waist=" + waist +
                ", hip=" + hip +
                ", chest=" + chest +
                ", date=" + date +
                ", patient=" + patient +
                '}';
    }
}
