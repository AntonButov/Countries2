package pro.butovanton.countries2.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import pro.butovanton.countries2.Repo.Model;

@Dao
public interface cDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Model model);

    @Query("DELETE FROM ctable")
    void deleteAll();

    @Query("SELECT * from ctable ORDER BY name ASC")
    List<Model> getAll();
}
