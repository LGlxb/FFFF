package view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class FlowVivw extends FrameLayout {
    public FlowVivw(@NonNull Context context) {
        super(context);
    }


    public FlowVivw(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowVivw(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FlowVivw(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //获取控件宽度
        int width = getWidth();
        //定义常量行数
        int row = 0;
        //首个子控件距控件距离
        int disWidth = 20;
        for (int i = 0; i < getChildCount(); i++) {
            //子控件视图
            View view = getChildAt(i);
            //子控件宽高
            int cWidth = view.getWidth();
            int cHeigth = view.getHeight();
            if (cWidth + disWidth > width) {
                row++;
                disWidth = 20;
            }
            view.layout(disWidth, row * cHeigth, cWidth + disWidth, cHeigth * (row + 1));
            disWidth += disWidth;
        }
    }
}
