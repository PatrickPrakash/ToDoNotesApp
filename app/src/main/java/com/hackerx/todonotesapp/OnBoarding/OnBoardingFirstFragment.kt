package com.hackerx.todonotesapp.OnBoarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hackerx.todonotesapp.R

class OnBoardingFirstFragment : Fragment() {
    lateinit var textviewnext:TextView
    lateinit var onNextClick: OnNextClick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onNextClick = context as OnNextClick
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_on_boarding_first, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
    }

    private fun bindViews(view:View) {
        textviewnext = view.findViewById(R.id.fragment1next)
        textviewnext.setOnClickListener { 
            onNextClick.onselectClick()
        }
    }


    interface OnNextClick {
        fun onselectClick()
    }

}

