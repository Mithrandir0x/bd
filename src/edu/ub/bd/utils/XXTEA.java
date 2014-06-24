package edu.ub.bd.utils;

import java.io.UnsupportedEncodingException;

/**
 * Implementacio de cifratge en bloc de TEA, anomenat XXTEA.
 * 
 * Per a mes informacio: http://en.wikipedia.org/wiki/XXTEA
 * 
 * @author Internet
 */
public class XXTEA {

        private XXTEA() {
        }

        /**
         * Gets Base64 for a string.
         *
         * @param s the s
         * @return the bAS e64
         */
        private static String getBASE64(String s) {
                if (s == null)
                        return null;
                return Base64.encode(s.getBytes());
        }

        /**
         * Gets a string from a base64.
         *
         * @param s the s
         * @return the from bas e64
         */
        private static String getFromBASE64(String s) {
                if (s == null)
                        return null;
                
                try {
                        byte[] b = Base64.decode(s);
                        return new String(b);
                } catch (Exception e) {
                        return null;
                }
        }

        /**
         * Hexadecimal to string.
         *
         * @param theHex the the hex
         * @return the string
         */
        private static String hex2Str(String theHex) {
                String theRst = "";
                int byteCount = 0;
                byte[] theByte = new byte[theHex.length() / 2];

                try {
                        for (int i = 0; i < theHex.length(); i += 2) {
                                if (!theHex.substring(i, i + 2).equalsIgnoreCase("ff")) {
                                        theByte[i / 2] = Integer.decode(
                                                        "0X" + theHex.substring(i, i + 2)).byteValue();
                                        byteCount = byteCount + 1;
                                }
                        }
                        theRst = new String(theByte, 0, byteCount, "GBK");
                } catch (Exception Ue) {
                        System.out.println(Ue.toString());
                }
                return theRst;
        }

        /**
         * String to hexadecimal.
         *
         * @param theStr the the str
         * @return the string
         */
        private static String str2Hex(String theStr) {
                byte[] bytes;
                String result = "";
                int tmp;
                String tmpStr;
                try {
                        bytes = theStr.getBytes("GBK");
                        for (int i = 0; i < bytes.length; i++) {
                                if (bytes[i] < 0) {
                                        tmp = 256 + bytes[i];
                                        tmpStr = Integer.toHexString(tmp).toUpperCase();
                                        result += tmpStr;
                                } else {
                                        tmpStr = Integer.toHexString(bytes[i]).toUpperCase();

                                        result += tmpStr.length() == 1 ? "0" + tmpStr : tmpStr;
                                }
                        }
                } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                return result;
        }

        /**
         * Byte to string.
         *
         * @param b the b
         * @return the string
         */
        private static String byteToString(byte b) {
                byte high, low;
                byte maskHigh = (byte) 0xf0;
                byte maskLow = 0x0f;

                high = (byte) ((b & maskHigh) >> 4);
                low = (byte) (b & maskLow);

                StringBuffer buf = new StringBuffer();
                buf.append(findHex(high));
                buf.append(findHex(low));

                return buf.toString();
        }

        /**
         * Bytes to string.
         *
         * @param b the b
         * @return the string
         */
        private static String bytesToString(byte[] b) {
                String s_temp = "";
                for (int i = 0; i <= b.length - 1; i++) {
                        s_temp = s_temp + byteToString(b[i]);
                }
                return hex2Str(s_temp);
        }

        private static char findHex(byte b) {
                int t = new Byte(b).intValue();
                t = t < 0 ? t + 16 : t;

                if ((0 <= t) && (t <= 9)) {
                        return (char) (t + '0');
                }

                return (char) (t - 10 + 'A');
        }

        /**
         * Encrypt data with key.
         *
         * @param data
         * @param key
         * @return
         */
        public static byte[] encrypt(byte[] data, byte[] key) {
                if (data.length == 0) {
                        return data;
                }
                return toByteArray(encrypt(toIntArray(data, true), toIntArray(key,
                                false)), false);
        }

        /**
         * Decrypt data with key.
         *
         * @param data
         * @param key
         * @return
         */
        public static byte[] decrypt(byte[] data, byte[] key) {
                if (data.length == 0) {
                        return data;
                }
                return toByteArray(decrypt(toIntArray(data, false), toIntArray(key,
                                false)), true);
        }

