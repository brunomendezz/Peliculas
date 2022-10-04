package ar.edu.unlam.apppeliculas.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ar.edu.unlam.apppeliculas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMainBinding.inflate(layoutInflater)
     setContentView(binding.root)


    }
}