package unram.psti.week5fragmentkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class CategoryFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = view.findViewById(R.id.button)
        button.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button) {
            val mDetailCategoryFragment = DetailCategoryFragment()
            val mFragmentManager = fragmentManager

            val mBundle = Bundle()
            mBundle.putString(DetailCategoryFragment.EXTRA_MESSAGE, "ini adalah data yang dikirim dari Category Fragment")
            mDetailCategoryFragment.arguments = mBundle

            mFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container, mDetailCategoryFragment, DetailCategoryFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }
}