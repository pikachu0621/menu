package com.pikachu.menu.util.state;
import androidx.appcompat.app.AppCompatActivity;


//继承这个防止用户其他操作又变回来了
//系统清楚 flag 重新
public class PKStatusBarActivity extends AppCompatActivity {
    private PKStatusBarTools pkStatusBarTools;
    private boolean isLight = false;
    private boolean isDark = false;


    @Override
    protected void onRestart() {
        super.onRestart();
        if (pkStatusBarTools != null)
            pkStatusBarTools.init();

        if (isLight)
            QMUIStatusBarHelper.setStatusBarLightMode(this);
        else if (isDark)
            QMUIStatusBarHelper.setStatusBarDarkMode(this);
    }



    public void setPkStatusBarTools(PKStatusBarTools pkStatusBarTools) {
        if (pkStatusBarTools != null)
            this.pkStatusBarTools = pkStatusBarTools;
    }



    public boolean setStatusBarLightMode() {
        isLight = true;
        return QMUIStatusBarHelper.setStatusBarLightMode(this);
    }
    public boolean setStatusBarDarkMode() {
        isDark = true;
        return QMUIStatusBarHelper.setStatusBarDarkMode(this);
    }


}
