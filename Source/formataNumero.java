/*  1:   */ import br.gov.pbh.desif.model.registros.RegUtil;
/*  2:   */ import java.io.PrintStream;
/*  3:   */ 
/*  4:   */ public class formataNumero
/*  5:   */ {
/*  6:   */   int posicao;
/*  7:   */   
/*  8:   */   public String iniciar(String entrada)
/*  9:   */   {
/* 10:11 */     String numero = entrada;
/* 11:   */     
/* 12:13 */     RegUtil util = new RegUtil();
/* 13:14 */     if (util.verificaCasasDecimais(numero))
/* 14:   */     {
/* 15:15 */       numero = verificaPontoDecimal(numero);
/* 16:16 */       numero = "R$ " + inserePontoMilhar(numero);
/* 17:17 */       System.out.println("Número formatado: " + numero);
/* 18:   */     }
/* 19:   */     else
/* 20:   */     {
/* 21:20 */       System.out.println("Deu erro!!!!!!");
/* 22:   */     }
/* 23:22 */     return numero;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public String verificaPontoDecimal(String entrada)
/* 27:   */   {
/* 28:26 */     String resultado = entrada;
/* 29:27 */     for (int i = 0; i < resultado.length(); i++) {
/* 30:28 */       if ('.' == resultado.charAt(i))
/* 31:   */       {
/* 32:29 */         this.posicao = i;
/* 33:30 */         resultado = trocaPontoPorVirgula(resultado);
/* 34:   */       }
/* 35:   */     }
/* 36:33 */     return resultado;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public String trocaPontoPorVirgula(String entrada)
/* 40:   */   {
/* 41:37 */     String resultadoTroca = entrada = entrada.replace('.', ',');
/* 42:38 */     return resultadoTroca;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public String inserePontoMilhar(String entrada)
/* 46:   */   {
/* 47:42 */     String resultadoInserePonto = entrada;
/* 48:43 */     int cont = 1;
/* 49:44 */     for (int i = this.posicao - 1; i > 0; i--)
/* 50:   */     {
/* 51:45 */       char ponto = '.';
/* 52:46 */       if (cont == 3)
/* 53:   */       {
/* 54:47 */         resultadoInserePonto = resultadoInserePonto.substring(0, i) + ponto + resultadoInserePonto.substring(i, resultadoInserePonto.length());
/* 55:48 */         cont = 0;
/* 56:   */       }
/* 57:50 */       cont++;
/* 58:   */     }
/* 59:52 */     return resultadoInserePonto;
/* 60:   */   }
/* 61:   */ }


/* Location:           C:\JAVA-DECOMP\BHDigital\DESIF.jar
 * Qualified Name:     formataNumero
 * JD-Core Version:    0.7.0.1
 */