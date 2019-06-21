package com.example.urraanproject.Common;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class ActivityMovementClass {

    SharedPreferences sp;
    String UserVerification="UserVerification";
    public static String MyPREFERENCES = "MyTag" ;
    Context context;


    public ActivityMovementClass(Context context) {
        this.context=context;
        MyPREFERENCES="MyTag";
        sp = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }





    public void setFlag(int checks){
    try{
        SharedPreferences.Editor editor =  sp.edit();
        editor.putString(UserVerification, String.valueOf(checks));
        editor.commit();
        editor.apply();
        // Toast.makeText(activity.this, "Record stored", Toast.LENGTH_LONG).show();
    }
    catch (Exception ex)
    {
        //  Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
    }

}



    public int getFlag(){
        int Flag = 0;
        // sp = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if(sp.contains(UserVerification))
        {
            Flag=Integer.valueOf(sp.getString(UserVerification,""));
        }else{
            Toast.makeText(context, "Please Verify yourself..!", Toast.LENGTH_SHORT).show();
        }
        return Flag;

    }
}
