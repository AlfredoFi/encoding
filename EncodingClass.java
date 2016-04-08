public class EncodingClass {
  private static byte    byte_value = 101;
  private static short  short_value = 5;
  private static int      int_value = 100000001;
  private static long    long_value = 123456787654321L;

  final static int byte_size = Byte.SIZE / Byte.SIZE;
  final static int int_size = Integer.SIZE / Byte.SIZE;
  final static int short_size = Short.SIZE / Byte.SIZE;
  final static int long_size = Long.SIZE / Byte.SIZE;

  private final static int mask = 0xFF;

  public static void printByteArray(byte[] val){
    StringBuilder str = new StringBuilder();
    for(byte b : val){
      str.append(b & mask).append(" ");
    }
    System.out.println(str.toString());
  }

  public static byte[] encodeBigEndian(long val, int size){
    byte[] ret = new byte[size];
    for(int i = 0;  i < size; i++){
      ret[i] =  (byte) (val >> (size - i - 1) * Byte.SIZE);
    }
    return ret;
  } 

  public static byte[] catByteArr(byte[] a, byte[] b){
    byte[] c = new byte[a.length + b.length];
    for(int i = 0; i < a.length; i++){
      c[i] = a[i];
    }
    for(int i = a.length; i < c.length; i++){
      c[i] = b[i - a.length];
    }
    return c;
  }

  public static void main(String[] args){
    System.out.println("byte:" + byte_size + " short:" + 
        short_size + " int:" + int_size + " long:" + long_size);
    byte[] byte2  = encodeBigEndian(345, short_size);
    byte[] byte1  = encodeBigEndian(84, byte_size);
    byte[] byte3  = catByteArr(byte2, byte1);
    byte[] byte8  = encodeBigEndian(1234567654321L, long_size);
    byte[] byte11 = catByteArr(byte3, byte8);
    printByteArray(byte11); 
  }
}
