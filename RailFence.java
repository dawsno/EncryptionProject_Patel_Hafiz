public class RailFence
{


    /**
     * Constructor for objects of class RailFence
     */
    public RailFence()
    {
        
    }
    
    static String encode(String s)throws Exception
 {
  int r=3;
  int c=s.length()/3;
  char mat[][]=new char[r][c];
  int k=0;
   
  String cipherText="";
   
  for(int i=0;i< c;i++)
  {
   for(int j=0;j< r;j++)
   {
    if(k!=s.length())
     mat[j][i]=s.charAt(k++);
    else
     mat[j][i]='X';
   }
  }
  for(int i=0;i< r;i++)
  {
   for(int j=0;j< c;j++)
   {
    cipherText+=mat[i][j];
   }
  }
  return cipherText;
 }
    
  static String decode(String s)throws Exception
 {
  int r=3;
  int c=s.length()/3;
  char mat[][]=new char[r][c];
  int k=0;
  while (s.length()/3*3 != s.length())
  {
      s = s + " ";
    }
  
  String plainText="";
   
   
  for(int i=0;i< r;i++)
  {
   for(int j=0;j< c;j++)
   {
    mat[i][j]=s.charAt(k++);
   }
  }
  for(int i=0;i< c;i++)
  {
   for(int j=0;j< r;j++)
   {
    plainText+=mat[j][i];
   }
  }
   
  return plainText;
 }
}