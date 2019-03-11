package com.example.komputer.epsonerrorcodesv11;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import 	android.app.SearchManager; //for searching web


public class MainActivity extends AppCompatActivity {

    String extModel ="";
    String extError ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //to disable rotation of screen
    }

    public void onButtonClick(View w) {
        EditText editText = (EditText) findViewById(R.id.editText); //getting text from user
        TextView textView = (TextView) findViewById(R.id.textView); //textView is a display
        Spinner spinner = (Spinner) findViewById(R.id.spinner); //object spinner
        String err = (editText.getText().toString().toUpperCase()); //assignment of error typed by user to String err
        extError = err; //assignment to external variable
        String epsonChosen = (String)spinner.getSelectedItem(); //reading spinner, = String.valueOf(spinner.getSelectedItem());
        String result=""; //for storing result of checking error
        getNameForExtModel(epsonChosen);//choosing the content of extModel string

        switch(epsonChosen){//displaying accurate info according to choice of user
            case "Epson 9800/7800":
            case "Epson 9880/7880":
            case "Epson 9450/7450":
            {
                result=Error7800.check7800(err);
                textView.setText(result);
                break;
            }
            case "Epson 9600/7600":
            {
                result=Error7600.check7600(err);
                textView.setText(result);
                break;
            }
            case "Epson 4800/4880":
            case "Epson 4450/4400":
            {
                result=Error4800.check4800(err);
                textView.setText(result);
                break;
            }
            case "Epson 4000":
            {
                result=Error4000.check4000(err);
                textView.setText(result);
                break;
            }
            default:
            {
                textView.setText("Switch error!");
                break;
            }
        }//switch

        View b = findViewById(R.id.button3); //var of button for searching
        if (result.equals("THERE IS NO SUCH ERROR")) {
            b.setVisibility(View.INVISIBLE);
        }else{
            b.setVisibility(View.VISIBLE);
        }

    }//onButtonClick

    void getNameForExtModel(String a){
        switch(a){//displaying accurate info according to choice of user
            case "Epson 9800/7800":
            case "Epson 9880/7880":
            case "Epson 9450/7450":
            {
                extModel = "Epson 9800";
                break;
            }
            case "Epson 9600/7600":
            {
                extModel = "Epson 9600";
                break;
            }
            case "Epson 4800/4880":
            case "Epson 4450/4400":
            {
                extModel = "Epson 4800";
                break;
            }
            case "Epson 4000":
            {
                extModel = "Epson 4000";
                break;
            }
            default:
            {
                break;
            }
        }//switch
    }

    public void onSearchButtonClick(View w) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY,extModel + " error " + extError);
        startActivity(intent);
    }//onButtonClick

}