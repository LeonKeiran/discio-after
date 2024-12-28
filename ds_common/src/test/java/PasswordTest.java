import cn.hutool.crypto.KeyUtil;
import com.zeeyeh.discio.common.utils.SecureUtil;

/**
 * @author Leon_Keiran
 * @description
 * @date 2024/12/25/周三 15:33:03
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
public class PasswordTest {
    public static void main(String[] args) {
        String password = "123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456123456";
        String key = "0ryHafQycR/vzZ0YaqdcjtBH1kXSb9T0RsO7+azSdKI=";
        String iv = "x/LqK4zqLdRUwn+6fyAxFw==";
        String encryptedPassword = SecureUtil.encryptPassword(password, key, iv);
        System.out.println(encryptedPassword);
        System.out.println(encryptedPassword.length());
    }
}
