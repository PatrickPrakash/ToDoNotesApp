package com.hackerx.todonotesapp.OnBoarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hackerx.todonotesapp.R

class OnBoardingTwoFragment : Fragment() {
    lateinit var textviewdone:TextView
    lateinit var textviewback:TextView
    lateinit var onOption: onOptionClick
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onOption = context as onOptionClick
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_on_boarding_two, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
    }

    private fun bindViews(view:View) {
        textviewdone = view.findViewById(R.id.fragment2done)
        textviewback = view.findViewById(R.id.fragment2back)
        clickListeners()
    }

    private fun clickListeners() {
        textviewdone.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                onOption.onOptionNext()
            }

        })
        textviewback.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                onOption.onOptionBack()
            }

        })
    }

    interface onOptionClick {
        fun onOptionBack()
        fun onOptionNext()
    }

}