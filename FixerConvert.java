package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FixerConvert {
   public static void conversion(String date, String base) {
      BufferedReader br = null;
      String newUrls1 = "https://spib.wooribank.com/pib/Dream?withyou=CMCOM0187";
      String newUrls = String.format(newUrls1,  date,base); // %s�� �ش� date�� �����Ѵ�. �� url�� ������ ���� ������ �����ϴ�. 
      URL url = null;
      StringBuffer sb = new StringBuffer();
      String s="";
      
      int flag = 0;
      try {
         url=new URL(newUrls);
         //System.out.println(newUrls);
         br = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
         String msg="";
         while((msg = br.readLine())!=null) {
            
            if(msg.contains(String.format("<td>%s</td>", base))) {
               flag= 1;
            }
         
            if(msg.contains("</tr>"))
            {
               flag = 0;
            }
            //System.out.println(msg);
            if(flag ==1   ) {
               sb.append(msg); // �׳���.
               s = s+msg.trim();
                  
            }
         }
      }catch(Exception e) {
         System.out.println("parse error!!!");
      }
      
/*      ----s----
      <td>USD</td>         
      <td>1,185.50</td>         
      <td><span>2.00</span></td>         
      <td>1,183.50</td>      
      </tr>         
*/   
      s = s.replaceAll("<td>|</td>|/|USD|JPY|EUR|"+String.format("<td>%s</td>", base)
      +"|span class=\"rise\">���","");     
      String [] arr = s.split("<");
      
      switch(base) {
      case "EUR":
    	  base = "����";
    	  break;
      case "USD":
    	  base = "�̱�";
    	  break;
      case "JPY":
    	  base = "�Ϻ�";
    	  break; 
      }

      System.out.println(String.format("%s�� ���� ȯ�� : ", base)+arr[0]);
      System.out.println("���� ��� : "+ arr[3].substring(5,9));
      System.out.println("���ϱ����� : "+ arr[4].substring(5,13));
      System.out.println();
   }
   public static void main(String[] args) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Calendar cal = Calendar.getInstance();
      String date = sdf.format(cal.getTime());
      
      String base = "USD";
      FixerConvert.conversion(date, base);
      
      String base2 = "EUR";
      FixerConvert.conversion(date, base2);
     
      String base3 = "JPY";
      FixerConvert.conversion(date, base3);
     
   }
}














