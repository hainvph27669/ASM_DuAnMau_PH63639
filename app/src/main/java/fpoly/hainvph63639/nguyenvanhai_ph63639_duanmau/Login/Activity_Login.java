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
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.MainActivity;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.R;

public class Activity_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        EditText edUser = findViewById(R.id.edUser);
        EditText edPass = findViewById(R.id.edPass);
        Button btnLogin = findViewById(R.id.btnLogin);

        ThuThuDAO thuThuDAO = new ThuThuDAO(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUser.getText().toString();
                String pass = edPass.getText().toString();
                if(thuThuDAO.checkDN(user,pass)){
                   startActivity(new Intent(Activity_Login.this, MainActivity.class ));
                }else {
                   Toast.makeText(Activity_Login.this, "User hoặc pass sai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}