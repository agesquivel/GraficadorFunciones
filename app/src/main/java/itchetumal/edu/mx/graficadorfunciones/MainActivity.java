package itchetumal.edu.mx.graficadorfunciones;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout1 = (LinearLayout) findViewById(R.id.LayoutPrincipal);
        Lienzo fondo = new Lienzo(this);
        layout1.addView(fondo);
    }

    class Lienzo extends View {

        public Lienzo(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            //int ancho = canvas.getWidth();
            //int alto = canvas.getHeight();
            //int ancho = this.getRight();
            //int alto = this.getBottom();

            int ancho = this.getMeasuredWidth();
            int alto = this.getMeasuredHeight();

            Paint pincel1 = new Paint();



            pincel1.setARGB(255, 0, 0, 0);
            pincel1.setStyle(Paint.Style.STROKE);
            canvas.drawPoint(ancho / 2, alto / 2, pincel1);
            canvas.drawRect(0, 0, ancho-1, alto-1, pincel1);

            canvas.save();
            canvas.translate(100,100);
            pincel1.setARGB(255, 255, 0, 0);
            canvas.drawRect(ancho/2 - 100, alto/2 - 100, ancho/2+100, alto/2+100, pincel1);

            pincel1.setColor(Color.MAGENTA);
            //canvas.translate(100,100);


            canvas.drawCircle(0, 0, 100, pincel1);
            pincel1.setStrokeWidth(3);

            canvas.restore();


            pincel1.setColor(Color.BLUE);
            pincel1.setStrokeWidth(3);

            //dibujar linea de eje X
            canvas.drawLine(0, alto/2, ancho - 1, alto/2, pincel1);

            //dibujar linea de eje XY
            canvas.drawLine(ancho/2, 0, ancho/2, alto-1, pincel1);

            //Dibujar texto
            pincel1.setARGB(255, 100, 100, 0);
            pincel1.setTextSize(30);
            pincel1.setStrokeWidth(3);
            //canvas.drawText("Alto: " + alto, ancho/2, alto/2, pincel1);
            canvas.drawText("Graficador", ancho/2, alto/2, pincel1);

            pincel1.setColor(Color.RED);
            String texto = "Graficador";

            Rect limites = new Rect();
            pincel1.getTextBounds(texto, 0, texto.length(), limites);

            limites.set(ancho/2 + limites.left, alto/2 + limites.top,
                    ancho/2 + limites.right, alto/2 + limites.bottom);

            canvas.drawRect(limites, pincel1);

            float centroX = limites.exactCenterX();
            float centroY = limites.exactCenterY();

            //Rotar texto a 90 grados

            String textY = "EjeY";

            Rect limTxtY = new Rect();
            pincel1.getTextBounds(textY, 0, textY.length(), limTxtY);

            canvas.save();
            canvas.rotate(-45, centroX, centroY);

            canvas.drawText("Origen canvas girado", centroX, centroY, pincel1);

            int anchoRot = canvas.getHeight();
            int altoRot = canvas.getWidth();

            /*canvas.drawText("Origen canvas rotado", anchoRot, altoRot, pincel1);

            canvas.drawText("Eje Y", altoRot/2, anchoRot/2, pincel1);
            */

            pincel1.setColor(Color.DKGRAY);
            canvas.rotate(-45, centroX, centroY);
            canvas.translate(centroX, centroY);
            canvas.drawText("Texto 90º", 0, 0, pincel1);

            canvas.restore();

            //canvas.rotate(90, ancho/2, alto/2);
            pincel1.setColor(Color.BLACK);
            pincel1.setStrokeWidth(2);

            pincel1.setStyle(Paint.Style.FILL);
            pincel1.setTextSize(20);
            canvas.drawText("Límites: " + limTxtY.toShortString(), 0, alto - 10, pincel1);
            canvas.drawText("Valores CentroX: " + centroX
                             + " CentroY: " + centroY, 0, alto - 30, pincel1);
            canvas.drawText("Dimensiones ancho=" + ancho + " alto="+alto, 0, alto-60, pincel1);
            pincel1.setColor(Color.GREEN);
            pincel1.setStrokeWidth(3);
            //Dibujado de la gráfica de la función seno
            double gradRad = 0;
            double senGrado = 0;

            //Variables para transformación de coordenadas
            int coordX, coordY,
                    factorX, factorY,
                    pixelPantallaX, pixelPantallaY,
                    origPantallaX = 0, origPantallaY=alto/2;

            for (int grado = 0; grado <= 360; grado++){ //CoordX
                gradRad = grado * 3.14/180;
                senGrado = Math.sin(gradRad);   //CoordY

                //Cálculos de coordenadas
                factorX = 1;
                pixelPantallaX = factorX * grado;

                factorY = 100;
                pixelPantallaY = (int) (factorY * senGrado);

                coordX = origPantallaX + pixelPantallaX;
                coordY = origPantallaY - pixelPantallaY;
                //Dibujado de puntos
                canvas.drawPoint(coordX, coordY, pincel1);
            }


        }
    }
}
