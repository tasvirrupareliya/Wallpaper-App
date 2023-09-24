package com.wallpaper.app.model

data class ApiResponse(val category: List<Category>)
data class Category(val name: String, val thumb: List<String>)

data class ApiResponse2(val category: List<Category2>)
data class Category2(val name: String, val thumb_HD: List<String>)

data class ApiResponse3(val category: List<Category3>)
data class Category3(val name: String, val small: List<String>)


