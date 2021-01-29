package Database

import androidx.room.*
import kotlinx.coroutines.selects.select


@Dao
interface DAO {
    @Insert (onConflict = 3) // 3 is the value of *onConflictStrategy.ABORT
    fun addFormel(formel:Formel_Entity)

    @Query ( "SELECT * FROM Formel_Entity")
    fun getAll():List<Formel_Entity>

    @Query ( "SELECT * FROM Formel_Entity WHERE formelFachGroup = :Fachgroup")
    fun getByGroup(Fachgroup:String):List<Formel_Entity>
}