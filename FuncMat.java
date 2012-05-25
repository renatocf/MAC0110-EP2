/******************************************************/
/**  MAC  110  -  Introdução à  Computação           **/
/**  IME-USP   -  Primeiro  Semestre  de    2012     **/
/**  Turma 45  -  Marcel Parolin Jackowski           **/
/**                                                  **/
/**  Primeiro  Exercício-Programa                    **/
/**  Arquivo:  JogoDeDados.java                      **/
/**                                                  **/
/**  Renato Cordeiro Ferreira            7990933     **/
/**  Vinícius Jorge Vendramini           7991103     **/
/**                                                  **/
/**  26/04/12                                        **/
/******************************************************/ 

class FunçõesMatemáticas
{
  double pi = 3.141592653589793238462643;
  // Usaremos, para o método corrigeValor(), pi com aproxi-
  // mação de 24 casas decimais.
  
  double epsilon = 10e-8;
  /**
   *   A variável 'epsilon' representa o erro das séries de Taylor.
   *   Ao empregá-lo como condição para a interrupção de todos os
   * laços, vamos obtendo termos até que este seja menor que o 
   * 'epsilon', indicando que este termo é menor que a precisão de-
   * sejada e, portanto, pode ser ignorado.
   *   A fim de aumentar a eficiência do código, esta comparação é
   * feita sempre com base no quadrado do epsilon e no quadrado do
   * termo. Com essa escolha, evitamos a chamada de um método para
   * comparar números na forma absoluta, o que teria um custo, na
   * perspectiva de velocidade de execução, maior que o da multi-
   * plicação realizada. De qualquer modo, mantem-se o objetivo de
   * evitar problemas com a mudança de sinal do termo. O ganho re-
   * lativo do uso do quadrado continua mesmo se fosse usado o 
   * método Math.abs() da biblioteca Matemática do Java ou se fosse
   * desenvolvido um método próprio dentro da classe para esta 
   * função específica.
   */
  
