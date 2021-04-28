package com.example.employeesxmlpullparsertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import java.io.IOException

private var TAG ="Employees"
class Employee{
    var id:Int= 0
    var name:String = ""
    var salary:Float =  0.toFloat()
    override fun toString(): String {
        return """
              employee id : $id
              employee name : $name
              employee salary : $salary
               """.trimIndent()
    }
}
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list_item = findViewById<ListView>(R.id.list_item)

        var employees :List<Employee>? = null

        try {
            var paeser = XmlPullParserHandler()
            val employeesDetailes = assets.open("employee.txt")
            employees = paeser.parse(employeesDetailes)
            val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,employees)
            list_item.adapter = adapter

        }catch (e:IOException){
            e.printStackTrace()
        }
    }
}