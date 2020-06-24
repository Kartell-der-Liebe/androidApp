package com.example.kartellderliebe.json.lineUp

import com.google.gson.Gson

data class Address(
    var city: String? = null,
    var post: String? = null) {

}

data class Student(
    var name: String? = null,
    var address: Address? = null) {
}

val student = Gson().fromJson<Student>("../test.json", Student::class.java)