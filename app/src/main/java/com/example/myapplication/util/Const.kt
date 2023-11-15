package com.example.myapplication.util

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Const {

    companion object{
        const val  BASE_URL = "https://gateway.marvel.com"
        val timeStamp = Timestamp(System.currentTimeMillis()).toString()
        const val  API_KEY = "f71a18992b53dc1c85c37a560b2b4690"
        const val  PRIVATE_KEY = "bfdd2413902d4afa1149c05ad93dbab5956f4fb2"
        const val  limit = "20"
        const val  OFFSET = 0
        fun hash(): String {
            val input = "$timeStamp + $PRIVATE_KEY + $API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

    }
}