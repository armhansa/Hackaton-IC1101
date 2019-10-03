package com.example.myapplication.basic_api.data

import com.example.myapplication.basic_api.API_KEY
import com.example.myapplication.basic_api.API_SECRET

data class TokenBody(val applicationKey : String = API_KEY,
                     val applicationSecret: String = API_SECRET
                       ) {

}