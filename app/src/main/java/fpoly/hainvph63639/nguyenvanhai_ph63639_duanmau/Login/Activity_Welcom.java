package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO.ThuThuDAO;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.R;

public class Activity_Welcom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcom);


        setContentView(R.layout.activity_login);

    }
}