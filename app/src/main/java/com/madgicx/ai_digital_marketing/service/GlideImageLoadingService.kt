package com.madgicx.ai_digital_marketing.service

import android.widget.ImageView
import com.bumptech.glide.Glide
import ss.com.bannerslider.ImageLoadingService

class GlideImageLoadingService : ImageLoadingService {

    override fun loadImage(url: String?, imageView: ImageView?) {
        Glide.with(imageView?.context!!).load(url).into(imageView)
    }

    override fun loadImage(resource: Int, imageView: ImageView?) {
        Glide.with(imageView?.context!!).load(
                imageView.context.resources
                        .getIdentifier(resource.toString(), "drawable", imageView.context.packageName)
        ).fitCenter().into(imageView)
    }

    override fun loadImage(
            url: String?, placeHolder: Int, errorDrawable: Int, imageView: ImageView?
    ) {
        Glide.with(imageView?.context!!).load(url).fitCenter().into(imageView)
    }
}