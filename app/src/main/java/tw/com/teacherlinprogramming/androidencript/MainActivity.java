package tw.com.teacherlinprogramming.androidencript;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.PrivateKey;
import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {

    PrivateKey privateKey;

    PublicKey publicKey;

    EditText txfOrigional;
    EditText txfEncrypted;
    EditText txfDecrypted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEncrypt = (Button)findViewById(R.id.btnEncrypt);
        Button btnDecrypt = (Button)findViewById(R.id.btnDecrypt);

        txfOrigional = (EditText)findViewById(R.id.txfOrigional);
        txfEncrypted = (EditText)findViewById(R.id.txfEncryptContext);
        txfDecrypted = (EditText)findViewById(R.id.txfResult);

        privateKey = UtilityRSA.loadPrivateKeyFromString(strPKCS8PrivateKey);

        publicKey = UtilityRSA.loadPublicKeyFromString(strPublicKey);
    }

    public void btnEncryptClicked(View v)
    {
        byte[] encryptByte = UtilityRSA.encryptData(txfOrigional.getText().toString().trim().getBytes(),publicKey);
        String encriptContext = Base64.encodeToString(encryptByte,Base64.NO_WRAP);

        txfEncrypted.setText(encriptContext);
    }

    public void btnDecryptClicked(View v)
    {

    }

    String strPKCS8PrivateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANV0lIXpdvmdUqot\n" +
            "6bnQphOBZBqH+NgEf48Fdgtkd5g7zQBx2DCp1e6kLUQnNZ+5ulAMOTi7hdQn+KXS\n" +
            "3llw3M9oOZW309OymExpZxLWj329g0LTW92dmqbomfiBavb5a2K8BYI1Es5eReCL\n" +
            "apGWVZF8/IOcLGFDWoxqwZ4iPV5/AgMBAAECgYEA0YmFmwcZweOMGH7Fp6CIxzEH\n" +
            "YXwE7ZlM1ssXnAtd01Uw4rTe8rZlycPko7iRzV/240l8ipKaxy8NyUp89vPx7OUY\n" +
            "6wrmr441zlOL2D/aK3EOMjTh6fVdVM4jjZtKZKHRScgoaaed8D5UFOIsRyVST7BW\n" +
            "VvNyD1SuRw8LQIgktyECQQDz4bhP2A+DJ0WI7VY4E5lRRKPVPqbvcOpGPMxhHNhD\n" +
            "lg9laAsRNtR+dGPeCpr6KWgk6aT4JIxmw1J/xm+9nPlnAkEA4A/TA12bHbZsLL+x\n" +
            "SZTUNuqXLluc0njuTh08LNDcqYGGREs/SZWDKixHxLJN6HbrWFH/w8XcVOyfIul7\n" +
            "uE4LKQJAA6DVDCMLwAjCiRH22Wi5PDNIOLDw7NkCMz9OTxeNxBukaP7OQ1hMewEi\n" +
            "RzhjK9uX2Ay9zo9Eo3a+cHY0TV7bewJBAItEarU51nNmcuBxmeGRd0JXF/cBjH0q\n" +
            "G31EIKX+gFl/n5QNbV3wZ6Gr+kPhTBmE6Q1xklb+QdArb1KE65iIMdkCQQCiS5VH\n" +
            "/df0o+kaKXzShx/Zis8vb8prwo+kl70g8QqfyhA+eCifoeO5BmkKo1L1640AKIqv\n" +
            "9ksGgIifrSpF85El";

    String strPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVdJSF6Xb5nVKqLem50KYTgWQa\n" +
            "h/jYBH+PBXYLZHeYO80AcdgwqdXupC1EJzWfubpQDDk4u4XUJ/il0t5ZcNzPaDmV\n" +
            "t9PTsphMaWcS1o99vYNC01vdnZqm6Jn4gWr2+WtivAWCNRLOXkXgi2qRllWRfPyD\n" +
            "nCxhQ1qMasGeIj1efwIDAQAB";

}
