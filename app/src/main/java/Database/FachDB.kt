package Database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [(Formel_Entity::class)],version = 1)
abstract class FachDB: RoomDatabase()
{
    abstract fun fachDAO() : DAO

    companion object{
        @Volatile
        private var instance: FachDB? = null

        fun getDatabase(context: Context): FachDB {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FachDB::class.java,
                        "Fach_database"
                    ).fallbackToDestructiveMigration().addCallback(callback()).build()
                }
            }
            return instance!!
        }

        private fun callback():androidx.room.RoomDatabase.Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                AsyncTask(instance).execute()
            }
        }
    }



    private class AsyncTask(instance: FachDB?) : android.os.AsyncTask<Void, Void, Void>() {
        private lateinit var fachddao: DAO

        private fun AsncTask(fachDB: FachDB){
            fachddao = fachDB.fachDAO()
        }

        override fun doInBackground(vararg p0: Void?): Void? {

            val formel1 : Formel_Entity=Formel_Entity(0,"mathe","geometrie","hypothenuse","hypothenuse au carré est la somme des carrés des 2 autres cotés","c*c = a*a +b*b)")
            val formel2 : Formel_Entity=Formel_Entity(1,"mathe","geometrie","surface d'un triangle","base multipliée par la hauteur le tout divisé par 2","(b*h)/2)")
            val formel3 : Formel_Entity=Formel_Entity(2,"mathe","Arithmetique","cos","base fgdfdggdgfdgfg par la hauteur le tout divisé par 2","(b*h)/2)")
            val formel4 : Formel_Entity=Formel_Entity(3,"physik","Mechanik","Geschwindigkeit","base fgdfdggdgfdgfg par la hauteur le tout divisé par 2","(b*h)/2)")

            instance!!.fachDAO().addFormel(formel1)
            instance!!.fachDAO().addFormel(formel2)
            instance!!.fachDAO().addFormel(formel3)
            instance!!.fachDAO().addFormel(formel4)
            return null
        }
    }
}