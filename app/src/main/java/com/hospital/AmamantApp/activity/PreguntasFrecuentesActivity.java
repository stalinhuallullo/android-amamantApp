package com.hospital.AmamantApp.activity;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hospital.AmamantApp.R;
import com.hospital.AmamantApp.utils.Tools;

public class PreguntasFrecuentesActivity extends AppCompatActivity {

    private static final int MAX_STEP = 4;

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private Button btn_got_it;
    private String title_array[] = {
            "¿Es normal que mi bebé duerma tanto?",
            "Mi bebé se ha puesto amarillo, ¿Estará enfermo?",
            "Ha hecho caquitas oscuras, ¿Le pasará algo?",
            "¿Cuándo me podrá ver mi bebé?"
    };
    private String description_array[] = {
            "Los primeros meses el bebé pasará durmiendo la mayor parte del día,\n" +
                    "luego sus necesidades de sueño disminuirán. Los recién nacidos\n" +
                    "duermen de 16 a 17 horas, 9 horas por la noche y el resto por el día. Con\n" +
                    "tres meses, necesitará descansar 15 horas y al cumplir un año13 o 14,\n" +
                    "siendo 11 horas de noche y 3 por el día.",
            "Los primeros días de vida muchos niños adquieren un tono amarillo, es lo\n" +
                    "que conocemos como ictericia. El pediatra valorará la necesidad de\n" +
                    "realizarle pruebas, pero en la mayoría de los casos basta con exponer al\n" +
                    "niño a la luz solar. En una habitación previamente caldeada, se deja al\n" +
                    "pequeño sólo con el pañal y se le expone 10 minutos boca arriba y 10\n" +
                    "minutos boca abajo. La luz permite metabolizar la bilirrubina y eliminarla\n" +
                    "por la orina.",
            "En las primeras 24-48 horas después del nacimiento, el pequeño debe\n" +
                    "deponer el meconio, una deposición muy oscura y pegajosa. Los\n" +
                    "siguientes días, deberá hacer las deposiciones de transición que son\n" +
                    "verdosas, pero más líquidas y, por último, se establece el\n" +
                    "ritmo normal, que varía según el tipo de lactancia.",
            "Al nacer la visión es borrosa, casi en blanco y negro, pero en los días\n" +
                    "sucesivos la imagen se hace más definida y tras unas semanas puede\n" +
                    "ver colores. La agudeza visual de los pequeños madura de forma\n" +
                    "progresiva. Fijará la mirada a partir del mes y a partir de los 3 meses será\n" +
                    "capaz de seguir un objeto hasta 180º.",
    };
    private int about_images_array[] = {
            R.drawable.preguntas_frecuentes1,
            R.drawable.preguntas_frecuentes2,
            R.drawable.preguntas_frecuentes3,
            R.drawable.preguntas_frecuentes4
    };
    private int color_array[] = {
            R.color.colorPrimary,
            R.color.colorPregunta2,
            R.color.colorPrimary,
            R.color.colorPregunta2
    };

//    private int color_array[] = {
//            R.color.red_600,
//            R.color.blue_grey_600,
//            R.color.purple_600,
//            R.color.deep_orange_600
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_frecuentes);


        initComponent();

        Tools.setSystemBarTransparent(this);
    }

    private void initComponent() {
        viewPager = findViewById(R.id.view_pager);
        btn_got_it = findViewById(R.id.btn_got_it);

        // adding bottom dots
        bottomProgressDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btn_got_it.setVisibility(View.GONE);
        btn_got_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        (findViewById(R.id.btn_skip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void bottomProgressDots(int current_index) {
        LinearLayout dotsLayout = findViewById(R.id.layoutDots);
        ImageView[] dots = new ImageView[MAX_STEP];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.overlay_dark_30), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.grey_10), PorterDuff.Mode.SRC_IN);
        }
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            bottomProgressDots(position);
            if (position == title_array.length - 1) {
                btn_got_it.setVisibility(View.VISIBLE);
            } else {
                btn_got_it.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.item_stepper_wizard_color, container, false);
            ((TextView) view.findViewById(R.id.title)).setText(title_array[position]);
            ((TextView) view.findViewById(R.id.description)).setText(description_array[position]);
            ((ImageView) view.findViewById(R.id.image)).setImageResource(about_images_array[position]);
            (view.findViewById(R.id.lyt_parent)).setBackgroundColor(getResources().getColor(color_array[position]));
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return title_array.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}