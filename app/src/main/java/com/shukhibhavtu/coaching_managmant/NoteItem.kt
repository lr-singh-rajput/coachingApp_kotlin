package com.shukhibhavtu.coaching_managmant

data class NoteItem(


    var name:String,
    var fname:String,
    var mnumber:String,
    var group:String,
    var tfees:String,
    var tpaid:String,
    var noteId: String,
){


    constructor(): this("","","","","","","")
}

