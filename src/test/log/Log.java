package test.log;

import java.io.*; 
import java.util.Date; 

public class Log { 
      String logFile = "C:/debug.log"; 
      FileWriter fw; 
      static final String ENTER = System.getProperty("line.separator"); 

      public Log() { 
           try { 
                 fw = new FileWriter(logFile, true); 
           } catch (IOException e){e.printStackTrace();} 
      } 

      public void close() { 
           try { 
               fw.close(); 
           } catch (IOException e){} 
      } 

      public void debug(String msg) { 
           try { 
              fw.write(new Date()+ " : "); 
              fw.write(msg + ENTER); 
              fw.flush(); 
          } catch (IOException e) { 
             System.err.println("IOException!"); 
          }
      } 

} 
