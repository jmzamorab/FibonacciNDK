package com.fibonaccindk;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FibonacciNDK extends Activity implements View.OnClickListener {
    TextView Resultado;
    Button botonLanzar;
    EditText ValorEntrante;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ValorEntrante = (EditText) findViewById(R.id.ValorEntrante);
        Resultado = (TextView) findViewById(R.id.Resultado);
        botonLanzar = (Button) findViewById(R.id.botonLanzar);
        botonLanzar.setOnClickListener(this);
    }
    public static long fibonacciDalvikR(long n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        return fibonacciDalvikR(n - 1) + fibonacciDalvikR(n - 2);
    }
    public static long fibonacciDalvikI(long n) {
        long previous = -1;
        long result = 1;
        for (long i = 0; i <= n; i++) {
            long sum = result + previous;
            previous = result;
            result = sum;
        }
        return result;
    }
    static {
        System.loadLibrary("fibonacci");
    }
    public static native long fibonacciNativoR(int n);
    public static native long fibonacciNativoI(int n);
    public void onClick(View view) {
        int input = Integer.parseInt(ValorEntrante.getText().toString());
        long start1, start2, stop1, stop2;
        long result;
        String out = "";
// Dalvik - Recursivo
        start1 = System.currentTimeMillis();
        result = fibonacciDalvikR(input);
        stop1 = System.currentTimeMillis();
        out += String.format("Dalvik recursiva - Valor: %d Tiempo: (%d msec)", result, stop1 - start1);
// Dalvik - Iterativo
        start2 = System.currentTimeMillis();
        result = fibonacciDalvikI(input);
        stop2 = System.currentTimeMillis();
        out += String.format("\nDalvik iterativa-Valor: %d Tiempo: (%d msec)", result, stop2 - start2);
// Nativo - Recursivo
        start1 = System.currentTimeMillis();
        result = fibonacciNativoR(input);
        stop1 = System.currentTimeMillis();
        out += String.format("\nNativo recursivo-Valor: %d Tiempo: (%d msec)", result, stop1 - start1);
// Nativo - Iterativo
        start2 = System.currentTimeMillis();
        result = fibonacciNativoI(input);
        stop2 = System.currentTimeMillis();
        out += String.format("\nNativo iterativo-Valor: %d Tiempo: (%d msec)", result, stop2 - start2);
        Resultado.setText(out);
    }
}