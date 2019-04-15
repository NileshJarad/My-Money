package com.n2ksp.expense_tracker.ui.intro


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.IntroScreenModel
import kotlinx.android.synthetic.main.fragment_intro.*


class IntroFragment : Fragment() {


    companion object {
        private const val ARG_DATA = "arg_data"

        fun newInstance(data: IntroScreenModel) = IntroFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_DATA, data)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        arguments?.let {

            it.getParcelable<IntroScreenModel>(ARG_DATA)?.let { introModel ->
                intro1ImageView.setImageResource(introModel.image)
                titleTextView.text = introModel.title
                messageTextView.text = introModel.message
            }


        }
    }


}
