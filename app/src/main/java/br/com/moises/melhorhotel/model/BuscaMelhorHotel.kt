package br.com.moises.melhorhotel.model

import java.math.BigDecimal

class BuscaMelhorHotel(
    private val hotelList: List<Hotel> = listOf(),
    private val hospede: Hospede
) {

    var melhorHotel: Hotel? = null
    var totalParaDiasUteis: BigDecimal? = BigDecimal(0)
    var totalParaFinsDeSemana: BigDecimal? = BigDecimal(0)
    var totalGeral: BigDecimal? = BigDecimal(0)
    var totalParcial: BigDecimal? = BigDecimal(0)

    fun calcular(): Hotel? {
        reciclarVariaveis()
        for (hotel in hotelList) {
            totalParcial = if (hospede.isFidelidade) {
                calcularParaHospedeFidelidade(hotel)
            } else {
                calcularParaHospedeRegular(hotel)
            }
            processarHotelEncontrado(hotel)
        }
        return melhorHotel
    }

    private fun reciclarVariaveis() {
        melhorHotel = null
        totalParaDiasUteis = BigDecimal(0)
        totalParaFinsDeSemana = BigDecimal(0)
        totalGeral = BigDecimal(0)
        totalParcial = BigDecimal(0)
    }

    fun calcularParaHospedeFidelidade(hotel: Hotel): BigDecimal? {

        totalParaDiasUteis =
            hotel.precoFidelidadeDiaUtil?.multiply(BigDecimal(hospede.quantidadeDiasUteis()))

        totalParaFinsDeSemana =
            hotel.precoFidelidadeFimDeSemana?.multiply(BigDecimal(hospede.quantidadeDiasFimDeSemana()))

        return totalParaDiasUteis?.add(totalParaFinsDeSemana)
    }

    fun calcularParaHospedeRegular(hotel: Hotel): BigDecimal? {

        totalParaDiasUteis =
            hotel.precoRegularDiaUtil?.multiply(BigDecimal(hospede.quantidadeDiasUteis()))

        totalParaFinsDeSemana =
            hotel.precoRegularFimDeSemana?.multiply(BigDecimal(hospede.quantidadeDiasFimDeSemana()))

        return totalParaDiasUteis?.add(totalParaFinsDeSemana)
    }

    private fun processarHotelEncontrado(hotel: Hotel) {
        if (encontrouMelhorPreco()) {
            registraMelhorHotelPorPreco(hotel)
        } else if (empatou()) {
            registraMelhorHotelPorClassificao(hotel)
        }
    }

    private fun encontrouMelhorPreco(): Boolean {
        if (totalGeral == BigDecimal(0)) {
            return true
        }
        return totalParcial!! < totalGeral
    }

    private fun registraMelhorHotelPorPreco(hotel: Hotel) {
        totalGeral = totalParcial
        melhorHotel = hotel
    }

    private fun empatou(): Boolean {
        return totalParcial == totalGeral
    }

    fun registraMelhorHotelPorClassificao(hotelEmpate: Hotel) {

        val melhorHotelPorPreco = melhorHotel

        melhorHotel = if (melhorHotelPorPreco?.classificacao!! > hotelEmpate.classificacao!!) {
            melhorHotelPorPreco
        } else {
            hotelEmpate
        }
    }
}