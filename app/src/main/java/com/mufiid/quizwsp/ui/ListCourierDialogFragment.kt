package com.mufiid.quizwsp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mufiid.quizwsp.MyViewModel
import com.mufiid.quizwsp.R
import com.mufiid.quizwsp.adapters.CostAdapter

class ListCourierDialogFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: MyViewModel
    private var pb: ProgressBar? = null
    private var adapter: CostAdapter? = null
    private var list: RecyclerView? = null
    private var kurir: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.fragment_list_courier_dialog_list_dialog, container, false)
        val btnOk = root.findViewById<View>(R.id.btn_ok) as Button
        kurir = root.findViewById<View>(R.id.kurir) as TextView
        pb = root.findViewById(R.id.pb)
        list = root.findViewById(R.id.list)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MyViewModel::class.java)

        viewModel.getCost().observe(this, Observer {
            it?.let {
                adapter?.setCost(it)
                showLoading(false)
            }
        })

        setKurir()
        showRecyclerList()

        btnOk.setOnClickListener {
            dismiss()
        }
        return root
    }

    private fun setKurir() {
        when(arguments?.getString(ListCourierDialogFragment.COURIER)) {
            "jne" -> kurir?.text = getString(R.string.kurir, "JNE")
            "tiki" -> kurir?.text = getString(R.string.kurir, "TIKI")
            "pos" -> kurir?.text = getString(R.string.kurir, "POS Indonesia")
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.findViewById<RecyclerView>(R.id.list)?.layoutManager =
            LinearLayoutManager(context)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            showLoading(true)
            viewModel.checkCost(
                arguments!!.getInt(ListCourierDialogFragment.CITYORIGIN, 0),
                arguments!!.getInt(ListCourierDialogFragment.CITYDESTINATION, 0),
                arguments!!.getInt(ListCourierDialogFragment.WEIGHT, 0),
                arguments!!.getString(ListCourierDialogFragment.COURIER)

            )
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb?.visibility = View.VISIBLE
        } else {
            pb?.visibility = View.GONE
        }
    }

    private fun showRecyclerList() {
        adapter = CostAdapter().apply {
            notifyDataSetChanged()
        }

        list?.layoutManager = LinearLayoutManager(context)
        list?.adapter = adapter

    }


    companion object {

        const val TAG = "BottomSheet"
        const val CITYORIGIN = "city_origin"
        const val CITYDESTINATION = "city_destination"
        const val WEIGHT = "weight"
        const val COURIER = "courier"

    }

}