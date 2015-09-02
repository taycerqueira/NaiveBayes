package naivebayes;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws Exception {
		BaseDados dados = new BaseDados("iris.arff");
		NaiveBayes naive = new NaiveBayes();
		try {
			//dados.lerArquivo();
			naive.lerDados("iris.arff");
			naive.processar();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
