package org.eurekamps.appexamenkotlin.fragmentsMain

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import org.eurekamps.appexamenkotlin.R


class SplashFragment : Fragment() {


    lateinit var  progressBar: ProgressBar
    lateinit var tvLoadingSplash: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)
        tvLoadingSplash = view.findViewById(R.id.tvLoadingSplash)


        var imageView: ImageView = view.findViewById(R.id.ivImgSplash)
        Glide.with(this)
            .asGif()
            .load(R.drawable.pinguino_office)
            .into(imageView)


        Handler(Looper.getMainLooper()).postDelayed({

            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)


        }, 3000)
    }


}