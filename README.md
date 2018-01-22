# BlackFloat
两秒自动黑屏的全屏悬浮窗

适配Android 8.0

        if (Build.VERSION.SDK_INT >= 26) {//8.0新特性
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        params.format = PixelFormat.RGB_888;//不设置这个会花屏
