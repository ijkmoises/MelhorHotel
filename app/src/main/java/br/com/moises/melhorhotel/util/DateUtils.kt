package br.com.moises.melhorhotel.util

import java.util.*

object DateUtils {

    fun contarDias(inicio: Date, fim: Date, dayType: DayType): Int {

        val cal = Calendar.getInstance()

        cal.time = inicio

        var contador = 0

        while (!cal.time.after(fim)) {

            val diaDaSemana = cal.get(Calendar.DAY_OF_WEEK)

            if (dayType == DayType.WEEKEND) {
                if (diaDaSemana == Calendar.SUNDAY || diaDaSemana == Calendar.SATURDAY) {
                    contador++
                }
            } else if (dayType == DayType.WORKING) {
                if (diaDaSemana != Calendar.SUNDAY && diaDaSemana != Calendar.SATURDAY) {
                    contador++
                }
            }

            cal.add(Calendar.DAY_OF_MONTH, 1)
        }

        return contador
    }
}