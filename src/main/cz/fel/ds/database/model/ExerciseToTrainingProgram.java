package cz.fel.ds.database.model;

import javafx.beans.property.SimpleDoubleProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "ExerciseToTrainingProgram")
public class ExerciseToTrainingProgram implements Serializable 
{
    private SimpleDoubleProperty duration = new SimpleDoubleProperty();
    private Exercise exercise;
    private TrainingProgram trainingProgram;

    @Column(name = "duration", length = 100)
    public double getDuration() {
        return duration.get();
    }

    public void setDuration(double duration) 
   {
        this.duration.set(duration);
    }

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_id")
    public Exercise getExercise()
    {
        return exercise;
    }

    public void setExercise(Exercise exercise)
    {
        this.exercise = exercise;
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
