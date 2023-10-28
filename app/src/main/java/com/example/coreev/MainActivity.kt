package com.example.coreev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton


class MainActivity : AppCompatActivity() {

    private lateinit var introSliderViewPager: ViewPager2
    private lateinit var btnNext: MaterialButton
    private lateinit var indicatorsContainer: LinearLayout
    private lateinit var textSkipIntro: TextView

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Discover and Search",
                "Solve EV range anxiety , helping EV drivers find nearby charging stations, making long trips worry-free",
                R.drawable.discover_search
            ),
            IntroSlide(
                "SustainableEV CalcX",
                "Calculate carbon emissions ,monthly saving ,co2 footprints & range for ICE and EVs based on vehicle data and routes. Make eco-friendly driving decisions effortlessly",
                R.drawable.ev_calculator
            ),
            IntroSlide(
                "Electro Mobilize",
                "One-stop destination for EV sales, rentals, emerging startups, and AI-powered chatbot support, simplifying your electric vehicle journey",
                R.drawable.sale_service
            )
        )
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        introSliderViewPager = findViewById(R.id.introSliderViewPager)
        btnNext = findViewById(R.id.btnNext)
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        textSkipIntro = findViewById(R.id.textSkipIntro)



        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicators(0)

        introSliderViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicators(position)
            }
        })

        btnNext.setOnClickListener {
            if (introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount) {
                introSliderViewPager.currentItem +=1
            }else{
                Intent(applicationContext, HomeActivity:: class.java).also {
                    startActivity(it)
                }
            }
        }

        textSkipIntro.setOnClickListener {
            Intent(applicationContext, HomeActivity::class.java).also {
                startActivity(it)
            }
        }


    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])

        }
    }
    private fun setCurrentIndicators(index : Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView =
                indicatorsContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}