package org.fedorahosted.freeotpbackup;

import androidx.test.runner.AndroidJUnit4;

import com.google.gson.Gson;

import junit.framework.TestCase;

import org.fedorahosted.freeotpbackup.encryptor.MasterKey;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.crypto.AEADBadTagException;

@RunWith(AndroidJUnit4.class)
public class MasterKeyTest extends TestCase {
    @Test
    public void test() throws GeneralSecurityException, IOException {
        String pwd = "MyM4sterPassw0rd";
        String wrongpwd = "Incorrectpwd";

        MasterKey orig = MasterKey.generate(pwd);
        MasterKey read = new Gson().fromJson(new Gson().toJson(orig), MasterKey.class);

        Assert.assertArrayEquals(orig.decrypt(pwd).getEncoded(), read.decrypt(pwd).getEncoded());
        assertEquals(orig.decrypt(pwd).getAlgorithm(), "AES");
        assertEquals(read.decrypt(pwd).getAlgorithm(), "AES");

        try {
            orig.decrypt(wrongpwd);
        } catch (AEADBadTagException e) {
            // Expected
            // javax.crypto.AEADBadTagException:
            // error:1e000065:Cipher functions:OPENSSL_internal:BAD_DECRYPT
        }
    }
}