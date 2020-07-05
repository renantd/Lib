package br.sofex.com.lib.Lib;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;

public class JustifiedTextView extends androidx.appcompat.widget.AppCompatTextView {
    private final String CORE_TEMPLATE = "<html><body style='text-align:justify;margin: 0px 0px 0px 0px;'>%s</body></html>";

    public JustifiedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setText(Html.fromHtml(String.format(CORE_TEMPLATE,getText())));
    }

    public JustifiedTextView(Context context) {
        super(context);
        setText(Html.fromHtml(String.format(CORE_TEMPLATE,getText())));
    }

    public JustifiedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setText(Html.fromHtml(String.format(CORE_TEMPLATE,getText())));
    }
}
