package com.behruzbek0430.viewpageranimation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.behruzbek0430.viewpageranimation.adapters.ImageAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var imageAdapter: ImageAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4
        )

        viewPager = findViewById(R.id.viewPager)
        imageAdapter = ImageAdapter(images)
        viewPager.adapter = imageAdapter

        // Avtomatik o'zgartirish uchun handlerdan foydalanish
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (currentPage == images.size) {
                    currentPage = 0
                }
                viewPager.setCurrentItem(currentPage++, true)
                handler.postDelayed(this, 10000) // 10 soniyadan keyin yana ishlaydi
            }
        }, 10000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}