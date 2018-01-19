package com.xmwang.cyh.application;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by xmwang on 2018/1/5.
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    /**
     * 清单解析的开启
     *
     * 这里不开启，避免添加相同的modules两次
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
