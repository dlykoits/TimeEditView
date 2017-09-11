package by.com.blogspot.dlykoits.timeeditviewlibrary;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

/**
 * Created by dzmitrylukoits on 9/5/17.
 */

public class TimeEditView extends EditText {

    private static final int NUMBERS_COUNT = 4;
    private Character fillerSymbol = '0';
    private Character divisorSymbol = ':';
    private int timeMs = 0;

    private TextWatcher textWatcher = new TextWatcher() {

        boolean skip = false;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (!skip) {
                skip = true;
                String time = editable.toString();
                time = time.replaceAll("[^0-9]", "");
                while (time.length() < NUMBERS_COUNT) {
                    time = fillerSymbol + time;
                }
                if (time.length() > NUMBERS_COUNT) {
                    time = time.substring(time.length() - NUMBERS_COUNT, time.length());
                }
                time = time.substring(0, NUMBERS_COUNT / 2) + divisorSymbol + time.substring(NUMBERS_COUNT / 2, NUMBERS_COUNT);
                editable.replace(0, editable.length(), time);
            } else {
                skip = false;
            }
        }
    };

    public TimeEditView(Context context) {
        super(context);
        init();
    }

    public TimeEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimeEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TimeEditView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        addTextChangedListener(textWatcher);
        setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_TIME);
        setText(msToStr(timeMs));
    }


    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        setSelection(length());
    }

    /**
     * Filler symbol used to fill edit text when user delete symbols
     *
     * @param fillerSymbol
     */
    public void setFillerSymbol(Character fillerSymbol) {
        this.fillerSymbol = fillerSymbol;
    }

    /**
     * divisor symbol used to split minutes and seconds
     *
     * @param divisorSymbol
     */
    public void setDivisorSymbol(Character divisorSymbol) {
        this.divisorSymbol = divisorSymbol;
    }

    public void setTimeMs(int timeMs) {
        this.timeMs = timeMs;
    }

    private String msToStr(long ms) {
        String.format("%02d:%02d", TimeUnit.MILLISECONDS.toHours(), TimeUnit.MILLISECONDS.toMinutes();
    }
}
