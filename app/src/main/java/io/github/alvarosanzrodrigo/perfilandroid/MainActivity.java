package io.github.alvarosanzrodrigo.perfilandroid;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Switch show;
    TextView textView;
    EditText firstName;
    EditText surname1;
    EditText surname2;
    EditText postalCode;
    EditText city;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = findViewById(R.id.switch1);
        firstName = findViewById(R.id.etxtName);
        surname1 = findViewById(R.id.etxtSurname1);
        surname2 = findViewById(R.id.etxtSurname2);
        postalCode = findViewById(R.id.etxtPostalCode);
        city = findViewById(R.id.etxtCity);
        textView = findViewById(R.id.textView);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonMessage();
            }
        });
        show.setChecked(true);
        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView.setVisibility(View.VISIBLE);
                } else {
                    textView.setVisibility(View.GONE);
                }
            }
        });

        surname2.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed() && firstName.getText().length() != 0) {
                                // the user is done typing.
                                postalCode.setVisibility(View.VISIBLE);
                                city.setVisibility(View.VISIBLE);
                                button.setVisibility(View.VISIBLE);
                                buttonMessage();
                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                }
        );

    }
    public void buttonMessage(){
        Context context = getApplicationContext();
        CharSequence text = firstName.getText() + " " + surname1.getText() + " " + surname2.getText() + " " + postalCode.getText() + " " + city.getText();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}