  void carregaEpsilon (double e) 
  //Método usado para o usuário definir quantas casas decimais de precisão ele quer nas contas.
  // No caso de não ser chamado, definimos, por padrão, o 'epsilon' com o valor 10e-8 (0.00000001)
  {   
    epsilon = e; // A variável global "epsilon" recebe o valor "e" (e sendo o parâmetro do método) qe o usuário 
                 // deseja quiser. A partir daí, todos os cálculos serão feitas usando o novo epsilon.
  }
  
  
  /**
   *    As funções abaixo têm seus valores definidos de acordo com os
   * Polinômios de Taylor, funções do tipo polinominal criadas para
   * facilitar estimativas de valores para funções irracionais ou cujo
   * cálculo é complicado. Elas são definidas em torno de um certo ponto
   * inicial e convergem para certa constante em dois casos: dentro de
   * uma faixa de valores próxima do ponto inicial para o qual foram
   * definidas ou quando tendem ao infinito (caso quando são chamadas de
   * 'Séries de Taylor'). 
   *    Em todo caso, porém, esta metodologia gera um erro, causado pelo
   * próprio conceito de ser uma função que 'aproxima' os valores da ima-
   * gem das funções originais que os descrevem (por exemplo, sempre há
   * um erro na aproximação por Polinômios de Taylor de um valor para a
   * raiz, tomando em relação o valor que a função raiz real geraria).
   *    Por conta da limitação computacional de não ser capaz de traba-
   * lhar com o conceito de 'infinito' naturalmente, os métodos mostrados
   * abaixo só funcionam dentro de um certo 'raio de ação' que varia de 
   * 0 a 2 unidades (para logaritmo e raiz). Os polinômios usados são
   * criados com base no ponto 0 e por isso podem também ser chamados de
   * Polinômios ou Séries de MacLaurien.
   */
  
  
  double corrigeValor(double x)
    //   Esta função tem por objetivo corrigir o valor
    // de PI para os métodos sen() e cos(). Pela carac-
    // terística periódica das funções correspondentes,
    // qualquer valor abaixo de 0 ou acima de 2 PI é
    // equivalente ao valor de sen/cos para o domínio
    // [0, 2*PI] (fechado, subconjunto dos reais). Por-
    // tanto, é importante, a fim de gerar algoritmos 
    // menos comlexos no cálculo de sen e cos, ter um
    // método que devolva valores mais simples e cujos 
    // sen/cos são equivalentes aos do valor original.
  {
    if(x <= 0)
    { // Para sen/cos menores que 0
      for(int i = 0; x < 0; i++)
        x += 2*pi;
    }
    
    if(x >= 2*pi)
    { // Para sen/cos maiores que 2 pi
      x = x % 2*pi;
    }
    return x;
  }
  
  
  double sen(double x)
    /**
     * O polinômio de Taylor para a função seno é:
     * sen x = x/1! - x^3/3! + x^5/5! - x^7/7! + ... + (-1)^n * x^(2n+1) / (2n + 1)!
     */
  {   
    x = corrigeValor(x); // Corrigindo o valor de 'x' para o intervalo [0, 2*pi]
    double seno = 0;
    double termo = x; // O primeiro termo é inicializado com o valor
                      // de 'x' - os outros a partir deste serão calculados
                      // com base neste primeiro termo
       
    for (int n=1; (termo*termo) >= (epsilon * epsilon); n=n+2) 
      // 'n' define os expoentes de potência e o valor do fato-
      // rial. Começa por 1 para mantê-los sempre ímpares, con-
      // forme o polinômio de Taylor para o seno.
    {
      seno = seno + termo;
      termo = -termo*x*x/((n+1)*(n+2));
    }
    
    return seno;
  }
  
  
  double cos(double x)
    /**
     * O polinômio de Tayor para a função cosseno é:
     * cos x = 1 - x^2/2! + x^4/4! - x^6/6! + ... + (-1)^n * x^n / (2*n)!
     */
  {
    x = corrigeValor(x); // Corrigindo o valor de 'x' para o intervalo [0, 2*pi]
    double cosseno = 0;
    double termo = 1; // O primeiro termo é inicializado com o valor
                      // '1' - os outros a partir deste serão calculados
                      // com base neste primeiro termo
    
    
    for (int n=0; (termo*termo) >= (epsilon * epsilon); n=n+2) 
      // 'n' define os expoentes de potência e o valor do fato-
      // rial. Começa por 0 para mantê-los sempre pares, con-
      // forme o polinômio de Taylor para o seno.
    {
      cosseno = cosseno + termo;
      termo = -termo*x*x/((n+1)*(n+2)); 
      // Alterado o "*x" nessa linha, de acordo com a correção do professor postada no Paca.
    }

    return cosseno;
  }
   
  
  double ln(double umMaisX)
    /**
     * O polinômio de Tayor para a função logaritmo natural é:
     * lnx = x - x^2/2 + x^3/3 - x^4/4 + ... + (-1)^(n-1) * x^n / n
     */
  {
    if(umMaisX < 0)
      throw new ArithmeticException("Não existe logaritmo para valores negativos.");
    // Lançando uma exceção para parar a execução do método caso se queira calcular
    // o ln de algum valor abaixo de 0, para o qual os logaritmos não estão definidos.
    
    if(umMaisX == 0)
      throw new ArithmeticException("Não existe logaritmo para valores negativos.");
    // Lançando uma exceção para parar a execução do método caso se queira calcular
    // o ln de 0, para o qual a função logarítmica tende ao infinito (assíntota horizontal).
    
    if(umMaisX >= 2)
      throw new ArithmeticException("Este método não pode ser usado para calcular valores de ln acima de 2.");
    // Lançando uma exceção para parar a execução do método caso se queira calcular
    // a raiz quadrada de algum valor maior ou igual a 2, para o qual o polinômio de Taylor 
    // passa a ser ineficiente.    
    
    double x = umMaisX - 1; // Como este polinômio de Taylor calcula a apro-
                            // ximação para valores de 1 + x, para achar o 'x'
                            // (variável) passada pelo usuário subtraímos da
                            // variável 1 unidade.
    double ln = 0;
    double termo = x; // O primeiro termo é inicializado com o valor
                      // 'x' - os outros a partir deste serão calculados
                      // com base neste primeiro termo
    
    for (int n=1; (termo*termo) >= (epsilon * epsilon); n++)
      // 'n' define os expoentes de potência e o valor do fato-
      // rial. Começa por 1 para no acréscimo dentro do laço, 
      // já fazer com que o denominador da fração do segundo ter-
      // mo seja 2, conforme o polinômio de Taylor para o seno.
    {
      ln = ln + termo;
      termo = -termo*x*n/(n+1);
      // Corrigindo a função 'raiz' para a forma conforme descrito na
      // forma do termo geral
    }
    
    return ln;
  }
  
        
  double raiz(double umMaisX)
    /**
     * O polinômio de Tayor para a função raiz é:
     * raiz x = 1 + x/2 - x^2/8 + x^3/16 - 5*x^4/128 + ... + (-1)^n * (2n)! * x^n / ((1-2*n) * (n!)^2 * 4^n
     */
  {
    if(umMaisX < 0)
      throw new ArithmeticException("Não existe raiz quadrada para valores negativos.");
    // Lançando uma exceção para parar a execução do método caso se queira calcular
    // a raiz quadrada de algum valor maior ou igual a 2, para o qual raízes de expoente par
    // não estão definidas no domínio real.
    
    if(umMaisX == 0)
      throw new ArithmeticException("Não existe logaritmo para valores negativos.");
    // Pelo enunciado, o domínio de utilização desta função foi definido para o in-
    // tervalo |x| < 1, o que resulta que umMaisX está contida em (0, 2).
    
    if(umMaisX >= 2)
      throw new ArithmeticException("Este método não pode ser usado para calcular valores de raiz acima de 2.");
    // Lançando uma exceção para parar a execução do método caso se queira calcular
    // a raiz quadrada de algum valor acima de 0, para o qual o polinômio de Taylor 
    // passa a ser ineficiente.
    
    double x = umMaisX - 1; // Analogamente ao caso do logaritmo, como este
                            // polinômio de Taylor calcula a aproximação para
                            // 1 + x, para achar a raiz da variável passada 
                            // pelo usuário (umMaisX) calulamos a função 
                            // polinomial correspondente para esta variável
                            // subtraída de 1 unidade (umMaisX - 1)
    double raiz = 0;
    double termo = 1; // O primeiro termo é inicializado com o valor
                      // '1' - os outros a partir deste serão calculados
                      // com base neste primeiro termo
    
    for(int n=0; (termo*termo) >= (epsilon*epsilon); n++)
      // 'n' define vários expoentes de potência e dois valor de fa-
      // torial. Será inicializado com 0 porque este é o valor neces-
      // sário para, a partir do primeiro termo (inicializado com 1), 
      // resultar no segundo valor de termo (x/2)
    {
      raiz = raiz + termo;
      termo = termo * (-1) * (2*n + 1) * (2*n + 2) * (1-2*n) * x / ((-1-2*n) * (n+1) * (n+1) * 4); 
    }

    return raiz;
  }
  
  
  
  float senFloat(float x)
    /* Este método calcula o seno com variáveis floats (com
     * metade do valor do double, totalizando 4 bytes na memória)
     */
  {
    if(x <= 0)
    { // Para senFloat menores que 0
      for(int i = 0; x < 0; i++)
        x += 2*pi;
    }
    
    if(x >= 2*pi)
    { // Para senFloat maiores que 2 pi
      for(int i = 0; x >= 2*pi; i++)
        x -= 2*pi;
    }
    float seno = 0;
    float termo = x; // O primeiro termo é inicializado com o valor
                      // de 'x' - os outros a partir deste serão calculados
                      // com base neste primeiro termo
       
    for (int n=1; (termo*termo) >= (epsilon * epsilon); n+=2) 
      // 'n' define os expoentes de potência e o valor do fato-
      // rial. Começa por 1 para mantê-los sempre ímpares, con-
      // forme o polinômio de Taylor para o seno.
    {
      seno = seno + termo;
      termo = -termo*x*x/((n+1)*(n+2));
    }
    
    return seno;
  } // float senFloat(float x)
  
} // class FunçõesMat 