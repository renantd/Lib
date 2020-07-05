package br.sofex.com.lib.Lib;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.sofex.com.lib.R;

public class Lib {

    Cores  cores;
    Context mContext;
    Lib(Context context){this.mContext = context;}

    //TODO: Retorna o nome do arquivo
    public String Nome_Arquivo(File Nome_do_arquivo){
        return  Nome_do_arquivo.getName();
    }

    //TODO: Retorna o caminho do arquivo
    public String Arquivo_Caminho(File Nome_do_arquivo){
        return  Nome_do_arquivo.getAbsolutePath();
    }

    //TODO: verifica se arquivo existe
    public Boolean Check_IfExists(File Nome_do_arquivo){
        if(Nome_do_arquivo.exists()){return true;}
        else{ return  false;}
    }

    //TODO: Retorna o tamanho total do arquivo
    public String Arquivo_TotalUsado(File Nome_do_arquivo){
        return  Nome_do_arquivo.getTotalSpace()+" KB";
    }

    //TODO: instancia o auto completar
    public AutoCompleteTextView AutoCompletar(List<String> Lista_de_Itens, AutoCompleteTextView actv, Integer cor, String Texto_Mascara, Integer font_size){
        //Creating the instance of ArrayAdapter containing list of fruit names
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.mContext, android.R.layout.select_dialog_item, Lista_de_Itens);
        //Getting the instance of AutoCompleteTextView
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);//TODO: seta cor do texto
        actv.setText(null);//TODO: limpar o campo
        actv.setHint(Texto_Mascara);//TODO: Seta o texto da mascara
        actv.setHintTextColor(cor);//TODO: Seta a cor do texto de mascara
        actv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);//TODO: Seta o alinhamento do texto
        actv.setTextSize(font_size);//TODO: Seta o tamanho do texto
        actv.setTextColor(cor); //TODO: Seta a cor do texto
        return actv;
    }

    //TODO: Botão para redirecionar
    public void BotaoRedirecionar(Button btn, final Context context, final Class Destino ){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Destino);
                context.startActivity(intent);
            }
        });
    }

    //TODO: Botão para redirecionar
    public void BotaoRedirecionarIntent (Button btn, final String Nome_da_tag_envio , final String valor_da_tag_envio, final Context context, final Class Destino ){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Destino);
                intent.putExtra(Nome_da_tag_envio,valor_da_tag_envio);
                context.startActivity(intent);
            }
        });
    }

    //TODO: instancia o combobox
    public Spinner ComboBox(List<String> Lista_de_Itens, Spinner Nome_Spinner, Integer Gerar_Linha_Selecione_Intog){
        //TODO: Combobox de ações principais
        // Creating adapter for spinner

        //TODO: Gera o primeiro item com um texto já definido
        List<String> ListAux = new ArrayList<>();
        if(Gerar_Linha_Selecione_Intog == 1){
            ListAux.add("Selecione uma opção");
            for(String x : Lista_de_Itens)
            {ListAux.add(x);}
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext, R.layout.spin_center_item,R.id.tv_center_item, ListAux);
            Log.e("App1","Teste : "+ListAux);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.spin_center_item);


            // attaching data adapter to spinner
            Nome_Spinner.setAdapter(dataAdapter);
            Nome_Spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            Nome_Spinner.setGravity(View.TEXT_ALIGNMENT_CENTER);//Gravity setting for positioning the currently selected item.
            //Nome_Spinner.getBackground().setColorFilter(this.mContext.getResources().getColor(Cor_Seta), PorterDuff.Mode.SRC_ATOP);
        }
        else{
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext,R.layout.spin_center_item,R.id.tv_center_item, Lista_de_Itens);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.spin_center_item);

            // attaching data adapter to spinner
            Nome_Spinner.setAdapter(dataAdapter);
            Nome_Spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            Nome_Spinner.setGravity(View.TEXT_ALIGNMENT_CENTER);//Gravity setting for positioning the currently selected item.
            //Nome_Spinner.getBackground().setColorFilter(this.mContext.getResources().getColor(Cor_Seta), PorterDuff.Mode.SRC_ATOP);
        }

        return Nome_Spinner;
    }

    //TODO: instancia gridview
    public GridView setGridViewImagem(int[] Lista_de_Imagem, GridView gridview, Context context){
        gridview.setAdapter(new AdptadorGridView(context , Lista_de_Imagem));
        return gridview;
    }
    public void getGridViewImagemID(final int[] Lista_de_Imagem, GridView gridview, final Context context){
        gridview.setAdapter(new AdptadorGridView(context, Lista_de_Imagem));
        gridview.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "Imagem: "+(position)+1, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //TODO: setar tamanho do bitmap
    protected Bitmap setBitmapSize(Bitmap srcBitmap, Integer Width, Integer Height) {
        return Bitmap.createScaledBitmap(srcBitmap, Width, Height, false);
    }

    //TODO: setar bitmap arredondado
    protected Bitmap getRoundedBitmap(Bitmap srcBitmap, int cornerRadius) {
        // Initialize a new instance of Bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(
                srcBitmap.getWidth(), // Width
                srcBitmap.getHeight(), // Height

                Bitmap.Config.ARGB_8888 // Config
        );

        /*
            Canvas
                The Canvas class holds the "draw" calls. To draw something, you need 4 basic
                components: A Bitmap to hold the pixels, a Canvas to host the draw calls (writing
                into the bitmap), a drawing primitive (e.g. Rect, Path, text, Bitmap), and a paint
                (to describe the colors and styles for the drawing).
        */
        // Initialize a new Canvas to draw rounded bitmap
        Canvas canvas = new Canvas(dstBitmap);

        // Initialize a new Paint instance
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        /*
            Rect
                Rect holds four integer coordinates for a rectangle. The rectangle is represented by
                the coordinates of its 4 edges (left, top, right bottom). These fields can be accessed
                directly. Use width() and height() to retrieve the rectangle's width and height.
                Note: most methods do not check to see that the coordinates are sorted correctly
                (i.e. left <= right and top <= bottom).
        */
        /*
            Rect(int left, int top, int right, int bottom)
                Create a new rectangle with the specified coordinates.
        */
        // Initialize a new Rect instance
        Rect rect = new Rect(0, 0, srcBitmap.getWidth(), srcBitmap.getHeight());

        /*
            RectF
                RectF holds four float coordinates for a rectangle. The rectangle is represented by
                the coordinates of its 4 edges (left, top, right bottom). These fields can be
                accessed directly. Use width() and height() to retrieve the rectangle's width and
                height. Note: most methods do not check to see that the coordinates are sorted
                correctly (i.e. left <= right and top <= bottom).
        */
        // Initialize a new RectF instance
        RectF rectF = new RectF(rect);

        /*
            public void drawRoundRect (RectF rect, float rx, float ry, Paint paint)
                Draw the specified round-rect using the specified paint. The roundrect will be
                filled or framed based on the Style in the paint.

            Parameters
                rect : The rectangular bounds of the roundRect to be drawn
                rx : The x-radius of the oval used to round the corners
                ry : The y-radius of the oval used to round the corners
                paint : The paint used to draw the roundRect
        */
        // Draw a rounded rectangle object on canvas
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);

        /*
            public Xfermode setXfermode (Xfermode xfermode)
                Set or clear the xfermode object.
                Pass null to clear any previous xfermode. As a convenience, the parameter passed
                is also returned.

            Parameters
                xfermode : May be null. The xfermode to be installed in the paint
            Returns
                xfermode
        */
        /*
            public PorterDuffXfermode (PorterDuff.Mode mode)
                Create an xfermode that uses the specified porter-duff mode.

            Parameters
                mode : The porter-duff mode that is applied

        */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        /*
            public void drawBitmap (Bitmap bitmap, float left, float top, Paint paint)
                Draw the specified bitmap, with its top/left corner at (x,y), using the specified
                paint, transformed by the current matrix.

                Note: if the paint contains a maskfilter that generates a mask which extends beyond
                the bitmap's original width/height (e.g. BlurMaskFilter), then the bitmap will be
                drawn as if it were in a Shader with CLAMP mode. Thus the color outside of the
                original width/height will be the edge color replicated.

                If the bitmap and canvas have different densities, this function will take care of
                automatically scaling the bitmap to draw at the same density as the canvas.

            Parameters
                bitmap : The bitmap to be drawn
                left : The position of the left side of the bitmap being drawn
                top : The position of the top side of the bitmap being drawn
                paint : The paint used to draw the bitmap (may be null)
        */
        // Make a rounded image by copying at the exact center position of source image
        canvas.drawBitmap(srcBitmap, 0, 0, paint);

        // Free the native object associated with this bitmap.
        srcBitmap.recycle();

        // Return the circular bitmap
        return dstBitmap;
    }

    //TODO: setar bitmap arredondado
    protected Bitmap CircularImagem(Bitmap srcBitmap) {
        // Calculate the circular bitmap width with border
        int squareBitmapWidth = Math.min(srcBitmap.getWidth(), srcBitmap.getHeight());

        // Initialize a new instance of Bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(
                squareBitmapWidth, // Width
                squareBitmapWidth, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        /*
            Canvas
                The Canvas class holds the "draw" calls. To draw something, you need 4 basic
                components: A Bitmap to hold the pixels, a Canvas to host the draw calls (writing
                into the bitmap), a drawing primitive (e.g. Rect, Path, text, Bitmap), and a paint
                (to describe the colors and styles for the drawing).
        */
        // Initialize a new Canvas to draw circular bitmap
        Canvas canvas = new Canvas(dstBitmap);

        // Initialize a new Paint instance
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        /*
            Rect
                Rect holds four integer coordinates for a rectangle. The rectangle is represented by
                the coordinates of its 4 edges (left, top, right bottom). These fields can be accessed
                directly. Use width() and height() to retrieve the rectangle's width and height.
                Note: most methods do not check to see that the coordinates are sorted correctly
                (i.e. left <= right and top <= bottom).
        */
        /*
            Rect(int left, int top, int right, int bottom)
                Create a new rectangle with the specified coordinates.
        */
        // Initialize a new Rect instance
        Rect rect = new Rect(0, 0, squareBitmapWidth, squareBitmapWidth);

        /*
            RectF
                RectF holds four float coordinates for a rectangle. The rectangle is represented by
                the coordinates of its 4 edges (left, top, right bottom). These fields can be
                accessed directly. Use width() and height() to retrieve the rectangle's width and
                height. Note: most methods do not check to see that the coordinates are sorted
                correctly (i.e. left <= right and top <= bottom).
        */
        // Initialize a new RectF instance
        RectF rectF = new RectF(rect);

        /*
            public void drawOval (RectF oval, Paint paint)
                Draw the specified oval using the specified paint. The oval will be filled or
                framed based on the Style in the paint.

            Parameters
                oval : The rectangle bounds of the oval to be drawn

        */
        // Draw an oval shape on Canvas
        canvas.drawOval(rectF, paint);

        /*
            public Xfermode setXfermode (Xfermode xfermode)
                Set or clear the xfermode object.
                Pass null to clear any previous xfermode. As a convenience, the parameter passed
                is also returned.

            Parameters
                xfermode : May be null. The xfermode to be installed in the paint
            Returns
                xfermode
        */
        /*
            public PorterDuffXfermode (PorterDuff.Mode mode)
                Create an xfermode that uses the specified porter-duff mode.

            Parameters
                mode : The porter-duff mode that is applied

        */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        // Calculate the left and top of copied bitmap
        float left = (squareBitmapWidth-srcBitmap.getWidth())/2;
        float top = (squareBitmapWidth-srcBitmap.getHeight())/2;

        /*
            public void drawBitmap (Bitmap bitmap, float left, float top, Paint paint)
                Draw the specified bitmap, with its top/left corner at (x,y), using the specified
                paint, transformed by the current matrix.

                Note: if the paint contains a maskfilter that generates a mask which extends beyond
                the bitmap's original width/height (e.g. BlurMaskFilter), then the bitmap will be
                drawn as if it were in a Shader with CLAMP mode. Thus the color outside of the

                original width/height will be the edge color replicated.

                If the bitmap and canvas have different densities, this function will take care of
                automatically scaling the bitmap to draw at the same density as the canvas.

            Parameters
                bitmap : The bitmap to be drawn
                left : The position of the left side of the bitmap being drawn
                top : The position of the top side of the bitmap being drawn
                paint : The paint used to draw the bitmap (may be null)
        */
        // Make a rounded image by copying at the exact center position of source image
        canvas.drawBitmap(srcBitmap, left, top, paint);

        // Free the native object associated with this bitmap.
        srcBitmap.recycle();

        // Return the circular bitmap
        return dstBitmap;
    }

    //TODO: enviar Dados para outra activity
    public void EnviarParaActivity(String Nome_da_tag_envio ,String valor_da_tag_envio, final Context context, final Class Destino ){
        Intent intent = new Intent(context,Destino);
        intent.putExtra(Nome_da_tag_envio,valor_da_tag_envio);
        context.startActivity(intent);
    }

    //TODO: Pegar Dados da outra activity
    public String ReceberDadosActivity(Context context, String Nome_da_tag_envio, Intent intent) {
        try {
            String i = intent.getExtras().getString(Nome_da_tag_envio);
            if(i != null){return i;}
            else{Toast.makeText(context, " Nenhum valor retornado !", Toast.LENGTH_SHORT).show();return null;}
        }
        catch (NullPointerException NP_ex)
        {Toast.makeText(context, " Error NP_ex :"+NP_ex, Toast.LENGTH_SHORT).show();
            Log.e("App1","Error NP_ex : "+NP_ex); return null; }

        catch (Exception ex)
        {Toast.makeText(context, " Error ex :"+ex, Toast.LENGTH_SHORT).show();
            Log.e("App1","Error ex : "+ex); return null; }

    }
    public String ReceberDadosActivity2(Context context, String Nome_da_tag_envio) {
        try {
            if(Nome_da_tag_envio != null){return Nome_da_tag_envio;}
            else{Toast.makeText(context, " Nenhum valor retornado !", Toast.LENGTH_SHORT).show();return null;}
        }
        catch (NullPointerException NP_ex)
        {Toast.makeText(context, " Error NP_ex :"+NP_ex, Toast.LENGTH_SHORT).show();
            Log.e("App1","Error NP_ex : "+NP_ex); return null; }

        catch (Exception ex)
        {Toast.makeText(context, " Error ex :"+ex, Toast.LENGTH_SHORT).show();
            Log.e("App1","Error ex : "+ex); return null; }

    }

    //TODO: Radion buton com cor
    public RadioButton RadioButtonColor(RadioButton rb1, Integer cor_valor){
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled}, //disabled
                        new int[]{android.R.attr.state_enabled} //enabled
                },
                new int[] {
                        cores.CorBlack, //disabled
                        cor_valor //enabled
                }
        );
        rb1.setButtonTintList(colorStateList);
        return rb1;
    }
    public void Lista_RadioButtonColor(List<RadioButton> rb1, Integer cor_valor){

        for(RadioButton rab1 : rb1){
            ColorStateList colorStateList = new ColorStateList(
                    new int[][]{
                            new int[]{-android.R.attr.state_enabled}, //disabled
                            new int[]{android.R.attr.state_enabled} //enabled
                    },
                    new int[] {
                            cores.CorBlack, //disabled
                            cor_valor //enabled
                    }
            );
            rab1.setButtonTintList(colorStateList);
        }
    }

    //TODO: pegar o texto do radio buton de sexo
    public void RBSexo_GetTexto(final RadioGroup rg1, final RadioButton masculino, final RadioButton feminino){

        masculino.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = rg1.getCheckedRadioButtonId();


                Toast.makeText(mContext, masculino.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        feminino.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = rg1.getCheckedRadioButtonId();


                Toast.makeText(mContext,feminino.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //TODO: Verifica se o EditText é válido
    public String EditText_returnvalue(android.widget.EditText edt, Context context){
        if (EditTextValue_isvalid(edt,context) == true)
        {return  edt.getText().toString();}
        else{
            Toast.makeText(context, " Campo em branco .\n Favor digitar algum valor ! ", Toast.LENGTH_SHORT).show();  return  null;}
    }
    public Boolean EditTextValue_isvalid(android.widget.EditText edt, Context context){
        if (EditText_isNotNull(edt) == true)
        {Toast.makeText(context, " Ok "+edt.getText().toString(), Toast.LENGTH_SHORT).show(); return  true;}
        else{Toast.makeText(context, " Campo em branco .\n Favor digitar algum valor ! ", Toast.LENGTH_SHORT).show();  return  false;}
    }
    public boolean EditText_isNotNull(android.widget.EditText edt){
        if (!edt.getText().toString().isEmpty()) {return true;}
        else{return false;}
    }

    //TODO: justifica testo com webview
    public void WebView_Texto (WebView wvContent_Detail, String body, String texto_tamanho, String cor_texto , String texto_alinhamento) {
        //String HTML1 = "<html><head><style type=\"text/css\">@font-face{font-family: MyFont;src: url(\"file:///android_asset/%s\")}body {font-family: MyFont;font-size: medium; text-align: match-parent; line-height: %spx;}</style></head><body dir='rtl'>";
        String HTML= "<html><head><head><body style = 'font-family: MyFont; font-size: "+texto_tamanho+"; text-align: "+texto_alinhamento+"; color:"+cor_texto+"; line-height: %spx;'></body></html>";

        String str = String.format(HTML, "IRANSansMobile_UltraLight.ttf", 25);


        String content = body.replace("text-align:", "");
        content = content.replace("font-family:", "");
        content = content.replace("line-height:", "");
        content = content.replace("dir=", "");
        content = content.replace("width=", "width=\"100%;\"");

        String myHtmlString = str + content ;
        wvContent_Detail.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null);

        WebSettings webSettings = wvContent_Detail.getSettings();
        webSettings.setDefaultFontSize(20);
    }

    //TODO: para array to lista
    public List<String> ArraytoListString(String[] data) {
        List<String> lista = ArraytoListString(data);
        return lista;
    }
    public List<Integer> ArraytoListInteger(Integer[] data) {
        List<Integer> lista = ArraytoListInteger(data);
        return lista;
    }
    public List<Long> ArraytoListLong(Long[] data) {
        List<Long> lista = ArraytoListLong(data);
        return lista;
    }



}
