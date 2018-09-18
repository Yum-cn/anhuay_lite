package com.common.util;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.io.FileUtils;

import net.sf.json.JSONObject;

public class EncryptUtil {

	private static final String SALT = "SAFECLI98725695";
	
    private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/' };

    private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,
            60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,
            -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
            38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,
            -1, -1 };
    /**
     * BASE64加密
     * @param data
     * @return
     */
    protected static String encryptBase64(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4)
                        | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4)
                    | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2)
                    | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }

    /**
     * Base64 解密
     * @param str
     * @return
     * @throws Exception
     */
    protected static byte[] decryptBase64(String str) throws Exception{
        StringBuffer sb = new StringBuffer();
        byte[] data =  str.getBytes("US-ASCII");
        int len = data.length;
        int i = 0;
        int b1, b2, b3, b4;
        while (i < len) {

            do {
                b1 = base64DecodeChars[data[i++]];
            } while (i < len && b1 == -1);
            if (b1 == -1)
                break;

            do {
                b2 = base64DecodeChars[data[i++]];
            } while (i < len && b2 == -1);
            if (b2 == -1)
                break;
            sb.append((char) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

            do {
                b3 = data[i++];
                if (b3 == 61)
                    return sb.toString().getBytes("iso8859-1");
                b3 = base64DecodeChars[b3];
            } while (i < len && b3 == -1);
            if (b3 == -1)
                break;
            sb.append((char) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

            do {
                b4 = data[i++];
                if (b4 == 61)
                    return sb.toString().getBytes("iso8859-1");
                b4 = base64DecodeChars[b4];
            } while (i < len && b4 == -1);
            if (b4 == -1)
                break;
            sb.append((char) (((b3 & 0x03) << 6) | b4));
        }
        return sb.toString().getBytes("iso8859-1");
    }
  

    /**
     * DES加密
     * @param data:需要加密的数据
     * @return
     */
    public static String encryptDES(String data,String des_key) throws Exception
    {
        String s = null;
        if ( data != null )
        {
            //DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            //从原始密钥数据创建DESKeySpec对象
            DESKeySpec desKeySpec = new DESKeySpec(des_key.getBytes());
            //创建一个密钥工厂，用它将DESKeySpec转化成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKeySpec);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
            //将加密后的数据编码成字符串

            s = encryptBase64(cipher.doFinal(data.getBytes()));
        }
        return s;
    }

    /**
     * DES解密
     * @param data:需要解密的数据
     * @return
     * @throws Exception
     */
    public static String decryptDES(String data,String des_key) throws Exception
    {
        String s = null;
        if ( data != null )
        {
            //DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            //从原始密钥数据创建DESKeySpec对象
            DESKeySpec desKeySpec = new DESKeySpec(des_key.getBytes());
            //创建一个密钥工厂，用它将DESKeySpec转化成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKeySpec);
            //Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密钥初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
            //将加密后的数据解码再解密

            byte[] buf = cipher.doFinal(decryptBase64(data));
            s = new String(buf);
        }
        return s;
    }
	
	public static void main(String[] args) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("test", "hello,Yum!");
		
		try {
			//FileUtils.writeStringToFile(new File("D:/safe_cli.cfg"),EncryptUtil.encryptDES(jsonObject.toString(),SALT), "UTF-8",false);
			System.out.println(EncryptUtil.decryptDES("E5JjSMgB1VnDR6VHg4vmNbfkgNFrobU2QGhmG5GnLZbVknFf7IGElhyQyesRXT7EQe2Ab077VLYuNWOotw9I5KTOQfxfHiUjF8O2Lf5VD5XHka/qAM38Eu0tDRuq4Ew5v/is7/hsU+JRhExotLOtlNo3ndUHAImdyDV8gCEWfPi4YOSe3GlgtO5sktfJ/NCl3jJctJTUYskmk3G+SfHdgzmzgaL5lmE5IYtVaVMk++jl86mYKWQClbmDM9zkag2Zdac/apuqg7fyAP+pGecnrMNHpUeDi+Y1RYIh1XYhxZHHka/qAM38Eu6zGJRQWjZzeR4qq6nNN4GwmhGQd1v+pOSIampyNVNA6bhPbXY9Lhn9l+HdCjSAvkcmq3ME2TnK/QT2Eh++Vx+5gzPc5GoNmbePh8lnVjhb6zRT2R4xbdryAP+pGecnrHQR7I+T1rLzKNE1rMupFXQzBnELv8IvwqUauWyrjsppObOBovmWYTlm8x+pEwGig+m4T212PS4Z7Dw63wRABG/iF/lKHVfdB3o2BNf1sLUld+0lFBYGD/mZCcrHPhqIN9p5KZyRbCbMbIDJbrZDfwHLzBw6+NEZGawlz2OLTzfIrgBiP0EabqysN26MSGr/1Y6qQbDcbNWa", SALT));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
