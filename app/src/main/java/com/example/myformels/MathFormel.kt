package com.example.myformels


import android.os.Parcel


class Formel()  {
    var name: String? = null
    var description: String? = null


    constructor(name: String, description: String)   :this(){
        this.name = name
        this.description = description
    }


}
