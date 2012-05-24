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

class TesteDeDesempenho
{
  double[][] tempos = new double[100][5];
  /* A primeira linha da matriz representa os tempos
   * para a função seno. A segunda, para cosseno. A
   * terceira, para o logaritmo natural e a quarta,
   * para a raiz.
   */
  
  double[][] estatísticas = new double[5][2];
  /* Este vetor armazena, dentro de si, os dados esta-
   * tísticos (média e desvio-padrão) relativos aos 100
   * testes realizados dentro da classe. Na primeira li-
   * nha, ficam guardados os dados de média relativa às
   * cinco funções - seno (em [0][0]), cosseno ([1][0]),
   * ln ([2][0]), raiz ([3][0]) e senoFloat ([4][0]).
   */
  
  double pi = 3.141592653589793238462643;
  // Este atributo contém pi com 24 dígitos
  
  FunçõesMatemáticas função = new FunçõesMatemáticas();
  /*   Instanciamos um objeto da classe 'FunçõesMatemáticas'
   * para testar os tempos das funções executadas com valo-
   * res pré-definidos (estes valores são o intervalo do do-
   * mínio no qual as funções podem ser executadas dividido
   * por 100, de modo a criar um um acréscimo constante de
   * um valor para o seguinte).
   */
  
  
  void carregaTemposSeno()
  {
    double tempoInicial; // O tempo, registrado pelo reló-
                         // gio interno do computador, antes
                         // de executar a função.
    double tempoFinal; // O tempo, registrado pelo reló-
                       // gio interno do computador, depois
                       // de executar a função.
      
    for(int i=0; i < 100; i++)
    {
      double entrada = i * 2 * pi / 100;
      // A entrada é calculada com base no intervalo [0, 2*pi]. Apesar da função
      // estar definido para todo o eixo real, todos os valores de entrada vêm deste
      // intervalo, pois a função é periódica.
      tempoInicial = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
      função.sen(entrada);
      tempoFinal = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
      tempos[i][0] = tempoFinal - tempoInicial;
      // Carregando na matriz 'tempos' com o tempo de execução do método,
      // calculado com base no tempo do relógio interno do computador final
      // menos o tempo registrado inicialmente.
    }
  } // int carregaTemposSeno()
  
  
  void carregaTemposCosseno()
  {
    double tempoInicial;
    double tempoFinal;
    
    for(int i=0; i < 100; i++)
    {
      double entrada = i * 2 * pi / 100;
      // A entrada é calculada com base no intervalo [0, 2*pi]. Apesar da função
      // estar definido para todo o eixo real, todos os valores de entrada vêm deste
      // intervalo, pois a função é periódica.
      tempoInicial = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos.
      função.cos(entrada);
      tempoFinal = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos.
      tempos[i][1] = tempoFinal - tempoInicial;
      // Carregando os tempos na matriz para a função cosseno.
    }
  } // int carregaTemposCosseno()
  
  
  
  void carregaTemposLogaritmo()
  {
    double tempoInicial;
    double tempoFinal;
      
    for(int i=0; i < 50; i++)
    {
      double entrada = i * 2 / 100 + 0.01;
      // A primeira parte da entrada é calculada para o intervalo [0.01, 0.99],
      // com um total de 49 valores carregados.
      tempoInicial = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
      função.ln(entrada);
      tempoFinal = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
      tempos[i][2] = tempoFinal - tempoInicial;
    }
    
    // Para incluir o valor 1.0 como entrada na função, reservamos
    // para ele o endereço [50][3] na matriz 'tempos'. O objetivo é
    // justamente colocar todos os valores 
    tempoInicial = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
    função.ln(1.0);
    tempoFinal = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
    tempos[50][2] = tempoFinal - tempoInicial;
    
    for(int i=51; i < 100; i++)
    {
      double entrada = i * 2 / 100 + 0.01;
      // A segunda parte da entrada é calculada para o intervalo [1.01, 1.99],
      // com um total de 50 valores carregados.
      tempoInicial = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
      função.ln(entrada);
      tempoFinal = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
      tempos[i][2] = tempoFinal - tempoInicial;
    }
  } // int carregaTemposLogaritmo()
  
  
  
  void carregaTemposRaiz()
  {
    double tempoInicial;
    double tempoFinal;
      
    for(int i=0; i < 50; i++)
    {
      double entrada = i * 2 / 100 + 0.01;
      tempoInicial = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
      função.raiz(entrada);
      tempoFinal = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
      tempos[i][3] = tempoFinal - tempoInicial;
    }
    
    tempoInicial = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
    função.raiz(1.0);
    tempoFinal = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
    tempos[50][3] = tempoFinal - tempoInicial;
    
    for(int i=51; i < 100; i++)
    {
      double entrada = i * 2 / 100 + 0.01;
      tempoInicial = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
      função.raiz(entrada);
      tempoFinal = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
      tempos[i][3] = tempoFinal - tempoInicial;
    }
  } // int carregaTemposRaiz()
  
  
  
