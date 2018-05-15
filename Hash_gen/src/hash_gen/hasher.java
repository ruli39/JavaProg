package hash_gen;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author Rz39
 */
public class hasher {
    public hasher(){
    }
         /* md5 Algo */

        public String md5encrypt(String str) throws NoSuchAlgorithmException{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] md5dg = md5.digest();
            StringBuffer md5Digest = new StringBuffer();
            for (byte md5str : md5dg)
                md5Digest.append(Integer.toHexString(md5str & 0xff).toString());
            return md5Digest.toString();
        }
    /*end of md5 */
    
    /* base64 */
        public String base64(String str){
            String encodedstr = Base64.getEncoder().encodeToString(str.getBytes());
            return encodedstr;
        }
    /* end of base64 */
    
    /* sha1 algo */

        public String sha1(String str) throws NoSuchAlgorithmException{
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            sha1.update(str.getBytes());
            byte[] sha1dg = sha1.digest();
            StringBuffer sha1Digest = new StringBuffer();
            for(int i = 0; i < sha1dg.length; i++)
                sha1Digest.append(Integer.toString((sha1dg[i]&0xff)+0x100,16).substring(1));
            return sha1Digest.toString();
        }
}
