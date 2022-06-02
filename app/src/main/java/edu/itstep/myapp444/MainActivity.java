package edu.itstep.myapp444;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLL;
    private ArrayList<Product> products = new ArrayList<>();
    private int idCnt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createProducts();


    }

    private void createProducts() {
        this.mainLL = this.findViewById(R.id.mainLL);
        for (int i = 0; i < 50; i++) {
            this.products.add(new Product("Продукт" + (++this.idCnt),
                    Math.round(Math.random() * 10000 / 100),
                    (int) (Math.random() * 50) * 10));
        }
        LayoutInflater inflater = this.getLayoutInflater();

        for (int i = 0; i < this.products.size(); i++) {
            View view = inflater.inflate(R.layout.productitem, this.mainLL, false);
            Product product = this.products.get(i);
            TextView tvName = view.findViewById(R.id.tvName);
            tvName.setText(product.getName());
            TextView tvPrice = view.findViewById(R.id.tvPrice);
            tvPrice.setText(product.getPrice() + " $");
            TextView tvWeight = view.findViewById(R.id.tvWeight);
            tvWeight.setText(product.getWeight() + " gr");
            this.mainLL.addView(view);
        }
    }


    public void btnActionClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btnDel:
                this.delCheckedProduct();
                break;
            case R.id.btnUpd:
                this.updCheckedProducts();
                break;
            case R.id.btnAdd:
                this.addNewProduct();
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void addNewProduct() {
        Product product = new Product("Продукт" + (++this.idCnt),
                Math.round(Math.random() * 10000 / 100),
                (int) (Math.random() * 50) * 10);
        this.products.add(product);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.productitem,
                this.mainLL,
                false);
        TextView tvName = view.findViewById(R.id.tvName);
        tvName.setText(product.getName());
        TextView tvPrice = view.findViewById(R.id.tvPrice);
        tvPrice.setText(product.getPrice() + "$");
        TextView tvWeight = view.findViewById(R.id.tvWeight);
        tvWeight.setText(product.getWeight() + "gr");
        this.mainLL.addView(view);
        ScrollView sv = this.findViewById(R.id.sv1);
        int height = this.mainLL.getChildAt(0).getHeight();
        sv.smoothScrollTo(0, height * this.mainLL.getChildCount());
    }

    @SuppressLint("SetTextI18n")
    private void updCheckedProducts() {
        int cnt = this.mainLL.getChildCount();
        for (int i = cnt - 1; i >= 0; i--) {
            View view = this.mainLL.getChildAt(i);
            CheckBox cb = view.findViewById(R.id.cb1);
            if (cb.isChecked()) {
                Product product = this.products.get(i);
                product.setPrice(product.getPrice() + 1);
                product.setWeight(product.getWeight() + 10);
                TextView tvPrice = view.findViewById(R.id.tvPrice);
                tvPrice.setText(product.getPrice() + "$");
                TextView tvWeight = view.findViewById(R.id.tvWeight);
                tvWeight.setText(product.getWeight() + "gr");
                cb.setChecked(false);
            }
        }
    }

    private void delCheckedProduct() {
        int cnt = this.mainLL.getChildCount();
        for (int i = cnt - 1; i >= 0; i--) {
            View view = this.mainLL.getChildAt(i);
            CheckBox cb = view.findViewById(R.id.cb1);
            if (cb.isChecked()) {
                this.mainLL.removeView(view);
                this.products.remove(i);
            }
        }
    }
}