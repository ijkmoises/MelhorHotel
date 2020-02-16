package br.com.moises.melhorhotel.model

import br.com.moises.melhorhotel.util.DateUtils
import br.com.moises.melhorhotel.util.DayType
import java.util.*

class Hospede {

    var dataEntrada: Date = Date(0)
    var dataSaida: Date = Date(0)
    var isFidelidade: Boolean = false

    fun quantidadeDiasFimDeSemana(): Int {
        return DateUtils.contarDias(dataEntrada, dataSaida, DayType.WEEKEND)
    }

    fun quantidadeDiasUteis(): Int {
        return DateUtils.contarDias(dataEntrada, dataSaida, DayType.WORKING)
    }

    fun isCheckInSelected(): Boolean {
        return dataEntrada.time > 0
    }

    fun reset(){
        dataEntrada = Date(0)
        dataSaida = Date(0)
    }
}