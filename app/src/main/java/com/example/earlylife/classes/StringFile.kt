package com.example.earlylife.classes
import java.io.File
import java.io.BufferedReader

class StringFile constructor (
    private val file: File
        ){

    lateinit var strings: List<String>

    init{
        val bufferedReader: BufferedReader = file.bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        TODO("Figure out how the string file needs to be formatted")
    }



}