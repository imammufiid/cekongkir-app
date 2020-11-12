package com.mufiid.quizwsp.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mufiid.quizwsp.MyViewModel
import com.mufiid.quizwsp.R
import com.mufiid.quizwsp.models.City
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewModel: MyViewModel
    private var cityName = arrayListOf<String>()
    private var cityId = arrayListOf<String>()
    private var cityOriginId: Int? = 0
    private var cityDestinationId: Int? = 0
    private var courier: String? = ""
    private var loading: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loading = ProgressDialog(this)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MyViewModel::class.java)

        btn_cek_ongkir.setOnClickListener(this)
        radio_courier.setOnCheckedChangeListener { group, checkId ->
            val check = findViewById<View>(checkId) as RadioButton
            courier = check.text.toString()
        }

        showLoading(true)
        viewModel.setCity()
        viewModel.getCity().observe(this, Observer {
            it?.let {
                getCity(it)
                showLoading(false)
            }

            setSpinnerCityOrigin()
            setSpinnerCityDestination()
        })


    }

    private fun showLoading(state: Boolean) {

        if (state) {
            loading?.setMessage("Mengambil data ...")
            loading?.setCancelable(false)
            loading?.setCanceledOnTouchOutside(false)
            loading?.show()
        } else {
            loading?.dismiss()
        }
    }

    private fun setSpinnerCityDestination() {
        cityDestination.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // code ...
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                cityDestinationId = cityId[position].toInt()
            }
        }
    }

    private fun setSpinnerCityOrigin() {
        cityOrigin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // code ...
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                cityOriginId = cityId[position].toInt()
            }

        }
    }

    private fun getCity(city: List<City>?) {
        city?.let {
            for (i in 0 until (city?.size ?: 0)) {
                cityName.add(city[i].cityName.toString())
                cityId.add(city[i].cityId.toString())
            }

            val adapter = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, cityName
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cityOrigin.adapter = adapter
            cityDestination.adapter = adapter
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_cek_ongkir -> {
                val weight = weight.text.toString()

                if (cityOriginId == 0 || cityDestinationId == 0) {
                    Toast.makeText(this, getString(R.string.alert_city), Toast.LENGTH_SHORT).show()
                } else if (weight.isNullOrEmpty()) {
                    Toast.makeText(this, getString(R.string.alert_weight), Toast.LENGTH_SHORT).show()
                } else if (radio_courier.checkedRadioButtonId <= 0) {
                    Toast.makeText(this, getString(R.string.alert_kurir), Toast.LENGTH_SHORT).show()
                } else {
                    val bundle = Bundle().apply {
                        putInt(ListCourierDialogFragment.CITYORIGIN, cityOriginId!!)
                        putInt(ListCourierDialogFragment.CITYDESTINATION, cityDestinationId!!)
                        putInt(ListCourierDialogFragment.WEIGHT, weight.toInt())
                        putString(ListCourierDialogFragment.COURIER, courier)
                    }
                    ListCourierDialogFragment().apply {
                        arguments = bundle
                        show(supportFragmentManager, ListCourierDialogFragment.TAG)
                    }
                }
            }
        }
    }
}