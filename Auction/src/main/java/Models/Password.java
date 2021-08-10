package Models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Password {
    private String password;

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return Objects.equals(password, password1.password);
    }

    /**
     * Given a byte[] array, produces a hex String,
     * such as "234a6f". with 2 chars for each byte in the array.
     * @param bytes bytes to convert to string
     * @return printableHash
     */
    private String hexToString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;  // remove higher bits, sign
            if (val<16) builder.append('0'); // leading 0
            builder.append(Integer.toString(val, 16));
        }
        return builder.toString();
    }

    /**
     * Hashes password string using SHA-256 algorithm
     * @return hash string of password
     */
    public String hash() {
        MessageDigest md = null;
        byte[] byteArray = password.getBytes(StandardCharsets.UTF_8);

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(byteArray);
            byte[] hashBytes = md.digest();

            String printableHash = hexToString(hashBytes);
            return printableHash;
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return "";
    }
}
