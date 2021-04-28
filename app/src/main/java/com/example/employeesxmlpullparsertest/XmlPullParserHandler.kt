package com.example.employeesxmlpullparsertest

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.lang.Exception

class XmlPullParserHandler {
    val employees = ArrayList<Employee>()
    var employee:Employee? = null
    var textView:String =""

    fun parse(inputStream: InputStream):List<Employee>
    {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagname = parser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> if (tagname.equals("employee", ignoreCase = true)) {
                        // create a new instance of employee
                        employee = Employee()
                    }
                    XmlPullParser.TEXT -> textView = parser.text
                    XmlPullParser.END_TAG -> if (tagname.equals("employee", ignoreCase = true)) {
                        // add employee object to list
                        employee?.let { employees.add(it) }
                    } else if (tagname.equals("id", ignoreCase = true)) {
                        employee!!.id = Integer.parseInt(textView)
                    } else if (tagname.equals("name", ignoreCase = true)) {
                        employee!!.name = textView
                    } else if (tagname.equals("salary", ignoreCase = true)) {
                        employee!!.salary = java.lang.Float.parseFloat(textView)
                    }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        }catch (e:XmlPullParserException){
           e.printStackTrace()
        }
        return employees
    }
}