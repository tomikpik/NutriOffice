package cz.fel.ds.database.model;

import javafx.beans.property.SimpleDoubleProperty;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    @ManyToOne(cascade = CascadeType.REMOVE)
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
    @ManyToOne(cascade = {CascadeType.REMOVE})
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
        if (!(o instanceof ExerciseToTrainingProgram)) return false;

        ExerciseToTrainingProgram that = (ExerciseToTrainingProgram) o;

        if (!duration.equals(that.duration)) return false;
        if (!exercise.equals(that.exercise)) return false;
        return trainingProgram.equals(that.trainingProgram);

    }

    @Override
    public int hashCode() {
        int result = duration.hashCode();
        result = 31 * result + exercise.hashCode();
        result = 31 * result + trainingProgram.hashCode();
        return result;
    }
}
