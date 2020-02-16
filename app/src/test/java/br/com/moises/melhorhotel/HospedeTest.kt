package br.com.moises.melhorhotel

import br.com.moises.melhorhotel.util.DateUtils
import br.com.moises.melhorhotel.util.DayType
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DateFormat
import java.util.*

class HospedeTest {

    @Test
    fun deve_Retornar_1_para_contador_de_dias_uteis_de_20_a_22_de_marco_de_2020() {
        val qtdDiasSemana = DateUtils.contarDias(marco_2020(20), marco_2020(22), DayType.WORKING)
        assertEquals(1, qtdDiasSemana)
    }

    @Test
    fun deve_Retornar_2_para_contador_de_dias_de_fim_de_semana_de_20_a_22_de_marco_de_2020() {
        val qtdDiasFimSemana = DateUtils.contarDias(marco_2020(20), marco_2020(22), DayType.WEEKEND)
        assertEquals(2, qtdDiasFimSemana)
    }

    @Test
    fun deve_retornar_16_de_marco_de_2020() {
        val data = marco_2020(16)
        assertEquals("16/03/2020", formatarData(data))
    }

    @Test
    fun deve_retornar_18_de_marco_de_2020() {
        val data = marco_2020(18)
        assertEquals("18/03/2020", formatarData(data))
    }

    private fun marco_2020(dia: Int): Date {
        val cal = Calendar.getInstance()
        cal.set(2020, Calendar.MARCH, dia)
        return cal.time
    }

    private fun formatarData(data: Date): String {
        return DateFormat.getDateInstance().format(data)
    }
}