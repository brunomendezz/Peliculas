package ar.edu.unlam.apppeliculas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import ar.edu.unlam.apppeliculas.databinding.ActivityMainBinding
import ar.edu.unlam.apppeliculas.databinding.FragmentLoMasPopularBinding
import ar.edu.unlam.apppeliculas.ui.main.SectionsPagerAdapter
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMainBinding.inflate(layoutInflater)
     setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        val navView: NavigationView =binding.navView
        val navController = findNavController(R.id.view_pager)
        navView.setupWithNavController(navController)

    }
}