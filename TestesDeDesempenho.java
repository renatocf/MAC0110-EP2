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

class TestesDeDesempenho
{
  FunçõesMatemáticas teste = new FunçõesMatemáticas();
  /**
   * Instanciando um objeto da classe 'FunçõesMatemáticas'
   * para poder realizar as medições de tempo a partir
   * dos métodos da classe.
   */
  
  
  double[][] estatística(double[][] tempos, int l)
    /**
     * O método estatística' é auxiliar e tem
     * por objetivo devolver uma matriz que con-
     * tém, na primeira linha, as médias equi-
     * valentes aos tempos de execução das fun-
     * ções 'seno', 'cosseno', 'ln' e 'raiz' 
     * respectivamente. Na segunda linha, esta
     * matriz contém os desvios-padrão destes 
     * mesmos tempos (apresentados na mesma or-
     * dem da primeira linha).
     */
  {
    double[][] estatística = new double[2][l];
    // Matriz que será retornada pelo método.
      
    for(int k=0; k < tempos.length; k++)
      // Laço para variar a 'coluna' da matriz
      // e carregar a média e desvio-padrão para
      // as diferentes funções definidas nos mé-
      // todos em 'FunçõesMatemáticas'
    {
      estatística[0][k] = 0;
      for(int i=0; i < tempos[0].length; i++)
        estatística[0][k] += tempos[k][i] / 100;
      // Carregando a média na primeira linha da
      // matriz 'estatística'.
      
      double variância = 0; // para calcular, primeiramen-
                            // te, a variância dos valores
      for(int j=0; j < tempos[0].length; j++)
        variância += (tempos[k][j] - estatística[0][k])*(tempos[k][j] - estatística[0][k]);
      estatística[1][k] = Math.sqrt(variância); // Armazenando na matriz o
                                                // valor do desvio-padrão
                                                // (raiz da variância).
      // Carregando a média na primeira linha da
      // matriz 'estatística'.
    }
    
    return estatística;
  }
  
  
  
  void imprimeRelatórioDeTempos(double valor)
    /* Este método cria um relatório com 100
     * testes de cada função (seno, cosseno,
     * ln, raiz) para o valor passado pelo 
     * usuário.
     */
  {
    double tempos[][] = new double[4][100];
    // A matriz acima guarda, em cada linha, os
    // valores de teste medidos. Na primeira, ficam
    // os testes para 'sen'; na segunda, para 
    // 'cos'; na terceira, para 'ln'; e na quarta,
    // para 'raiz'.
    
    double tempoInicial = 0;
    double tempoFinal = 0;
    // Estas variáveis são criadas fora para evitar
    // que precisem ser criadas a cada execução do
    // laço.
    
    for(int i=0; i < tempos[0].length; i++)
      /* Realizando os 100 testes para o método 'sen' */
    {
      tempoInicial = System.nanoTime() / 10e6;
      // Divisão para transformar em milissegundos.
      
      teste.sen(valor);
      
      tempoFinal = System.nanoTime() / 10e6;
      // Divisão para transformar em milissegundos.
      
      tempos[0][i] = tempoFinal - tempoInicial;
      // Guarda o tempo de execução da função seno para
      // o valor passado pelo usuário no teste 'i'.
    }

    for(int i=0; i < tempos[0].length; i++)
      /* Realizando os 100 testes para o método 'cos' */
    {
      tempoInicial = System.nanoTime() / 10e6;
      teste.cos(valor);
      tempoFinal = System.nanoTime() / 10e6;
      tempos[1][i] = tempoFinal - tempoInicial;
      // Guarda o tempo de execução da função cosseno para
      // o valor passado pelo usuário no teste 'i'.
    }
    
    for(int i=0; i < tempos[0].length; i++)
      /* Realizando os 100 testes para o método 'ln' */
    {
      tempoInicial = System.nanoTime() / 10e6;
      teste.ln(valor);
      tempoFinal = System.nanoTime() / 10e6;
      tempos[2][i] = tempoFinal - tempoInicial;
      // Guarda o tempo de execução da função ln para
      // o valor passado pelo usuário no teste 'i'.
    }
    
    for(int i=0; i < tempos[0].length; i++)
      /* Realizando os 100 testes para o método 'raiz' */
    {
      tempoInicial = System.nanoTime() / 10e6;
      teste.raiz(valor);
      tempoFinal = System.nanoTime() / 10e6;
      tempos[3][i] = tempoFinal - tempoInicial;
      // Guarda o tempo de execução da função raiz para
      // o valor passado pelo usuário no teste 'i'.
    }
    
    double[][] estatística = estatística(tempos, 4);
    // Salvando na matriz 'estatística' o retorno do método
    // 'estatística' (com os valores de média e desvio-padrão
    // das funções).
    
    /* Imprimindo o relatório */
    
    String[] funções = {"seno", "cosseno", "logaritmo natural", "raiz"};
    // Para automatizar a impressão de acordo com um modelo padrão.
    
    System.out.println("Relatório de Desempenho das Funções Matemáticas com Séries de Taylor");
    System.out.println(); // Título do relatório
    
    for(int i = 0; i < funções.length; i++)
    {
      for(int j = 0; j < tempos[0].length; j++)
        System.out.println("Teste " + i + ": " + tempos[i][j]);
      // Imprimindo a medição de tempo para os testes individuais
      System.out.println("Função " + funções[i] + ":"); // Nome da função
      System.out.println();
      System.out.println("Média das medições: " + estatística[0][i]); // Média dos valores
      System.out.println("Desvio-padrão das medições: " + estatística[1][i]); // Desvio-padrão dos valores
      
      if(i < 3)
        System.out.println(); // Pulando linhas entre as funções
    }
  } // void imprimeRelatórioDeTempos(double valor)
  
  
  
