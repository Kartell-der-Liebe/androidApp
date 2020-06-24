package com.example.kartellderliebe.rss.model

import com.example.kartellderliebe.rss.model.Feed
import com.example.kartellderliebe.rss.model.Item


data class RSSObject(var status : String, var feed: Feed, var items : List<Item>) {



}