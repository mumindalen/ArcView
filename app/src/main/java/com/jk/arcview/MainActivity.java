package com.jk.arcview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private SeekBar seekBar;

    private ArcView arcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(arcView);
        setContentView(R.layout.activity_main);

        //arcView = new ArcView(this);
        arcView = (ArcView) findViewById(R.id.arcView);

        arcView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(500);
                scaleAnimation.setRepeatCount(1);
                scaleAnimation.setRepeatMode(Animation.REVERSE);
                v.startAnimation(scaleAnimation);
            }
        });

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                arcView.setTotalPieces(progress);
                if(fromUser){
                    editText.setText(""+progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        editText = (EditText) findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String editTextContent = editText.getText().toString();

                int pieces = tryParse(editTextContent);
                arcView.setTotalPieces(pieces);

                seekBar.setProgress(pieces);

            }
        });

    }

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 1;
        }
    }
}
