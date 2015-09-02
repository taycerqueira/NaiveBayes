package naivebayes;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws Exception {
		BaseDados dados = new BaseDados("iris.arff");
		try {
			dados.lerArquivo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
