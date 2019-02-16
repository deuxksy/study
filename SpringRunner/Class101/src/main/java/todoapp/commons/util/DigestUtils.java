package todoapp.commons.util;

import todoapp.commons.SystemException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ?΄? ?¨?(hash function) ? ?Έλ¦¬ν°
 *
 * @author springrunner.kr@gmail.com
 */
public interface DigestUtils {

    /**
     * SHA-256 ?κ³ λ¦¬μ¦μΌλ‘? ?? ₯? λ¬Έμ?΄? ?΄? κ°μ ??±?©??€.
     *
     * @param value ??? λ¬Έμ?΄
     * @return
     */
    static String sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return new String(digest.digest(value.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException error) {
            throw new SystemException("SHA-256 ?κ³ λ¦¬μ¦μ΄ ??΅??€.", error);
        }
    }

}
