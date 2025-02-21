package com.example.data.mapper

interface Mapper<I,O> {
    fun map(input:I?):O
}