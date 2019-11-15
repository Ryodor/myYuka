package fr.lucas.barcodescanner

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Emp_Entity (

    @PrimaryKey(autoGenerate = true)
    var emp_id : Int,

    @ColumnInfo (name = "EMP_NAME")
    var emp_name : String = "",

    @ColumnInfo (name = "EMP_IMG")
    var emp_img : String = ""
)