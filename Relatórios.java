class Relatórios
{
  FunçõesMatemáticas função = new FunçõesMatemáticas();
  /**
   *   Instanciamos um objeto da classe 'FunçõesMatemáticas'
   * para permitir que o usuário possa realizar chamadas dos
   * métodos dessas funções por meio de objetos dessas classes
   * mesmo. Assim, é possível imprimir relatórios personaliza-
   * dos, nos quais temos misturados os valores padronizados
   * dados pelos objetos da classe 'TesteDeDesemenho' mistu-
   * rados com os testes de tempo baseados ns entradas passa-
   * das pelo usuário para os objetos de 'FunçõesMatemáticas'
   */
  
  TesteDeDesempenho teste = new TesteDeDesempenho();
  /**
   *   Este objeto 'teste' pertence a classe 'TesteDeDesempenho', 
   * cujos métodos 'imprimeRelatórioGeralDeTempos()' e 'imprime-
   * ComparativoDeSenos' imprimem relatórios com 100 testes cada.
   * Os valores usados são padronizados para variar em intervalos
   * regulares de 0.01. Ao criar um objeto e chamar uma das funções
   * 'seno', 'cosseno', 'ln' ou 'raiz', o valor calculado automa-
   * ticamente substituirá o valor padronizado mais próximo para
   * este objeto. Uma vez substituído, o tempo de execução do mé-
   * todo cuja entrada foi este valor aparecerá com um asterisco 
   * (*) ao lado do seu valor no relatório.
   */
  
  TesteDeDesempenho testeGeral = new TesteDeDesempenho();
  /**
   * Este segundo objeto da classe 'TesteDeDesempenho' mantém
   * sempre os valores-padrão calculados internamente na classe
   * para permitir imprimir o relatório-padrão e também definir
   * em que lugar da matriz 'tempos' do método 'teste' será colo-
   * cado o tempo correspondente a uma chamada de um dos métodos
   * 'sen', 'cos', 'ln', 'raiz' ou 'senFloat', quando comparado
   * ao valor-padrão.
   */
  
  
  double seno(double x)
  {
    carregaValorDaFunçãoParaRelatório(x, 0);
    // O valor 0 corresponde à função 'sen' dentro do
    // método 'carregaValorDaFunçãoParaRelatório()'
    return função.sen(x);
  }
  
  double cosseno(double x)
  {
    carregaValorDaFunçãoParaRelatório(x, 1);
    // O valor 1 corresponde à função 'cos' dentro do
    // método 'carregaValorDaFunçãoParaRelatório()'
    return função.cos(x);
  }
  
  double ln(double x)
  {
    carregaValorDaFunçãoParaRelatório(x, 2);
    // O valor 2 corresponde à função 'ln' dentro do
    // método 'carregaValorDaFunçãoParaRelatório()'
    return função.ln(x);
  }
  
  double raiz(double x)
  {
    carregaValorDaFunçãoParaRelatório(x, 3);
    // O valor 3 corresponde à função 'raiz' dentro do
    // método 'carregaValorDaFunçãoParaRelatório()'
    return função.raiz(x);
  }
  
  float senFloat (float x)
  {
    carregaValorDaFunçãoParaRelatório(x, 4);
    // O valor 4 corresponde à função 'senFloat' dentro
    // do método 'carregaValorDaFunçãoParaRelatório()'
    return função.senFloat(x);
  }
  
  
  
  void carregaValorDaFunçãoParaRelatório(double valor, int tipo)
    /**
     * A função 'carregaValorDaFunçãoParaRelatório()'
     * tem por objetivo substituir, no objeto 'teste',
     * os valores de tempo para as funções cuja entrada
     * foi testada pelo usuário.
     */
  {
    teste.carregaTemposSeno();
    teste.carregaTemposCosseno();
    teste.carregaTemposLogaritmo();
    teste.carregaTemposRaiz();
    teste.estatísticas();
    
    testeGeral.carregaTemposSeno();
    testeGeral.carregaTemposCosseno();
    testeGeral.carregaTemposLogaritmo();
    testeGeral.carregaTemposRaiz();
    testeGeral.estatísticas();
    // As chamadas de método acima apenas fazem com que
    // sejam carregados os valores-padrão nas matrizes
    // 'tempos' tanto para 'teste' como para 'testeGeral'
    
    double tempoInicial = 0;
    double tempoFinal = 0;
    teste.imprimeRelatórioGeralDeTempos();
    teste.imprimeComparativoDeSenos();
    
    if(tipo == 0)
    {
      tempoInicial = System.nanoTime() / 10e6;
      função.sen(valor);
      tempoFinal = System.nanoTime() / 10e6;
    }
    if(tipo == 1)
    {
      tempoInicial = System.nanoTime() / 10e6;
      função.cos(valor);
      tempoFinal = System.nanoTime() / 10e6;
    }
    if(tipo == 2)
    {
      tempoInicial = System.nanoTime() / 10e6;
      função.ln(valor);
      tempoFinal = System.nanoTime() / 10e6;
    }
    if(tipo == 3)
    {
      tempoInicial = System.nanoTime() / 10e6;
      função.raiz(valor);
      tempoFinal = System.nanoTime() / 10e6;
    }
    if(tipo == 4)
    {
      tempoInicial = System.nanoTime() / 10e6;
      função.senFloat((float) valor);
      tempoFinal = System.nanoTime() / 10e6;
    }
    
    double tempoDeExecução = tempoFinal - tempoInicial;
    
    for(int i=0; i < teste.tempos.length; i++)
    {
      double diferença = tempoDeExecução*tempoDeExecução - teste.tempos[0][tipo]*teste.tempos[0][tipo];
      if(tempoDeExecução*tempoDeExecução - teste.tempos[i][tipo]*teste.tempos[i][tipo] < diferença)
        diferença = tempoDeExecução*tempoDeExecução - teste.tempos[i][tipo]*teste.tempos[i][tipo];
      
      for(int j=0; diferença != tempoDeExecução*tempoDeExecução - teste.tempos[j][tipo]*teste.tempos[j][tipo]; j++)
        teste.tempos[j][tipo] = valor;
    }
    
  } // void carregaValorDaFunçãoParaRelatório(double valor, int tipo)
  
  
  
  
  
  
  
  void imprimeRelatórioDeTempos()
  {
    String[] funções = {"seno", "cosseno", "logaritmo natural", "raiz"};
    
    System.out.println("Relatório de Desempenho das Funções Matemáticas com Séries de Taylor");
    System.out.println();
    
    
    for(int i = 0; i < funções.length; i++)
    {
      System.out.println("Função " + funções[i] + ":");
      System.out.println();
      
      System.out.println("A função " + funções[i] + " teve os seguintes valores de teste: ");
      
      for(int j = 0; j <= teste.tempos.length; j++)
      {
        if(teste.tempos[j][i] != testeGeral.tempos[j][i])
          System.out.print((j+1) + "*: " + teste.tempos[j][i] + " ms          ");
        else
          System.out.print((j+1) + ": " + teste.tempos[j][i] + " ms          ");
      }
        
      System.out.println();
      System.out.println("Média das medições: " + teste.estatísticas[i][0]);
      System.out.println("Desvio-padrão das medições: " + teste.estatísticas[i][1]);
      
      if(i < 3)
        System.out.println();
    }
    
  }
  
  
  
  void imprimeComparativoDeSenos()
  {
    double[] ganhoMédio = new double[100];
    for(int i=0; i < ganhoMédio.length; i++)
      ganhoMédio[i] = (teste.tempos[i][4] * 100) / teste.tempos[i][0]; // Multiplica-se por 100 para obter a porcentagem
    
    
    System.out.println("Relatório Comparativo das Funções Seno e Cosseno Calculadas com Variáveis Double e Float");
    System.out.println();
    
    for(int j = 0; j < teste.tempos.length; j++)
    {
      if(testeGeral.tempos[j][0] != teste.tempos[j][0])
      {
        System.out.println("Double(" + (j+1) + ")*: " + teste.tempos[j][0] + " ms          Float(" + (j+1) + "): " + teste.tempos[j][4] + " ms");
        System.out.println("Ganho médio para o teste " + (j+1) + ": " + ganhoMédio[j] + " %");
        System.out.println();
      }
      else if(testeGeral.tempos[j][4] != teste.tempos[j][4])
      {
        System.out.println("Double(" + (j+1) + "): " + teste.tempos[j][0] + " ms          Float(" + (j+1) + ")*: " + teste.tempos[j][4] + " ms");
        System.out.println("Ganho médio para o teste " + (j+1) + ": " + ganhoMédio[j] + " %");
        System.out.println();
      }
      else if(testeGeral.tempos[j][0] != teste.tempos[j][0] && testeGeral.tempos[j][4] != teste.tempos[j][4])
      {
        System.out.println("Double(" + (j+1) + ")*: " + teste.tempos[j][0] + " ms          Float(" + (j+1) + ")*: " + teste.tempos[j][4] + " ms");
        System.out.println("Ganho médio para o teste " + (j+1) + ": " + ganhoMédio[j] + " %");
        System.out.println();
      }
      else
      {
        System.out.println("Double(" + (j+1) + "): " + teste.tempos[j][0] + " ms          Float(" + (j+1) + "): " + teste.tempos[j][4] + " ms");
        System.out.println("Ganho médio para o teste " + (j+1) + ": " + ganhoMédio[j] + " %");
        System.out.println();
      }
    }
    
    System.out.println("Média do seno com double: " + teste.estatísticas[0][0] + ";");
    System.out.println("Média do seno com float: " + teste.estatísticas[4][0] + ".");
    System.out.println("Ganho médio do uso de float no lugar de double em relação à média: " + teste.estatísticas[4][0] * 100 / teste.estatísticas[0][0]);
    System.out.println();
    
    System.out.println("Desvio-padrão do seno com double: " + teste.estatísticas[0][1] + ";");
    System.out.println("Desvio-padrão do seno com float: " + teste.estatísticas[4][1] + ".");
    System.out.println("Ganho médio do uso de float no lugar de double em relação ao desvio-padrão: " + teste.estatísticas[4][0] * 100 / teste.estatísticas[0][0]);
  }
    
  
  
  void imprimeRelatórioDeTemposPadrão()
    /**
     * Imprime o relatório com as médias e desvio-
     * -padrão dos métodos que calculado o seno, o
     * cosseno, o logaritmo natural e a raiz base-
     * ados em Polinômios de Taylor.
     */
  {
    testeGeral.imprimeRelatórioGeralDeTempos();
  }
  
  void imprimeComparativoDeSenosPadrão()
    /**
     * Imprime o relatório de comparação de velocidade
     * entre o seno com float e o seno com double 
     * com os valores-padrão calculados diretamente
     * pela classe 'TesteDeDesempenho'
     */
  {
    testeGeral.imprimeComparativoDeSenos();
  }
}