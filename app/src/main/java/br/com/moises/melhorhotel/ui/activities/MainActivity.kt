package br.com.moises.melhorhotel.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.moises.melhorhotel.R
import br.com.moises.melhorhotel.model.BuscaMelhorHotel
import br.com.moises.melhorhotel.model.Hospede
import br.com.moises.melhorhotel.repository.HotelRepository
import br.com.moises.melhorhotel.ui.OnSelectDateListener
import br.com.moises.melhorhotel.ui.fragments.DatePickerFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(),
    OnSelectDateListener {

    var hospede: Hospede =
        Hospede()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swReward.setOnCheckedChangeListener { _, isChecked ->
            hospede.isFidelidade = isChecked
        }
    }

    fun showDatePickerDialog(v: View) {
        val newFragment =
            DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    override fun onSelectDateFinish(date: Date) {

        if (hospede.isCheckInSelected()) {
            hospede.dataSaida = date
        } else {
            hospede.dataEntrada = date
        }
    }

    fun fetchBestHotelClick(view: View) {
        val buscaMelhorHotel = BuscaMelhorHotel(
            HotelRepository.listarHoteis(), hospede
        )
        val melhorHotel = buscaMelhorHotel.calcular()

        tvMelhorHotel.text = melhorHotel?.nome

        hospede.reset()
    }
}
