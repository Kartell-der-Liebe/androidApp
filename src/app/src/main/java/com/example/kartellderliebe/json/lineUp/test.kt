package com.example.kartellderliebe.json.lineUp

import com.google.gson.Gson

data class JSONObject (
    val year: String,
    val stage: List<Stage>
)

data class Stage (
    val name: String,
    val time: List<String>,
    val header: List<String>,
    val acts: List<Act>
)

data class Act (
    val name: String,
    val time: String,
    val duration: String,
    val image: String
)