  void carregaTemposSenoFloat()
  {
    double tempoInicial;
    double tempoFinal;
      
    for(int i=0; i < 100; i++)
    {
      float pi = 3.141592653589793238462643f; // pi com tipo float
      float entrada = i * 2 * pi / 100;
      tempoInicial = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
      função.senFloat(entrada);
      tempoFinal = System.nanoTime() / 10e6; // Divisão para transformar em milissegundos
      tempos[i][4] = tempoFinal - tempoInicial;
    }
  } // int carregaTemposSeno()
  
  
  
  void estatísticas()
  {
    // Calcula a média:
    for(int i = 0; i < tempos[0].length; i++)
    {
      double soma = 0; // Armazena o somatório de todos os tempos do vetor 'tempos'
      for(int j = 0; j < tempos.length; j++)
        soma += tempos[j][i];
      // Calcula a soma
      
      estatísticas[i][0] = soma / 100;
      // Armazena na matriz 'estatísticas' (primeira linha) as médias de
      // cada uma das funções.
    }
    
    // Calcula dsvios-padrão:
    for(int i = 0; i < tempos[0].length; i++)
    {
      double somaQuadrados = 0; // Armazena o somatório da subtração termo menos
                                // média elevado ao quadrado: (termo-média)^2
      for(int j = 0; j < tempos.length; j++)
        somaQuadrados += (tempos[j][i] - estatísticas[i][1]) * (tempos[j][i] - estatísticas[i][1]);
      // Calcula somaQuadrados
      
      estatísticas[i][1] = Math.sqrt(somaQuadrados/100);
      // Armazena na matriz 'estatísticas' (segunda linha) os desvios-padrão
      // de cada uma das funções.
    }
  } // void estatísticas()
  
  
  
  void imprimeRelatórioGeralDeTempos()
  {
    carregaTemposSeno();
    carregaTemposCosseno();
    carregaTemposLogaritmo();
    carregaTemposRaiz();
    estatísticas();
    
    String[] funções = {"seno", "cosseno", "logaritmo natural", "raiz"};
    
    System.out.println("Relatório de Desempenho das Funções Matemáticas com Séries de Taylor");
    System.out.println();
    
    
    for(int i = 0; i < funções.length; i++)
    {
      System.out.println("Função " + funções[i] + ":");
      System.out.println();
      
      System.out.println("A função " + funções[i] + " teve os seguintes valores de teste: ");
      
      for(int j = 0; j <= tempos.length - 51; j++)
      {
        System.out.print((j+1) + ": " + tempos[j][i] + " ms          ");
        System.out.println((j+51) + ": " + tempos[j+50][i] + " ms");
      }
        
      System.out.println();
      System.out.println("Média das medições: " + estatísticas[i][0]);
      System.out.println("Desvio-padrão das medições: " + estatísticas[i][1]);
      
      if(i < 3)
        System.out.println();
    }
    
  } // void imprimeRelatórioGeralDeTempos()
  
  
  
  void imprimeComparativoDeSenos()
  {
    carregaTemposSeno();
    carregaTemposSenoFloat();
    estatísticas();
    
    double[] ganhoMédio = new double[100];
    for(int i=0; i < ganhoMédio.length; i++)
      ganhoMédio[i] = (tempos[i][4] * 100) / tempos[i][0]; // Multiplica-se por 100 para obter a porcentagem
    
    
    System.out.println("Relatório Comparativo das Funções Seno e Cosseno Calculadas com Variáveis Double e Float");
    System.out.println();
    
    for(int j = 0; j < tempos.length; j++)
    {
      System.out.println("Double(" + (j+1) + "): " + tempos[j][0] + " ms          Float(" + (j+1) + "): " + tempos[j][4] + " ms");
      System.out.println("Ganho médio para o teste " + (j+1) + ": " + ganhoMédio[j] + " %");
      System.out.println();
    }
    
    System.out.println("Média do seno com double: " + estatísticas[0][0] + ";");
    System.out.println("Média do seno com float: " + estatísticas[4][0] + ".");
    System.out.println("Ganho médio do uso de float no lugar de double em relação à média: " + estatísticas[4][0] * 100 / estatísticas[0][0]);
    System.out.println();
    
    System.out.println("Desvio-padrão do seno com double: " + estatísticas[0][1] + ";");
    System.out.println("Desvio-padrão do seno com float: " + estatísticas[4][1] + ".");
    System.out.println("Ganho médio do uso de float no lugar de double em relação ao desvio-padrão: " + estatísticas[4][0] * 100 / estatísticas[0][0]);

  } // void imprimeComparativoDeSenos()
  
  
} // class TesteDeDesempenho