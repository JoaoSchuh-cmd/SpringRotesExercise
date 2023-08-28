package com.pucpr.rotas

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.lang.StringBuilder
import java.time.temporal.ChronoUnit
import java.util.Collections
import java.util.Date
import java.util.stream.Collectors
import kotlin.math.abs

@Controller
class DateController {

    @GetMapping("/ex1")
    @ResponseBody
    fun exercise1(
            @RequestParam(name="iniDate", required=true) @DateTimeFormat(pattern = "yyyy-MM-dd") iniDate: Date,
            @RequestParam(name="endDate", required=true) @DateTimeFormat(pattern = "yyyy-MM-dd") endDate: Date
    ) : String {

        val days = abs(ChronoUnit.DAYS.between(iniDate.toInstant(), endDate.toInstant()))
        val weeks = abs(days) / 7
        val months = abs(weeks) / 4

        return "Difference <br> in days: ${days} <br> in weeks: ${weeks} <br> in months: ${months}"
    }

    @GetMapping("/ex2")
    @ResponseBody
    fun exercise2(
            @RequestParam(name="numberList", required=true) numberList : String
    ) : String {
        val desorganizedList = numberList.split(";")

        val ascOrder = ArrayList(desorganizedList)
        Collections.sort(ascOrder, Comparator.comparingInt(Integer::parseInt))

        val desOrder = ArrayList(desorganizedList)
        Collections.sort(desOrder, Comparator.comparingInt(Integer::parseInt).reversed())

        val pairNumbers = desorganizedList.stream()
                .filter { number -> Integer.parseInt(number) % 2 == 0 }
                .collect(Collectors.toList())

        return "Ascending order: ${ascOrder} <br>" +
                "Descending order: ${desOrder} <br>" +
                "Pair numbers: ${pairNumbers}"
    }

    @GetMapping("/ex3")
    @ResponseBody
    fun exercise3(
            @RequestParam(name="text", required=true) text : String
    ) : String {
        val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
        val result = StringBuilder()

        for (char in text) {
            if (char in vowels) {
                result.append("i")
            } else {
                result.append(char)
            }
        }

        return result.toString()
    }
}