package cz.fel.ds.database.services;

import cz.fel.ds.database.dao.DietDAO;

/**
 * Created by Barush on 15. 5. 2015.
 */
public class DietService
{
    DietDAO dao;

    public DietService(DietDAO dao)
    {
        this.dao = dao;
    }
    //METODY NA SLOZITEJSI PRIKAZY
}
