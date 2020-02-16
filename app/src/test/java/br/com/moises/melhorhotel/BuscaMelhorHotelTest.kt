package br.com.moises.melhorhotel


import br.com.moises.melhorhotel.model.BuscaMelhorHotel
import br.com.moises.melhorhotel.model.Hospede
import br.com.moises.melhorhotel.repository.HotelRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.util.*


class BuscaMelhorHotelTest {

    @Test
    fun deve_retornar_hotel_com_maior_classificacao() {

        val buscaMelhorHotel = BuscaMelhorHotel(
            HotelRepository.listarHoteis(),
            hospede_Regular_de_16_a_18_marco_de_2020()
        )

        buscaMelhorHotel.melhorHotel = HotelRepository.listarHoteis()[2]
        buscaMelhorHotel.registraMelhorHotelPorClassificao(HotelRepository.listarHoteis()[0])
        assertEquals("Mar Atlântico", buscaMelhorHotel.melhorHotel?.nome)
    }

    @Test
    fun deve_retornar_hotel_Parque_das_Flores_para_hospede_regular_entrando_de_16_a_18_de_marco() {

        val buscaMelhorHotel = BuscaMelhorHotel(
            HotelRepository.listarHoteis(),
            hospede_Regular_de_16_a_18_marco_de_2020()
        )

        val hotel = buscaMelhorHotel.calcular()
        assertEquals("Parque das flores", hotel?.nome)
    }

    @Test
    fun deve_Retornar_hotel_Jardim_Botanico_para_hospede_regular_entrando_de_20_a_22_de_marco() {

        val buscaMelhorHotel = BuscaMelhorHotel(
            HotelRepository.listarHoteis(),
            hospede_Regular_de_20_a_22_marco_de_2020()
        )

        val hotel = buscaMelhorHotel.calcular()
        assertEquals("Jardim Botânico", hotel?.nome)
    }

    @Test
    fun deve_retornar_hotel_Mar_Atlantico_para_hospede_fidelidade_entrando_de_26_a_28_de_marco() {

        val buscaMelhorHotel = BuscaMelhorHotel(
            HotelRepository.listarHoteis(),
            hospede_Fideliade_de_26_a_28_marco_de_2020()
        )

        val hotel = buscaMelhorHotel.calcular()
        assertEquals("Mar Atlântico", hotel?.nome)
    }

    @Test
    fun deve_retornar_330_reais_para_cliente_regular_de_16_a_18_de_marco_Parque_das_Flores_hotel() {

        val hotelParqueDasFlores = HotelRepository.listarHoteis()[0]

        val buscaMelhorHotel =
            BuscaMelhorHotel(hospede = hospede_Regular_de_16_a_18_marco_de_2020())

        val total = buscaMelhorHotel.calcularParaHospedeRegular(hotelParqueDasFlores)
        assertEquals(BigDecimal(110 + 110 + 110), total)
    }

    @Test
    fun deve_retornar_280_reais_para_hospede_regular_de_20_a_22_de_marco_em_Jardim_Botanico_hotel() {

        val hotelJardimBotanico = HotelRepository.listarHoteis()[1]

        val buscaMelhorHotel =
            BuscaMelhorHotel(hospede = hospede_Regular_de_20_a_22_marco_de_2020())

        val total = buscaMelhorHotel.calcularParaHospedeRegular(hotelJardimBotanico)
        assertEquals(BigDecimal(160 + 60 + 60), total)
    }

    @Test
    fun deve_retornar_240_reais_para_hospede_fidelidade_de_26_a_28_de_marco_em_Mar_Atlantico_hotel() {

        val hotelMarAtlantico = HotelRepository.listarHoteis()[2]

        val buscaMelhorHotel =
            BuscaMelhorHotel(hospede = hospede_Fideliade_de_26_a_28_marco_de_2020())

        val total = buscaMelhorHotel.calcularParaHospedeFidelidade(hotelMarAtlantico)
        assertEquals(BigDecimal(100 + 100 + 40), total)
    }

    private fun hospede_Regular_de_16_a_18_marco_de_2020(): Hospede {
        return hospede(
            dataEntrada = marco_2020(16),
            dataSaida = marco_2020(18),
            fidelidade = false
        )
    }

    private fun hospede_Regular_de_20_a_22_marco_de_2020(): Hospede {
        return hospede(
            dataEntrada = marco_2020(20),
            dataSaida = marco_2020(22),
            fidelidade = false
        )
    }

    private fun hospede_Fideliade_de_26_a_28_marco_de_2020(): Hospede {
        return hospede(
            dataEntrada = marco_2020(26),
            dataSaida = marco_2020(28),
            fidelidade = true
        )
    }

    private fun hospede(dataEntrada: Date, dataSaida: Date, fidelidade: Boolean): Hospede {
        val hospede = Hospede()
        hospede.dataEntrada = dataEntrada
        hospede.dataSaida = dataSaida
        hospede.isFidelidade = fidelidade
        return hospede
    }

    private fun marco_2020(dia: Int): Date {
        val cal = Calendar.getInstance()
        cal.set(2020, Calendar.MARCH, dia)
        return cal.time
    }
}