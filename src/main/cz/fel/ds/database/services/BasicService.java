package cz.fel.ds.database.services;

import cz.fel.ds.database.model.Exercise;
import cz.fel.ds.database.model.Food;

/**
 * Created by Tom on 16. 5. 2015.
 */
public class BasicService {

    public boolean saveFood(Food food){
        System.out.println(food);
        return true;
    }

    public boolean deleteFood(Food food){
        System.out.println(food);
        return true;
    }

    public boolean saveExcercise(Exercise exercise){
        System.out.println(exercise);
        return true;
    }

    public boolean deleteExcercise(Exercise exercise){
        System.out.println(exercise);
        return true;
    }

}
