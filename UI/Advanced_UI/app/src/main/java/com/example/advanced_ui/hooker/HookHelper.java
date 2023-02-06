package com.example.advanced_ui.hooker;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookHelper {

    private static final String TAG = "";

    public static final String EXTRA_TARGET_INTENT = "extra_target_intent";

    public static void hookIActivityManager() {

        /** TODO
         * 1.找到hook点
         * 2.使用动态代理
         * 3.获取到getDefault的IActivityManager原始对象
         * 4.动态代理 准备classloader 和 接口
         * 获取当前线程classloader
         * 6.接口Class.forName("android.app.IActivityManager“)
         * 7.Proxy.newProxyInstance()得到一个IActivityManageProxy
         */
        try {
            Field getDefaultField = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Class<?> activityManager = Class.forName("android.app.ActivityManager");
                getDefaultField = activityManager.getDeclaredField("IActivityManagerSingleton");
            } else {
                Class<?> activityManager = Class.forName("android.app.ActivityManagerNative");
                // get the Singleton
                getDefaultField = activityManager.getDeclaredField("gDefault");
            }
            getDefaultField.setAccessible(true);
            // get the interface of AMS
            /**
             * 注意：
             * 因为getDefaultField这个对象属性是static静态的，所以get（）方法的时候可以传null
             */
            Object getDefault = getDefaultField.get(null);//这里拿到的才是真正对象里面的字段
            //拿到Singleton的Class对象
            Class<?> singletonClass = Class.forName("android.util.Singleton");

            Field mInstance = singletonClass.getDeclaredField("mInstance");
            mInstance.setAccessible(true);
            // 获取到ActivityManagerNative里面的getDefault对象里面原始的IActivityManager对象
            final Object rawIActivityManager = mInstance.get(getDefault);
            //进行动态代理
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Class<?> iActivityMangerInterface = Class.forName("android.app.IActivityManager");
            //生成IActivityManager的代理对象
            Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{iActivityMangerInterface}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Log.d(TAG, "method:" + method.getName());
                    if ("startActivity".equals(method.getName())) {
                        Log.i(TAG, "准备启动activity");
                        for (Object obj : args) {
                            Log.i(TAG, "obj= " + obj);
                        }
                        //绕过权限 启动一个我没有注册的Activity
                        // 拿到原始的Intent保存起来
                        Intent rawIntent = null;
                        int index = 0;
                        for (int i = 0; i < args.length; i++) {
                            if (args[i] instanceof Intent) {
                                rawIntent = (Intent) args[i];
                                index = i;
                                break;
                            }
                        }
                        Log.i(TAG, "rawIntent= " + rawIntent);
                        //替换成Target
                        Intent newIntent = new Intent();
                        String stubPackage = "com.example.advanced_ui.hooker";
                        newIntent.setComponent(new ComponentName(stubPackage, StubActivity.class.getName()));
                        // 把这个newIntent放回args,这就能达到目的
                        newIntent.putExtra(EXTRA_TARGET_INTENT, rawIntent);
                        args[index] = newIntent;
                    }
                    return method.invoke(rawIActivityManager, args);
                }
            });

            //把我们的代理对象融入到framework
            mInstance.set(getDefault, proxy);

        } catch (Exception e) {
            Log.d(TAG, "Exception");
            e.printStackTrace();
        }
    }
}
