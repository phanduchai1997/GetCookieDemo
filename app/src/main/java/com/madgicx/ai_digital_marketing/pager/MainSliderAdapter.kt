package com.madgicx.ai_digital_marketing.pager

import com.madgicx.ai_digital_marketing.R
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class MainSliderAdapter : SliderAdapter() {

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder) {
        when (position) {
            0 -> viewHolder.bindImageSlide(R.drawable.image_1)
            1 -> viewHolder.bindImageSlide(R.drawable.image_2)
            2 -> viewHolder.bindImageSlide(R.drawable.image_3)
            3 -> viewHolder.bindImageSlide(R.drawable.image_4)
            4 -> viewHolder.bindImageSlide(R.drawable.image_5)
            5 -> viewHolder.bindImageSlide(R.drawable.image_6)
            6 -> viewHolder.bindImageSlide(R.drawable.image_7)
            7 -> viewHolder.bindImageSlide(R.drawable.image_8)
        }
    }
}