package cz.fel.ds.database.model;

import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "TrainingToPatientProgram")
public class TrainingProgramToPatient implements Serializable
{
    private SimpleIntegerProperty day = new SimpleIntegerProperty();
    private TrainingProgram trainingProgram;
    private Patient patient;

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
}