  void imprimeComparativoDeSenos(double valor)
    /**
     * Este método imprime um relatório compara-
     * tivo entre o cálculo do seno usando variá
     * veis 'double' com este mesmo cálculo usando
     * variáveis 'float'
     */
  {
    double[][] tempos = new double[2][100];
    // A matriz acima armazena as medições de tempo
    // para os métodos 'sen' e 'senFloat', para com-
    // pará-los posteriormente.
    
    double tempoInicial = 0;
    double tempoFinal = 0;
    // Estas variáveis foram criadas fora dos laços
    // para evitar que fossem criadas repetidamente 
    // conforme o laço se repetisse, aumentando a
    // eficiência e velocidade deles.
    
    for(int i=0; i < tempos[0].length; i++)
      /* Realizando os 100 testes para o método 'sen' */
    {
      tempoInicial = System.nanoTime() / 10e6;
      // Divisão para transformar em milissegundos.
      
      teste.sen(valor);
      
      tempoFinal = System.nanoTime() / 10e6;
      // Divisão para transformar em milissegundos.
      
      tempos[0][i] = tempoFinal - tempoInicial;
      // Guarda o tempo de execução da função 'sen' para
      // o valor passado pelo usuário no teste 'i'.
    }
    
    float valorFloat = (float) valor;
    // A variável 'valorFloat' armazena o equivalente
    // ao 'valor' passado pelo usuário após o typecast,
    // que transforma o valor 'double' (padrão para va-
    // lores de ponto flutuante em Java) para 'float'
    
    for(int i=0; i < tempos[0].length; i++)
      /* Realizando os 100 testes para o método 'sen' */
    {
      tempoInicial = System.nanoTime() / 10e6;
      teste.senFloat(valorFloat);
      tempoFinal = System.nanoTime() / 10e6;
      tempos[1][i] = tempoFinal - tempoInicial;
      // Guarda o tempo de execução da função 'senFloat' para
      // o valor passado pelo usuário no teste 'i'.
    }
    
    double[][] estatísticas = estatística(tempos, 2);
    /**
     * Carregando a média e o desvio-padrão para as fun-
     * ções 'sen' e 'senFloat', armazenando em uma nova
     * matriz 'estatísticas' os valores retornados na
     * matriz pelo método 'estatística' 
     */
    
    double[] ganhoMédio = new double[100];
    for(int i=0; i < ganhoMédio.length; i++)
      ganhoMédio[i] = 100.0 - (tempos[1][i] * 100) / tempos[0][i]; // Multiplica-se por 100 para obter a porcentagem
    // Criando, acima, um vetor para calcular o ganho médio do 'float'
    // em relação ao 'double', quando executando a função 'seno' baseada
    // em polinômios de Taylor usando estes dois tipos de variáveis.
    
    System.out.println("Relatório Comparativo das Funções Seno e Cosseno Calculadas com Variáveis Double e Float");
    System.out.println(); // Imprimindo o título do relatório.
    
    for(int j = 0; j < tempos[0].length; j++)
      /* Imprimindo, lado a lado, o tempo demorado para realizar as 
       * funções 'sen' e 'senFloat' para cada caso mostrado.
       */
    {
      System.out.println("Double(" + (j+1) + "): " + tempos[0][j] + " ms          Float(" + (j+1) + "): " + tempos[1][j] + " ms");
      System.out.println("Ganho médio para o teste " + (j+1) + ": " + ganhoMédio[j] + " %");
      System.out.println();
    }
    
    System.out.println("Média do seno com double: " + estatísticas[0][0] + ";");
    System.out.println("Média do seno com float: " + estatísticas[0][1] + ".");
    System.out.println("Ganho médio do uso de float no lugar de double em relação à média: " + estatísticas[0][1] * 100 / estatísticas[0][0]);
    // Imprimindo as informações da média dos tempos de 'sen' com float e double,
    // e mostrando o ganho médio, nas médias, com o uso de 'float' em relação ao
    // uso de 'double'
    
    System.out.println();
    
    System.out.println("Desvio-padrão do seno com double: " + estatísticas[1][0] + ";");
    System.out.println("Desvio-padrão do seno com float: " + estatísticas[1][1] + ".");
    System.out.println("Ganho médio do uso de float no lugar de double em relação ao desvio-padrão: " + estatísticas[1][1] * 100 / estatísticas[1][0]);
    // Realizando processo análogo ao acima, e imprimindo o ganho médio em relação
    // ao uso de 'float' com o desvio-padrão (revelando que o uso dele pode criar
    // variações menores no tempo de execução.
  
  } // void imprimeComparativoDeSenos(double valor) 
  
} // class TestesDeDesempenho