package ar.edu.unlam.apppeliculas.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ar.edu.unlam.apppeliculas.R

import ar.edu.unlam.apppeliculas.ui.view.fragment.LoMasPopularFragment
import ar.edu.unlam.apppeliculas.ui.view.fragment.LoMasValorado
import ar.edu.unlam.apppeliculas.ui.view.fragment.TendenciasFragment

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): Fragment {
            return when (sectionNumber) {
                0 -> LoMasPopularFragment()
                1 -> LoMasValorado()
                2 -> TendenciasFragment()
                else -> LoMasPopularFragment()
                }
            }
        }

        override fun onDestroyView() {
            super.onDestroyView()

        }
    }

