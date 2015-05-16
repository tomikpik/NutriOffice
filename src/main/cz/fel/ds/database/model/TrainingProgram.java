package cz.fel.ds.database.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tom on 15. 5. 2015.
 */
@Entity
@Table(name = "TrainingProgram")
public class TrainingProgram implements Serializable
{
    private int trainingProgramId;
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleIntegerProperty type = new SimpleIntegerProperty();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "training_program_id", unique = true, precision = 5, scale = 0)
    public int getTrainingProgramId()
    {
        return trainingProgramId;
    }

    public void setTrainingProgramId(int trainingProgramId)
    {
        this.trainingProgramId = trainingProgramId;
    }

    @Column(name = "type", length = 100)
    public int getType()
    {
        return type.get();
    }

    public void setType(int type)
    {
        this.type.set(type);
    }

    @Column(name = "name", length = 100)
    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }
}
