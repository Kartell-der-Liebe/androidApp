package com.example.kartellderliebe.json.lineUp

data class JSONActsObject(
    val year: String,
    val link: String,
    val stage: List<Stage>,
    val acts: List<Act>
)

data class Stage (
    val name: String,
    val time: List<String>,
    val header: List<String>
)

data class Act (
    val name: String,
    val time: String,
    val stage: String,
    val duration: String,
    val day: String,
    val image: String
)