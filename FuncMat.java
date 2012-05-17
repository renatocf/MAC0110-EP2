class FuncMat 
{
  
  //Lembrete: pensar em trocar os while's por for's.
  
  double epsilon = 10e-8;
  
  void carregaEpsilon (double e) //Método usado para o usuário definir quantas casas decimais de precisão ele quer nas contas.
  { 
    
    epsilon = e; //A variável global "epsilon" recebe o valor "e" que o usuário quiser.
                 //A partir daí, todas as contas serão feitas usando o novo epsilon.
  }
  
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
      termo = -termo*x*x/n; //Corrigi a fórmula adicionando "*x" nessa linha, de acordo com o que o professor corrigiu no Paca.
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
    }
    
    return ln;
  }
        
  double raiz(double umMaisX)
  {
    double x = umMaisX - 1;
    int k = -1;
    double raiz = 0;
    double termo = 1;
    
    //for (int n=1; (termo*termo) >= (epsilon * epsilon); n=n+2) 
    do // while ((termo*termo) >= (epsilon * epsilon))
    {
      raiz = raiz + termo;
      k = k + 1;
      termo = (-1) * termo * (1+2*k) * (2*k + 2) * (1-2*k) * x / ((4)*(k+1)*(k+1)*(-1-2*k));
    } while ((termo*termo) >= (epsilon * epsilon));
    
    return raiz;
  }  
  
}