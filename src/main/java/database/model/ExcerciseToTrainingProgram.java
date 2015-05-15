package database.model;

import javafx.beans.property.SimpleFloatProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "ExcerciseToTrainingProgram")
public class ExcerciseToTrainingProgram implements Serializable{
    private SimpleFloatProperty duration = new SimpleFloatProperty();
    private Excercise excercise;
    private TrainingProgram trainingProgram;

    @Column(name = "duration", length = 100)
    public float getDuration() {
        return duration.get();
    }
    public void setDuration(float duration) {
        this.duration.set(duration);
    }

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="excercise_id")
    public Excercise getExcercise() {
        return excercise;
    }
    public void setExcercise(Excercise excercise) {
        this.excercise = excercise;
    }

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="training_program_id")
    public TrainingProgram getTrainingProgram() {
        return trainingProgram;
    }
    public void setTrainingProgram(TrainingProgram trainingProgram) {
        this.trainingProgram = trainingProgram;
    }

}
