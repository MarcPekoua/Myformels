package Database

import android.content.Context
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

            //Mathe Geometrie
            val formel1 : Formel_Entity=Formel_Entity(0,"Mathe","geometrie","Seitenhalbierende ","Der Schwerpunkt S teilt jede Seiten-halbierende im Verhältnis", "S = (1/2)(sqr(2((a*a)+(b*b))-a*a)), wobei a, b und cc sind die seite.")
            val formel2 : Formel_Entity=Formel_Entity(1,"mathe","geometrie","Innenwinkel","Die Summe der Innenwinkel eines Vierecks beträgt 180°","Die summe der Innenwinkel eines Dreiecks beträgt 180°")
            val formel3 : Formel_Entity=Formel_Entity(2,"mathe","geometrie","Kreissektor","Tangente und Berührungsradius sind zueinander senkrecht.","u = (b + (2*r))")
            val formel4 : Formel_Entity=Formel_Entity(3,"mathe","geometrie","Geschwindigkeit","Tangente und Berührungsradius sind zueinander senkrecht.","Geschwindigkeit")
            val formel5 : Formel_Entity=Formel_Entity(4,"Mathe","geometrie"," Vielecksumkreis","Umkreis un Inkreis eines regelmößigen Vielecks haben den gleichen Mittelpunkt.","Umkreis un Inkreis eines regelmößigen Vielecks haben den gleichen Mittelpunkt.")

            //Math Arithmetik

            val formel6 : Formel_Entity=Formel_Entity(5,"Mathe","Aritmetik","Punktrichtungsgleichung ","Gerade durch den Punkt mit dem Richtungsvektor :", " ... ")
            val formel7 : Formel_Entity=Formel_Entity(6,"mathe","Aritmetik","Zweipunktegleichung","Schreibweise unter Verwendung von Koordinaten (im raum bzw. in der xy-Ebene)"," ... ")
            val formel8 : Formel_Entity=Formel_Entity(7,"mathe","Aritmetik","Lagebeziehung zweier Geraden","g und h liegen in einer Ebene genau dann, wen die Vektoren , und linear abhängig sind","...")
            val formel9 : Formel_Entity=Formel_Entity(8,"mathe","Aritmetik","Vektorprodukt","Unter dem Vektorprodukt x zweier Vektoren und versteht man einen Vektor mit folgenden Eigenschaften"," ... ")
            val formel10 : Formel_Entity=Formel_Entity(9,"Mathe","Aritmetik"," Flächeninhalte", "Flächeninhalt des von den Vektoren und aufgespannten Parallelogramms ABCD:"," ... ")

            //Mathe Folgen und Reihen, Grenzwerte
            val formel11 : Formel_Entity=Formel_Entity(10,"Mathe","Folgen und Reihen, Grenzwerte","n-te Partialsumme ","Jede nicht konvergente Zahlenfolge ist divergent.", " ... ")
            val formel12 : Formel_Entity=Formel_Entity(11,"mathe","Folgen und Reihen, Grenzwerte","(unendliche) Reihe","Eine Reihe ist konvergent, wenn ihre Partialsummen-folge gegen s konvergiert"," ... ")
            val formel13 : Formel_Entity=Formel_Entity(12,"mathe","Folgen und Reihen, Grenzwerte","Zahlenfolge","Das Glied gibt zugleich eine Bildungsvorschrift der Folge an"," ... ")
            val formel14 : Formel_Entity=Formel_Entity(13,"mathe","Folgen und Reihen, Grenzwerte","Grenzwertsätze","Eine Zahl g heißt Grenzwert der Funktion f für x gegen , wenn es zu jeder vorgegebenen positiven Zahl eine Zahl > 0 gibt, sodass | f (x) - g | < für alle x mit | x - | < und ."," ... ")
            val formel15 : Formel_Entity=Formel_Entity(14,"Mathe","Folgen und Reihen, Grenzwerte"," Stetigkeit", "Eine Funktion f heißt an einer Stelle stetig, wenn der Grenzwert von f an der Stelle existiert und mit dem Funtionswert f () übereinstimmt"," ... ")

            //Physik Mechanik
            val formel16 : Formel_Entity=Formel_Entity(15,"physik","Mechanik","Dichte ","Masse divizieren durch Volumen ist die Dichte", "D = (m/V)")
            val formel17 : Formel_Entity=Formel_Entity(16,"physik","Mechanik","Druck","der Druck ist Kraft durch die Flaäche","p = (F/A)")
            val formel18 : Formel_Entity=Formel_Entity(17,"physik","Mechanik","Schweredruck","Um der Schweredruck zu haben, multipliieren man die Masse und die Fallbeschleunigung und divisieren durch die Fläche","p = (m*g)/A")
            val formel19 : Formel_Entity=Formel_Entity(18,"physik","Mechanik","potentielle Energie ","eines Körpers in der Nähe der Erdoberfläche."," ... ")
            val formel20 : Formel_Entity=Formel_Entity(19,"physik","Mechanik"," kinetische Energie ","Energie der Bewegung","drawable/mechanik/kinetischeEnergie ")

            //Physik Mechanik
            val formel21 : Formel_Entity=Formel_Entity(20,"physik","Elektrizitätslehre","elektrische Stromstärke ","Unter der Bedingung eines stationären Stromes", " ... ")
            val formel22 : Formel_Entity=Formel_Entity(21,"physik","Elektrizitätslehre","ohmsches Gesetz","Unter der Bedingung = konstant"," ... ")
            val formel23 : Formel_Entity=Formel_Entity(22,"physik","Elektrizitätslehre","coulombsches Gesetz","Unter der Bedingung, dass Punktladungen vorliegen"," ... ")
            val formel24 : Formel_Entity=Formel_Entity(23,"physik","Elektrizitätslehre","elektrische Feldstärke ","Unter der Bedingung eines homogenen elektrischen Feldes"," ... ")
            val formel25 : Formel_Entity=Formel_Entity(24,"physik","Elektrizitätslehre"," Eigenfrequenz f eines elektrischen Schwingungkreises ","Unter der Bedingung einer freien und ungedämpften Schwingung (R = 0)"," ... ")

            //Physik Wärmelehre
            val formel26 : Formel_Entity=Formel_Entity(25,"physik","Wärmelehre","Schmelzwärme","spezifische Schmelzwärme und Masse multipliziert", " ... ")
            val formel27 : Formel_Entity=Formel_Entity(26,"physik","Wärmelehre","Verdampfungswärme","spezifische Verdampfungswärme und Masse multipliziert"," ... ")
            val formel28 : Formel_Entity=Formel_Entity(27,"physik","Wärmelehre","Verbrennungswärme","Heizwert und Masse multipliziert"," ... ")
            val formel29 : Formel_Entity=Formel_Entity(28,"physik","Wärmelehre","Wärmestrom","Wärme durch Zeit","drawable/wärmelehre/Wärmestrom  ")
            val formel30 : Formel_Entity=Formel_Entity(29,"physik","Wärmelehre","Wärmequellen","abgegebene nutzbare Wärme durch die Zeit"," ...  ")

            //Math Geometrie
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

            //Mathe Folgen und Reihen, Grenzwerte
            instance!!.fachDAO().addFormel(formel11)
            instance!!.fachDAO().addFormel(formel12)
            instance!!.fachDAO().addFormel(formel13)
            instance!!.fachDAO().addFormel(formel14)
            instance!!.fachDAO().addFormel(formel15)

            //Physik Mechanik
            instance!!.fachDAO().addFormel(formel16)
            instance!!.fachDAO().addFormel(formel17)
            instance!!.fachDAO().addFormel(formel18)
            instance!!.fachDAO().addFormel(formel19)
            instance!!.fachDAO().addFormel(formel20)

            //Physik Elektrizitätslehre
            instance!!.fachDAO().addFormel(formel21)
            instance!!.fachDAO().addFormel(formel22)
            instance!!.fachDAO().addFormel(formel23)
            instance!!.fachDAO().addFormel(formel24)
            instance!!.fachDAO().addFormel(formel25)

            //Physik Wärmelehre
            instance!!.fachDAO().addFormel(formel26)
            instance!!.fachDAO().addFormel(formel27)
            instance!!.fachDAO().addFormel(formel28)
            instance!!.fachDAO().addFormel(formel29)
            instance!!.fachDAO().addFormel(formel30)
            return null
        }
    }
}