        /**
         * Encrypt data with key.
         *
         * @param v
         * @param k
         * @return
         */
        public static int[] encrypt(int[] v, int[] k) {
                int n = v.length - 1;

                if (n < 1) {
                        return v;
                }
                if (k.length < 4) {
                        int[] key = new int[4];

                        System.arraycopy(k, 0, key, 0, k.length);
                        k = key;
                }
                int z = v[n], y = v[0], delta = 0x9E3779B9, sum = 0, e;
                int p, q = 6 + 52 / (n + 1);

                while (q-- > 0) {
                        sum = sum + delta;
                        e = sum >>> 2 & 3;
                        for (p = 0; p < n; p++) {
                                y = v[p + 1];
                                z = v[p] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y)
                                                + (k[p & 3 ^ e] ^ z);
                        }
                        y = v[0];
                        z = v[n] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y)
                                        + (k[p & 3 ^ e] ^ z);
                }
                return v;
        }

        /**
         * Decrypt data with key.
         *
         * @param v
         * @param k
         * @return
         */
        public static int[] decrypt(int[] v, int[] k) {
                int n = v.length - 1;

                if (n < 1) {
                        return v;
                }
                if (k.length < 4) {
                        int[] key = new int[4];

                        System.arraycopy(k, 0, key, 0, k.length);
                        k = key;
                }
                int z = v[n], y = v[0], delta = 0x9E3779B9, sum, e;
                int p, q = 6 + 52 / (n + 1);

                sum = q * delta;
                while (sum != 0) {
                        e = sum >>> 2 & 3;
                        for (p = n; p > 0; p--) {
                                z = v[p - 1];
                                y = v[p] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y)
                                                + (k[p & 3 ^ e] ^ z);
                        }
                        z = v[n];
                        y = v[0] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y)
                                        + (k[p & 3 ^ e] ^ z);
                        sum = sum - delta;
                }
                return v;
        }

        /**
         * Convert byte array to int array.
         *
         * @param data
         * @param includeLength
         * @return
         */
        private static int[] toIntArray(byte[] data, boolean includeLength) {
                int n = (((data.length & 3) == 0) ? (data.length >>> 2)
                                : ((data.length >>> 2) + 1));
                int[] result;

                if (includeLength) {
                        result = new int[n + 1];
                        result[n] = data.length;
                } else {
                        result = new int[n];
                }
                n = data.length;
                for (int i = 0; i < n; i++) {
                        result[i >>> 2] |= (0x000000ff & data[i]) << ((i & 3) << 3);
                }
                return result;
        }

        private static int[] stringToIntArray(String str) throws Exception
        {
            int slength=str.length();
            int retval[] = new int[(int) Math.ceil(slength / 4.0)];

            for (int i=0; i<retval.length; i++) {
                // note little-endian encoding - endianness is irrelevant as long as
                // it is the same in longsToStr()

                int char0=(i*4<slength)?str.charAt(i*4):0;
                int char1=(i*4+1<slength)?str.charAt(i*4+1):0;
                int char2=(i*4+2<slength)?str.charAt(i*4+2):0;
                int char3=(i*4+3<slength)?str.charAt(i*4+3):0;

                retval[i] = char0 + (char1<<8) +
                       (char2<<16) + (char3<<24);
            }
            return retval;
        }

        private static String intArrayToString(int[] sa) throws Exception
        {
            StringBuffer res=new StringBuffer();

            for(int i=0;i<sa.length;i++)
                {
                int ii=sa[i];

                char ch0=(char) (ii& 0xFF);
                char ch1=(char) (ii>>>8& 0xFF);
                char ch2=(char) (ii>>>16& 0xFF);
                char ch3=(char) (ii>>>24& 0xFF);

                if(ch0!=0)
                    res.append(ch0);

                if(ch1!=0)
                    res.append(ch1);

                if(ch2!=0)
                    res.append(ch2);

                if(ch3!=0)
                    res.append(ch3);
                }

            return res.toString();
        }

        /**
         * Convert int array to byte array.
         *
         * @param data
         * @param includeLength
         * @return
         */
        private static byte[] toByteArray(int[] data, boolean includeLength) {
                int n = data.length << 2;

                ;
                if (includeLength) {
                        int m = data[data.length - 1];

                        if (m > n) {
                                return null;
                        } else {
                                n = m;
                        }
                }
                byte[] result = new byte[n];

                for (int i = 0; i < n; i++) {
                        result[i] = (byte) ((data[i >>> 2] >>> ((i & 3) << 3)) & 0xff);
                }
                return result;
        }

        /**
         * Encrypts a string.
         *
         * @param data the data
         * @param password the password
         * @return the string
         */
        public static String encryptString(String data,String password)
        {
            try
            {
                int datab[]=stringToIntArray(data);
                int pwdb[]=stringToIntArray(password);

                int res[]=encrypt(datab,pwdb);

                return Base64.encode(toByteArray(res,false));
            }
            catch(Exception exx)
            {
                throw new RuntimeException("EXX ENCRYPTING");
            }
        }

        /**
         * Decrypts a string.
         *
         * @param data the data
         * @param password the password
         * @return the string
         */
        public static String decryptString(String data,String password)
        {
            try
            {
                int datab[]=toIntArray(Base64.decode(data),false);
                int pwdb[]=stringToIntArray(password);

                int res[]=decrypt(datab,pwdb);

                return intArrayToString(res);
            }
            catch(Exception exx)
            {
                throw new RuntimeException("EXX ENCRYPTING");
            }
        }

        /**
         * Test
         *
         * @param args the arguments
         * @throws Exception the exception
         */
        public static void main(String[] args) throws Exception {
            String pwd = "cursbd1314/.";
            String /* test,*/ encTest, decTest;
            String[] dnis = { "20100393R", "20200288L", "20300643G", "20400453H", "32013847R", "38227338T", "42516878R", "43382738F" };
            
            for ( String dni : dnis )
            {
                encTest = encryptString(dni, pwd);
                //decTest = decryptString(encTest, pwd);
                //System.out.println(1 + " (" + dni + ")");
                System.out.println("  >>> " + String.format("%s ,%s,", dni, encTest));
                //System.out.println("  <<< " + decTest);
                //System.out.println("  assert " + ( dni.equals(decTest) ));
                //System.out.println();
            }
        }
}