package Database

import android.content.Context
import android.os.AsyncTask
import android.provider.MediaStore.Images.Media.getBitmap
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myformels.Converters
import com.example.myformels.R

@Database(entities = [(Formel_Entity::class)],version = 1)
@TypeConverters(Converters::class)
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

            //Mathe Geometrie
            val formel1 : Formel_Entity=Formel_Entity(0,"Mathe","geometrie","Seitenhalbierende ","Der Schwerpunkt S teilt jede Seiten-halbierende im Verhältnis", "drawable/geometrie/dreieck")
            val formel2 : Formel_Entity=Formel_Entity(1,"mathe","geometrie","Innenwinkel","Die Summe der Innenwinkel eines Vierecks beträgt 360°","drawable/geometrie/Innenwinkel")
            val formel3 : Formel_Entity=Formel_Entity(2,"mathe","geometrie","Kreissektor","Tangente und Berührungsradius sind zueinander senkrecht.","drawable/geometrie/Kreissektor")
            val formel4 : Formel_Entity=Formel_Entity(3,"mathe","geometrie","Geschwindigkeit","Tangente und Berührungsradius sind zueinander senkrecht.","drawable/geometrie/Geschwindigkeit")
            val formel5 : Formel_Entity=Formel_Entity(4,"Mathe","geometrie"," Vielecksumkreis","Umkreis un Inkreis eines regelmößigen Vielecks haben den gleichen Mittelpunkt.","drawable/geometrie/Vielecksumkreis")

            //Math Arithmetik

            val formel6 : Formel_Entity=Formel_Entity(5,"Mathe","Aritmetik","Punktrichtungsgleichung ","Gerade durch den Punkt mit dem Richtungsvektor :", "drawable/arithmetik/Punktrichtungsgleichung")
            val formel7 : Formel_Entity=Formel_Entity(6,"mathe","Aritmetik","Zweipunktegleichung","Schreibweise unter Verwendung von Koordinaten (im raum bzw. in der xy-Ebene)","drawable/arithmetik/Zweipunktegleichung")
            val formel8 : Formel_Entity=Formel_Entity(7,"mathe","Aritmetik","Lagebeziehung zweier Geraden","g und h liegen in einer Ebene genau dann, wen die Vektoren , und linear abhängig sind","drawable/arithmetik/lagebeziehungZweierGeraden")
            val formel9 : Formel_Entity=Formel_Entity(8,"mathe","Aritmetik","Vektorprodukt","Unter dem Vektorprodukt x zweier Vektoren und versteht man einen Vektor mit folgenden Eigenschaften","drawable/arithmetik/Vektorprodukt")
            val formel10 : Formel_Entity=Formel_Entity(9,"Mathe","Aritmetik"," Flächeninhalte", "Flächeninhalt des von den Vektoren und aufgespannten Parallelogramms ABCD:","drawable/arithmetik/Flächeninhalte")

            instance!!.fachDAO().addFormel(formel1)
            instance!!.fachDAO().addFormel(formel2)
            instance!!.fachDAO().addFormel(formel3)
            instance!!.fachDAO().addFormel(formel4)
            instance!!.fachDAO().addFormel(formel5)

            //Mathe Arithmetik
            instance!!.fachDAO().addFormel(formel6)
            instance!!.fachDAO().addFormel(formel7)
            instance!!.fachDAO().addFormel(formel8)
            instance!!.fachDAO().addFormel(formel9)
            instance!!.fachDAO().addFormel(formel10)
            return null
        }
    }
}