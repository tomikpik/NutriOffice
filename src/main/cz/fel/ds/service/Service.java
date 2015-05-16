package cz.fel.ds.service;

import cz.fel.ds.database.model.Excercise;
import cz.fel.ds.database.model.Food;

/**
 * Created by Tom on 16. 5. 2015.
 */
public class Service {

    public boolean saveFood(Food food){
        System.out.println(food);
        return true;
    }

    public boolean deleteFood(Food food){
        System.out.println(food);
        return true;
    }

    public boolean saveExcercise(Excercise excercise){
        System.out.println(excercise);
        return true;
    }

    public boolean deleteExcercise(Excercise excercise){
        System.out.println(excercise);
        return true;
    }

}
