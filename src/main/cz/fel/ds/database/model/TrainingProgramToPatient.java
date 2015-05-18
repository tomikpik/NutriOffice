package cz.fel.ds.database.model;

import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "TrainingProgramToPatient")
public class TrainingProgramToPatient implements Serializable
{
    private SimpleIntegerProperty day = new SimpleIntegerProperty();
    private TrainingProgram trainingProgram;
    private Patient patient;

    public TrainingProgramToPatient()
    {

    }

    public TrainingProgramToPatient(Patient p, TrainingProgram trainingProgram)
    {
        this.patient = p;
        this.trainingProgram = trainingProgram;
    }

    @Column(name = "day", length = 100)
    public int getDay()
    {
        return day.get();
    }

    public void setDay(int day)
    {
        this.day.set(day);
    }

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_program_id")
    public TrainingProgram getTrainingProgram()
    {
        return trainingProgram;
    }

    public void setTrainingProgram(TrainingProgram trainingProgram)
    {
        this.trainingProgram = trainingProgram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainingProgramToPatient)) return false;

        TrainingProgramToPatient that = (TrainingProgramToPatient) o;

        if (!day.equals(that.day)) return false;
        if (!trainingProgram.equals(that.trainingProgram)) return false;
        return patient.equals(that.patient);

    }

    @Override
    public int hashCode() {
        int result = day.hashCode();
        result = 31 * result + trainingProgram.hashCode();
        result = 31 * result + patient.hashCode();
        return result;
    }
}
