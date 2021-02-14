package com.example.myformels.FormelListView


import android.os.Parcel


class FormelListView()  {
    var name: String? = null
    var description: String? = null
    var form: String? = null
    constructor(name: String, description: String, form: String)   :this(){
        this.name = name
        this.description = description
        this.form = form
    }

}
