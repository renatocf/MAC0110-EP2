class FuncMat 
{
  
  double epsilon = 10e-8;
  
  double sen(double x)
  {
    double n = 1;
    double seno = 0;
    double termo = x;
    
    //for (int n=1; (termo*termo) >= (epsilon * epsilon); n=n+2) 
    while ((termo*termo) >= (epsilon * epsilon))
    {
      seno = seno + termo;
      n = n + 2;
      termo = -termo*x*x/(n*(n-1));
    }
    
    return seno;
  }
  
    double cos(double x)
  {
    double n = 1;
    double cosseno = 1;
    double termo = -x*x/2;
    
    //for (int n=1; (termo*termo) >= (epsilon * epsilon); n=n+2) 
    while ((termo*termo) >= (epsilon * epsilon))
    {
      cosseno = cosseno + termo;
      n = n + 2;
      termo = -termo*x/n;
    }
    
    return cosseno;
  }
    
        double ln(double umMaisX)
  {
    double x = umMaisX - 1;
    int n = 1;
    double ln = 0;
    double termo = x;
    
    //for (int n=1; (termo*termo) >= (epsilon * epsilon); n=n+2) 
    while ((termo*termo) >= (epsilon * epsilon))
    {
      ln = ln + termo;
      n = n + 1;
      termo = -termo*x/n;
      System.out.println(termo);
    }
    
    return ln;
  }
        
  
}