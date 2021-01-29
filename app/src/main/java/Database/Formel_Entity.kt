package Database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
open class Fach(
    @PrimaryKey var formelId: Int? = null,
    var test :Int
)

@Entity(tableName = "Formel_Entity")
data class Formel_Entity (

            val formelFach: String,

            val formelFachGroup:String,

            val formelName: String,

            val formelText: String ,

            val formelForm: String
):Fach(test=0){


    constructor(

        id:Int,
        formfach: String,
        formgroup: String,
        formname: String,
        formtext: String,
        formform: String
    ) : this(formfach,formgroup,formname,formtext,formform) {

        super<Fach>.formelId=id
    }
}

