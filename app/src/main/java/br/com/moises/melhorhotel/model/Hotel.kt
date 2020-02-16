package br.com.moises.melhorhotel.model

import java.math.BigDecimal

data class Hotel(
    var nome: String,
    var classificacao: Int?,

    var precoRegularDiaUtil: BigDecimal?,
    var precoRegularFimDeSemana: BigDecimal?,

    var precoFidelidadeDiaUtil: BigDecimal?,
    var precoFidelidadeFimDeSemana: BigDecimal?
)
