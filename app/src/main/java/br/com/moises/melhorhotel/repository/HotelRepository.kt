package br.com.moises.melhorhotel.repository

import br.com.moises.melhorhotel.model.Hotel
import java.math.BigDecimal

object HotelRepository {

    fun listarHoteis(): List<Hotel> {

        val hotel0 = Hotel(
            nome = "Parque das flores",
            classificacao = 3,

            precoRegularDiaUtil = BigDecimal(110),
            precoRegularFimDeSemana = BigDecimal(90),

            precoFidelidadeDiaUtil = BigDecimal(80),
            precoFidelidadeFimDeSemana = BigDecimal(80)
        )

        val hotel1 = Hotel(
            nome = "Jardim Botânico",
            classificacao = 4,

            precoRegularDiaUtil = BigDecimal(160),
            precoRegularFimDeSemana = BigDecimal(60),

            precoFidelidadeDiaUtil = BigDecimal(110),
            precoFidelidadeFimDeSemana = BigDecimal(50)
        )

        val hotel2 = Hotel(
            nome = "Mar Atlântico",
            classificacao = 5,

            precoRegularDiaUtil = BigDecimal(220),
            precoRegularFimDeSemana = BigDecimal(150),

            precoFidelidadeDiaUtil = BigDecimal(100),
            precoFidelidadeFimDeSemana = BigDecimal(40)
        )

        return listOf(hotel0, hotel1, hotel2)
    }
}